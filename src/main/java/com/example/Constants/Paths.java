/*
 * Author: Matěj Šťastný
 * Date created: 5/16/2024
 * Github link: https://github.com/kireiiiiiiii/TargetGame
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

package com.example.Constants;

import java.io.File;

/**
 * Contstants for file paths. 
 * 
 */
public class Paths {
    
    /////////////////
    // Directory constants
    ////////////////

    public static final String RESOURCE_DIR = getResourcesDir();
    public static final String FONT_DIR = getFontsDir();

    /////////////////
    // Private methods
    ////////////////

    /**
     * Finds the name of the java file it was executed in using
     * {@code StackTraceElement} to find the caller of the method, looking for the
     * class it was excecuted in, and than appending ".java" to it to get the file
     * name.
     * 
     * @return returns a {@code String} od the file name in a format:
     *         "fileName.java"
     */
    private static String getFileName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // The caller class will be at index 2 in the stack trace
        String callerClassName = stackTrace[2].getClassName();
        // Extracting the file name from the class name
        String fileName = callerClassName.substring(callerClassName.lastIndexOf('.') + 1) + ".java";
        return fileName;
    }

    /**
     * Finds the path of the folder, where the java file this method was executed in
     * is located
     * 
     * @return String with path
     */
    private static String getProjectFolderPath() {
        String fileName = getFileName();
        int nameLenght = fileName.length();
        File folder = new File(fileName);
        String absolutePath = folder.getAbsolutePath();
        int pathLenght = absolutePath.length();
        return absolutePath.substring(0, pathLenght - nameLenght - 1);
    }

    /**
     * Gets the resources directory path. 
     * 
     * @return
     */
    private static String getResourcesDir() {
        return getProjectFolderPath() + File.separator + "src" + File.separator + "main" + File.separator + "Resources";
    }

    /**
     * Gets the fonts directory path. 
     * 
     * @return
     */
    private static String getFontsDir() {
        return getResourcesDir() + File.separator + "Fonts";
    }


}
