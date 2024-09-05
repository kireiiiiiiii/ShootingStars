/*
 * Author: Matěj Šťastný
 * Date created: 5/18/2024
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

package com.kireiiiiiiii.shooting_stars.common;

import java.io.IOException;

import com.kireiiiiiiii.shooting_stars.constants.Files;
import com.kireiiiiiiii.shooting_stars.constants.GameDialogue;

/**
 * File containing the user settings in order to be loaded the next time the
 * game is opened.
 * 
 */
public class Settings {

    /////////////////
    // Variables
    ////////////////

    private static AdvancedVariable<Language> language = new AdvancedVariable<>(Files.USER_CONFIG_FILE);

    /////////////////
    // Accesors
    ////////////////

    /**
     * Returns the language value from the settings file, if the value is not set,
     * returns the default language.
     * 
     * @return a {@code Language} enum value.
     */
    public static Language getLanguage() {
        try {
            language.loadFromFile(Language.class);
        } catch (IOException e) {
            language.set(GameDialogue.DEFAULT_LANGUAGE);
            try {
                language.save();
            } catch (IOException e1) {
                System.out.println("FATAL - Could not save settings language data");
            }
        }
        return language.get();
    }

    /////////////////
    // Modifiers
    ////////////////

    /**
     * Setter for the language value. This method saves the file when excecuted.
     * 
     * @param newLanguage - new {@code Language} enum value.
     */
    public static void setLanguage(Language newLanguage) {
        language.set(newLanguage);
        try {
            language.save();
        } catch (IOException e) {
            System.out.println("FATAL - Could not save language settings data");
        }
    }

}
