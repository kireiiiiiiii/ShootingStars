/*
 * Author: Matěj Šťastný
 * Date created: TODO: Change date
 * Github link: TODO: Insert GitHub repo link, where the util class was used in
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

package Helpers;

import java.io.File;

/**
 * A method utility class for getting paths to files or directories. It works by
 * looking for the caller of the method in the Stack, and then getting the class
 * the method was excecuted in.
 * 
 */
public class PathUtil {

    /////////////////
    // Get file name methods
    ////////////////

    /**
     * Finds the name of the java file it was executed in by looking for the class
     * this method lays in.
     * Doesn't work when called from another class, returns the name of this file.
     * 
     * @return returns a string "fileName.java"
     */
    @SuppressWarnings("unused")
    private static String getLocalFileName() {
        // Get the name of the class (excluding the package) and append ".java"
        String className = new Object() {
        }.getClass().getEnclosingClass().getSimpleName();
        String fileName = className + ".java";
        return fileName;
    }

    /**
     * Finds the name of the java file it was executed in using
     * {@code StackTraceElement} to find the caller of the method, looking for the
     * class it was excecuted in, and than appending ".java" to it to get the file
     * name.
     * 
     * @return returns a {@code String} od the file name in a format:
     *         "fileName.java"
     */
    public static String getFileName() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        // The caller class will be at index 2 in the stack trace
        String callerClassName = stackTrace[2].getClassName();
        // Extracting the file name from the class name
        String fileName = callerClassName.substring(callerClassName.lastIndexOf('.') + 1) + ".java";
        return fileName;
    }

    /////////////////
    // Get path methods
    ////////////////

    /**
     * Finds the path of the folder, where the java file this method was executed in
     * is located
     * 
     * @return String with path
     */
    public static String getProjectFolderPath() {
        String fileName = getFileName();
        int nameLenght = fileName.length();
        File folder = new File(fileName);
        String absolutePath = folder.getAbsolutePath();
        int pathLenght = absolutePath.length();
        return absolutePath.substring(0, pathLenght - nameLenght - 1);
    }
}
