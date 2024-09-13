/*
 * Author: Matěj Šťastný
 * Date created: 9/11/2024
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

import com.kireiiiiiiii.shooting_stars.AppMain;
import com.kireiiiiiiii.shooting_stars.common.Links;

public class Interact {

    public static final Runnable HOME = () -> {
        AppMain.game.onGoToMenu();
    };

    public static final Runnable OPTIONS = () -> {
        AppMain.game.onGoToOptions();
    };

    public static final Runnable LINKS = () -> {
        AppMain.game.onGoToLinks();
    };

    public static final Runnable GITHUB = () -> {
        Links.openURL(Links.GITHUB);
    };

    public static final Runnable INSTAGRAM = () -> {
        Links.openURL(Links.INSTAGRAM);
    };

    public static final Runnable NEXT_LAN = () -> {
        AppMain.game.onLanguageChange(true);
    };

    public static final Runnable PREV_LAN = () -> {
        AppMain.game.onLanguageChange(false);
    };
}
