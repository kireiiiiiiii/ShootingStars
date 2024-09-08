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
import java.lang.reflect.Field;

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

    public static AdvancedVariable<SettingsStructure> settings = new AdvancedVariable<>(Files.USER_CONFIG_FILE);

    /////////////////
    // Save method
    ////////////////

    public static void save() {
        settings.set(new SettingsStructure(GameDialogue.getLanguageIndex()));

        try {
            settings.save();
        } catch (IOException e) {
            System.out.println("FATAL - Failed to save settings");
        }
    }

    /////////////////
    // Accesors
    ////////////////

    public static Object getValue(String fieldName) {
        // Load the variable
        try {
            settings.loadFromFile(SettingsStructure.class);
        } catch (IOException e) {
            settings.set(new SettingsStructure(0));
            try {
                settings.save();
            } catch (IOException e1) {
                System.out.println("FATAL - Failed to save settings");
            }
        }

        Object obj = settings.get();
        // Get the class of the object
        Class<?> objClass = obj.getClass();

        // Get the field by name (this does not include private fields in superclasses)
        Field field;
        try {
            field = objClass.getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);

        } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
            System.out.println(
                    "FATAL - " + e.getClass().getName() + " occured when trying to acces a settings field value");
            return null;
        }
    }

    /**
     * An object structure class to hold all of the settings values.
     * 
     */
    public static class SettingsStructure {
        // Empty constructor used by the jackson library.
        public SettingsStructure() {
        }

        // Parameterized constructer used to create a new object with values.
        public SettingsStructure(int languageIndex) {
            this.languageIndex = languageIndex;
        }

        // ---- Settings ----

        public int languageIndex = 1;
    }

}
