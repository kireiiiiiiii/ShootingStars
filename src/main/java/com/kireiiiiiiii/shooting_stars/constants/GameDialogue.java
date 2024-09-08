/*
 * Author: Matěj Šťastný
 * Date created: 6/15/2024
 * Github link: https://github.com/kireiiiiiiii/ShootingStars
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

package com.kireiiiiiiii.shooting_stars.constants;

import com.kireiiiiiiii.shooting_stars.common.Settings;
import com.kireiiiiiiii.shooting_stars.tools.SpreadsheetUtil;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Constant class with all the game dialogues.
 * 
 */
public class GameDialogue {

    ////////////////
    // Constants
    ////////////////

    private static final String SPREADSHEET_FILENAME = "dialogue";
    private static final String SPREADSHEET_NAME = "dialogue";

    ////////////////
    // Variables
    ////////////////

    private static int currLanguageIndex = 0;

    ////////////////
    // Dialogue varibles
    ////////////////

    // ---- Language name ----
    public static String languageName;
    // ---- Fonts ----
    public static String headingFont;
    public static String textFont;
    // ---- Gloabl ----
    public static String languageDisplayName;
    public static String appName;
    // ---- Main menu ----
    public static String menuSubText;
    // ---- Game Widgets ----
    public static String score;
    public static String topscore;
    public static String timeLeft;
    // ---- Game over screen ----
    public static String gameOver;
    public static String gameOverSubtext;
    // ---- Pause screen ----
    public static String pause;

    /////////////////
    // Modifiers
    ////////////////

    public static void setNextLanguage() {
        int languageCount = getLanguages().size();
        currLanguageIndex++;
        if (currLanguageIndex > languageCount - 1) {
            currLanguageIndex = 0;
        }
        changeLanguage(currLanguageIndex);
    }

    public static void setPreviousLanguage() {
        int languageCount = getLanguages().size();
        currLanguageIndex--;
        if (currLanguageIndex < 0) {
            currLanguageIndex = languageCount - 1;
        }
        changeLanguage(currLanguageIndex);
    }

    public static void initialLanguageSet(int index) {
        System.out.println(index);
        if (index < 0 || index > getLanguages().size() - 1) {
            index = 0;
        }
        changeLanguage(index);
    }

    /////////////////
    // Accesors
    ////////////////

    /**
     * Gets all of the available languages from the dialogue spreadsheet.
     * 
     * @return an {@code ArrayList} of strings with names of the languages.
     */
    public static ArrayList<String> getLanguages() {
        ArrayList<String> languages = SpreadsheetUtil.getRowValues(SPREADSHEET_FILENAME, SPREADSHEET_NAME, 0);
        languages.remove(0);
        return languages;
    }

    /////////////////
    // Private methods
    ////////////////

    private static void changeLanguage(int languageIndex) {
        // ---- Set variables ---
        currLanguageIndex = languageIndex;

        // ---- Change dialogue variables ----
        ArrayList<String> values = SpreadsheetUtil.getColumnValues(SPREADSHEET_FILENAME, SPREADSHEET_NAME,
                currLanguageIndex);
        String[] result = new String[values.size()];

        for (int i = 0; i < values.size(); i++) {
            result[i] = SpreadsheetUtil.getCellValue(SPREADSHEET_FILENAME, SPREADSHEET_NAME, i, languageIndex + 1);
        }

        System.out.println(Arrays.toString(result));

        // Assign the variables
        languageName = result[0];
        headingFont = result[1];
        textFont = result[2];
        languageDisplayName = result[3];
        appName = result[4];
        menuSubText = result[5];
        score = result[6];
        topscore = result[7];
        timeLeft = result[8];
        gameOver = result[9];
        gameOverSubtext = result[10];
        pause = result[11];
    }

}
