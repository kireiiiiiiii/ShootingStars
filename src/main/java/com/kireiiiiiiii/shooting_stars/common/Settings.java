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

/**
 * File containing the user settings in order to be loaded the next time the
 * game is opened.
 * 
 */
public class Settings {

    /////////////////
    // Variables
    ////////////////

    private static AdvancedVariable<Integer> languageIndex = new AdvancedVariable<>(Files.USER_CONFIG_FILE);

    /////////////////
    // Accesors
    ////////////////

    /**
     * Returns the language value from the settings file, if the value is not set,
     * returns the default language.
     * 
     * @return a {@code Language} enum value.
     */
    public static int getLanguageIndex() {
        try {
            languageIndex.loadFromFile(Integer.class);
        } catch (IOException e) {
            languageIndex.set(-1);
        }
        return languageIndex.get();
    }

}
