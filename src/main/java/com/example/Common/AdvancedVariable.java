/*
 * Author: Matěj Šťastný
 * Date created: 5/5/2024
 * Github link:  https://github.com/kireiiiiiiii/ShootingStars
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package com.example.Common;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;

/**
 * JsonVariableManager - A utility class for managing variables and persisting them to JSON files.
 * This class allows storing variables of any type and saving them to JSON files for later retrieval.
 * It leverages the com.fasterxml.jackson library for serialization and deserialization.
 * <p>
 * Example Usage:
 * <blockquote><pre>
 *      // Creating an instance of JsonVariableManager to store a CustomObject
 *      JsonVariableManager<CustomObject> customObjectVariableManager = new JsonVariableManager<>("customObjectFile.json");
 *      CustomObject customObject = new CustomObject("John Doe", 30);
 *      customObjectVariableManager.setValue(customObject);
 * 
 *      try {
 *          // Save the variable to JSON file
 *          customObjectVariableManager.saveToFile();
 * 
 *          // Load the variable from JSON file
 *          customObjectVariableManager.loadFromFile(CustomObject.class);
 *          System.out.println("Loaded value: " + customObjectVariableManager.getValue().getName() + ", " + customObjectVariableManager.getValue().getAge());
 *      } catch (IOException e) {
 *          e.printStackTrace();
 *      }
 *  </pre></blockquote><p>
 * 
 * @param <T> The type of the variable to be stored and managed.
 * 
 * </p>
 * 
 * Dependencies:
 * - com.fasterxml.jackson.databind.ObjectMapper
 * <p>
 * 
 * Note: Ensure that the specified file path for saving/loading JSON files is accessible and valid.
 */

public class AdvancedVariable<T> {

    /////////////////
    // Fields
    ////////////////

    private T storedValue;
    private File saveFile;
    private final ObjectMapper objectMapper;
    private final ObjectWriter objectWriter;

    /////////////////
    // Constructor
    ////////////////

    /**
     * Default empty constructor. Initializes the default {@code ObjectMapper} and a
     * custom {@code ObjectWritter} with pretty writing (custom class shown below in
     * this file).
     * 
     */
    private AdvancedVariable() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        this.objectWriter = this.objectMapper.writer(
                new DefaultPrettyPrinter() {
                    @Override
                    public DefaultPrettyPrinter createInstance() {
                        return new CustomPrettyPrinter();
                    }
                });
    }

    /**
     * Initializes the save file and loads the value of the class type given from
     * the file.
     * 
     * @param saveFile - save file.
     * @param type     - class type of the variable.
     * @throws IOException
     */
    public AdvancedVariable(File saveFile, Class<T> type) throws IOException {
        this(saveFile);
        loadFromFile(type);
    }

    /**
     * Initializes the save file and loads the value of the class type given from
     * the file.
     * 
     * @param saveFilePath - save file path.
     * @param type         - class type of the variable.
     * @throws IOException
     */
    public AdvancedVariable(String saveFilePath, Class<T> type) throws IOException {
        this(new File(saveFilePath), type);
    }

    /**
     * Initializes the save file, doesn't set the value.
     * 
     * @param saveFile - save file.
     */
    public AdvancedVariable(File saveFile) {
        this();
        this.saveFile = saveFile;
    }

    /**
     * Initializes the save file, doesn't set the value.
     * 
     * @param saveFilePath - save file path.
     */
    public AdvancedVariable(String saveFilePath) {
        this(new File(saveFilePath));
    }

    /**
     * Initializes the save file, and sets the variable value.
     * 
     * @param saveFile - save file.
     * @param value    - value that this variable was set to.
     */
    public AdvancedVariable(File saveFile, T value) {
        this();
        this.saveFile = saveFile;
        this.set(value);
    }

    /**
     * Initializes the save file, and sets the default value.
     * 
     * @param saveFilePath - save file path.
     * @param value        - value that this variable was set to.
     */
    public AdvancedVariable(String saveFilePath, T value) {
        this(new File(saveFilePath), value);
    }

    /////////////////
    // Accessors
    ////////////////

    /**
     * Variable getter. Returns the value of this variable.
     * 
     * @return stored variable.
     */
    public T get() {
        return storedValue;
    }

    /////////////////
    // Modifiers
    ////////////////

    /**
     * Sets a new value to this variable.
     * 
     * @param value - new value of the variable.
     */
    public void set(T value) {
        this.storedValue = value;
    }

    /**
     * Sets a new value to this variable, and saves it into the save file.
     * 
     * @param value - new value of the variable.
     * @throws IOException
     */
    public void setAndSave(T value) throws IOException {
        this.set(value);
        this.save();
    }

    /////////////////
    // Save/Load methods
    ////////////////

    /**
     * Saves the current stored value of this variable into it's save file.
     * 
     * @throws IOException
     */
    public void save() throws IOException {
        this.objectWriter.writeValue(this.saveFile, storedValue);
    }

    /**
     * Loads a value from this variables save file, and than saves it into this
     * variable. Returns if the read was successful.
     * 
     * @param valueType - {@code Class} of the variable this object is reading.
     * @return if the read was successful.
     * @throws IOException
     */
    public boolean loadFromFile(Class<T> valueType) throws IOException {
        if (this.saveFile.exists()) {
            storedValue = this.objectMapper.readValue(this.saveFile, valueType);
        } else {
            return false;
        }
        return true;
    }

    /**
     * Reads the content of a resource file as a String.
     * 
     * @param resourceName The name of the resource.
     * @return The content of the resource as a String.
     * @throws IOException if an I/O error occurs.
     */
    public String readResource(String resourceName) throws IOException {
        StringBuilder content = new StringBuilder();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            if (inputStream == null) {
                throw new IOException("Resource not found: " + resourceName);
            }
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        return content.toString();
    }

    /**
     * Writes an object to a file.
     * 
     * @param filePath the path of the file to write to
     * @param contents - the {@code String} object of the contents
     * @throws IOException if an I/O error occurs
     */
    public void writeObject(String filePath, String contents) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(contents);
        }
    }

    /////////////////
    // Nested Classes
    ////////////////

    /**
     * Custom pretty writer to make the {@code JSON} file more readable, it puts brackets on new lines.
     * 
     */
    private static class CustomPrettyPrinter extends DefaultPrettyPrinter {
        @Override
        public void writeObjectFieldValueSeparator(JsonGenerator jg) throws IOException {
            jg.writeRaw(": ");
        }

        @Override
        public void writeStartObject(JsonGenerator jg) throws IOException {
            jg.writeRaw("{\n");
        }

        @Override
        public void writeEndObject(JsonGenerator jg, int nrOfEntries) throws IOException {
            jg.writeRaw("\n");
            super.writeEndObject(jg, nrOfEntries);
        }

        @Override
        public void writeStartArray(JsonGenerator jg) throws IOException {
            jg.writeRaw("[\n");
        }

        @Override
        public void writeEndArray(JsonGenerator jg, int nrOfValues) throws IOException {
            jg.writeRaw("\n");
            super.writeEndArray(jg, nrOfValues);
        }
    }
}
