/*
 * Author: Matěj Šťastný
 * Date created: 6/15/2024
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

import com.example.Common.Language;

/**
 * Constant class with all the game dialogues.
 * 
 */
public class GameDialogue {

    ////////////////
    // Variables
    ////////////////

    private static Language currLanguage = Language.ENGLISH;

    /////////////////
    // Modifiers
    ////////////////

    /**
     * Sets the language index. Will throw {@code IllegalArgumentException} if the
     * given idex is larger than the numer of languages.
     * 
     * @param value - new index value.
     */
    public static void setLanguageIndex(Language language) {
        currLanguage = language;
    }

    /////////////////
    // Accesors
    ////////////////

    public static Language getCurrentLanguage() {
        return currLanguage;
    }

    /////////////////
    // Global
    ////////////////

    /**
     * @return - Title of the game.
     */
    public static String appTitle() {
        switch (currLanguage) {
            case CZECH:
                return "Padající Hvězdy";
            case JAPANESE:
                return "流れ星撃ち";
            case KOREAN:
                return "별을 쏘다";
            default:
                return "Shooting stars";
        }
    }

    /////////////////
    // Main menu
    ////////////////

    public static String menuSubText() {
        switch (currLanguage) {
            case CZECH:
                return "Stiskněte libovolnou klávesu pro pokračování";
            case JAPANESE:
                return "続行するには任意のキーを押してください";
            case KOREAN:
                return "아무 키나 누르세요 계속하려면";
            default:
                return "Press any key to continue";
        }
    }

    /////////////////
    // Game widgets
    ////////////////

    public static String score() {
        switch (currLanguage) {
            case CZECH:
                return "Skóre";
            case JAPANESE:
                return "スコア";
            case KOREAN:
                return "점수";
            default:
                return "Score";
        }
    }

    public static String topscore() {
        switch (currLanguage) {
            case CZECH:
                return "Nejlepší skóre";
            case JAPANESE:
                return "トプスコア";
            case KOREAN:
                return "최고 점수";
            default:
                return "Top score";
        }
    }

    public static String timeLeft() {
        switch (currLanguage) {
            case CZECH:
                return "Zbývající čas";
            case JAPANESE:
                return "残り時間";
            case KOREAN:
                return "남은 시간 ";
            default:
                return "Time left";
        }
    }

    /////////////////
    // Game over screen
    ////////////////

    public static String gameOver() {
        switch (currLanguage) {
            case CZECH:
                return "KONEC HRY";
            case JAPANESE:
                return "ゲームオーバー";
            case KOREAN:
                return "게임 오버";
            default:
                return "GAME OVER";
        }
    }

    public static String gameOverSubtext() {
        switch (currLanguage) {
            case CZECH:
                return "Stiskněte R pro restart";
            case JAPANESE:
                return "再起動するには \"R\" を押してください";
            case KOREAN:
                return "다시 시작하려면 R을 누르세요";
            default:
                return "Press R to restart";
        }
    }

    /////////////////
    // Pause screen
    ////////////////

    public static String pause() {
        switch (currLanguage) {
            case CZECH:
                return "Pozastaveno";
            case JAPANESE:
                return "一時停止";
            case KOREAN:
                return "일시 정지";
            default:
                return "Pause";
        }
    }
}
