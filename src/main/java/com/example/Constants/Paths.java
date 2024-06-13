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
import java.io.IOException;
import com.example.AppMain;

/**
 * Contstants for file paths.
 * 
 */
public class Paths {

    /////////////////
    // File names
    ////////////////

    private static final String TOP_SCORE_FILENAME = "TopScore.JSON";
    private static final String USER_DATA_DIR_NAME = "UserData";

    /////////////////
    // Directory constants
    ////////////////

    public static final String DATA_DIR = getAppDataDirectory(AppMain.APP_NAME).getAbsolutePath();
    public static final String USER_DATA_DIR = DATA_DIR + File.separator + USER_DATA_DIR_NAME;
    public static final String RESOURCE_DIR = getResourcesDir();
    public static final String FONT_DIR = getFontsDir();

    /////////////////
    // File constants
    ////////////////

    public static final File TOP_SCORE_FILE = getScoreFile();

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

    /**
     * Gets the directory where all application data is stored dependion on the OS.
     * For example:
     * </p>
     * </p>
     * On MacOS the directory is "Application Support"
     * </p>
     * On Widnows the directory is "Appdata"
     * </p>
     * If the directory doesn't exist, it will create one.
     * 
     * @param appName - name of the application.
     * @return a {@code File} object of the directory.
     */
    private static File getAppDataDirectory(String appName) {
        String osName = System.getProperty("os.name").toLowerCase();
        String userHome = System.getProperty("user.home");
        File appDataDir = null;

        // Picks a path dependion on the OS
        if (osName.contains("win")) {
            // Windows: C:\Users\<user>\AppData\Roaming\<appName>
            String appData = System.getenv("APPDATA");
            appDataDir = new File(appData, appName);
        } else if (osName.contains("mac")) {
            // macOS: /Users/<user>/Library/Application Support/<appName>
            appDataDir = new File(userHome, "Library/Application Support/" + appName);
        } else if (osName.contains("nix") || osName.contains("nux")) {
            // Linux: /home/<user>/.<appName>
            appDataDir = new File(userHome, "." + appName);
        }

        // Ensure the directory exists, and creates it if it doesn't
        if (appDataDir != null && !appDataDir.exists()) {
            appDataDir.mkdirs();
        }

        return appDataDir;
    }

    /**
     * Gets the {@code File} object of the top score file located in the computer
     * application data library.
     * 
     * @return new {@code File} object referencing the score file.
     */
    private static File getScoreFile() {
        File userDataDir = new File(USER_DATA_DIR);

        // Checks if the dir exists, and creates it if it doesn't
        if (!userDataDir.exists()) {
            userDataDir.mkdir();
        }

        File scoreFile = new File(USER_DATA_DIR + File.separator + TOP_SCORE_FILENAME);
        try {
            if (!userDataDir.exists()) {
                userDataDir.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
            assert false : "FATAL - Could not create a score save file";
        }

        return scoreFile;
    }

}
