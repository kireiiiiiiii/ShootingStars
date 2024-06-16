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

/**
 * Constant class with all the game dialogues.
 * 
 */
public class GameDialogue {

    ////////////////
    // Language enum
    ////////////////

    enum Language {
        ENGLISH, CZECH, JAPANESE, KOREAN
    }

    ////////////////
    // Variables
    ////////////////

    private static Language[] languages = Language.values();
    private static int currLanguageIndex = 0;

    /////////////////
    // Language modifiers
    ////////////////

    /**
     * Sets the language index. Will throw {@code IllegalArgumentException} if the
     * given idex is larger than the numer of languages.
     * 
     * @param value - new index value.
     */
    public static void setLanguageIndex(int value) {
        if (value > languages.length - 1) {
            throw new IllegalArgumentException("Invalid language index");
        }
        currLanguageIndex = value;
    }

    /////////////////
    // Global
    ////////////////

    /**
     * @return - Title of the game.
     */
    public static String appTitle() {
        switch (currLanguageIndex) {
            case 1:
                return "Padající Hvězdy";
            case 2:
                return "流れ星撃ち";
            case 3:
                return "별을 쏘다";
            default:
                return "Shooting stars";
        }
    }

    /////////////////
    // Main menu
    ////////////////

    public static String menuSubText() {
        switch (currLanguageIndex) {
            case 1:
                return "Stiskněte libovolnou klávesu pro pokračování";
            case 2:
                return "続行するには任意のキーを押してください";
            case 3:
                return "아무 키나 누르세요 계속하려면";
            default:
                return "Press any key to continue";
        }
    }

    /////////////////
    // Game widgets
    ////////////////

    public static String score() {
        switch (currLanguageIndex) {
            case 1:
                return "Skóre";
            case 2:
                return "スコア";
            case 3:
                return "점수";
            default:
                return "Score";
        }
    }

    public static String topscore() {
        switch (currLanguageIndex) {
            case 1:
                return "Nejlepší skóre";
            case 2:
                return "トプスコア";
            case 3:
                return "최고 점수";
            default:
                return "Top score";
        }
    }

    public static String timeLeft() {
        switch (currLanguageIndex) {
            case 1:
                return "Zbývající čas";
            case 2:
                return "残り時間";
            case 3:
                return "남은 시간 ";
            default:
                return "Time left";
        }
    }

    /////////////////
    // Game over screen
    ////////////////

    public static String gameOver() {
        switch (currLanguageIndex) {
            case 1:
                return "KONEC HRY";
            case 2:
                return "ゲームオーバー";
            case 3:
                return "게임 오버";
            default:
                return "GAME OVER";
        }
    }

    public static String gameOverSubtext() {
        switch (currLanguageIndex) {
            case 1:
                return "Stiskněte R pro restart";
            case 2:
                return "再起動するには \"R\" を押してください";
            case 3:
                return "다시 시작하려면 R을 누르세요";
            default:
                return "Press R to restart";
        }
    }

    /////////////////
    // Pause screen
    ////////////////

    public static String pause() {
        switch (currLanguageIndex) {
            case 1:
                return "Pozastaveno";
            case 2:
                return "一時停止";
            case 3:
                return "일시 정지";
            default:
                return "Pause";
        }
    }
}
