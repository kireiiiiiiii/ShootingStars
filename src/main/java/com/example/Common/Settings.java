package com.example.Common;

import java.io.IOException;
import com.example.Constants.Files;
import com.example.Constants.GameDialogue;

public class Settings {

    private static AdvancedVariable<Language> language = new AdvancedVariable<>(Files.USER_CONFIG_FILE);

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

    public static void setLanguage(Language newLanguage) {
        language.set(newLanguage);
        try {
            language.save();
        } catch (IOException e) {
            System.out.println("FATAL - Could not save language settings data");
        }
    }

}
