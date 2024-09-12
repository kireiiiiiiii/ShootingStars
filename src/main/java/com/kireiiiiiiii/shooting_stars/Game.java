/*
 * Author: Matěj Šťastný
 * Date created: 6/13/2024
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

package com.kireiiiiiiii.shooting_stars;

import java.awt.event.*;
import java.io.IOException;
import java.util.Arrays;

import com.kireiiiiiiii.shooting_stars.common.AdvancedVariable;
import com.kireiiiiiiii.shooting_stars.common.PausableTimer;
import com.kireiiiiiiii.shooting_stars.common.Settings;
import com.kireiiiiiiii.shooting_stars.common.Vec2D;
import com.kireiiiiiiii.shooting_stars.constants.Colors;
import com.kireiiiiiiii.shooting_stars.constants.Files;
import com.kireiiiiiiii.shooting_stars.constants.Fonts;
import com.kireiiiiiiii.shooting_stars.constants.GameDialogue;
import com.kireiiiiiiii.shooting_stars.constants.Logs;
import com.kireiiiiiiii.shooting_stars.constants.Textures;
import com.kireiiiiiiii.shooting_stars.constants.WidgetTags;
import com.kireiiiiiiii.shooting_stars.tools.ScreenUtil;
import com.kireiiiiiiii.shooting_stars.ui.GPanel;
import com.kireiiiiiiii.shooting_stars.ui.Renderable;
import com.kireiiiiiiii.shooting_stars.ui.elements.Backround;
import com.kireiiiiiiii.shooting_stars.ui.elements.game_panel_elements.GameOverScreen;
import com.kireiiiiiiii.shooting_stars.ui.elements.game_panel_elements.HomeButton;
import com.kireiiiiiiii.shooting_stars.ui.elements.game_panel_elements.PauseScreen;
import com.kireiiiiiiii.shooting_stars.ui.elements.game_panel_elements.ScoreBoard;
import com.kireiiiiiiii.shooting_stars.ui.elements.game_panel_elements.ScoreWidget;
import com.kireiiiiiiii.shooting_stars.ui.elements.game_panel_elements.StarWidget;
import com.kireiiiiiiii.shooting_stars.ui.elements.game_panel_elements.TimerWidget;
import com.kireiiiiiiii.shooting_stars.ui.elements.game_panel_elements.TopscoreWidget;
import com.kireiiiiiiii.shooting_stars.ui.elements.menu_panel_elements.MenuButton;
import com.kireiiiiiiii.shooting_stars.ui.elements.menu_panel_elements.MenuScreen;
import com.kireiiiiiiii.shooting_stars.ui.elements.menu_panel_elements.PopUpPanelWindget;
import com.kireiiiiiiii.shooting_stars.ui.elements.menu_panel_elements.links_panel.GithubLink;
import com.kireiiiiiiii.shooting_stars.ui.elements.menu_panel_elements.links_panel.InstagramLink;
import com.kireiiiiiiii.shooting_stars.ui.elements.menu_panel_elements.settings_panel.ChangeButton;
import com.kireiiiiiiii.shooting_stars.ui.elements.menu_panel_elements.settings_panel.LanguageTitle;

/**
 * Main game object.
 * 
 */
public class Game {

    /////////////////
    // Constatns
    ////////////////

    private static final int GAME_LENGHT = 20;
    private static final int DEFAULT_TARGET_RADIUS = 20;
    private static final int TARGET_SCORE = 10;
    private static final int FPS = 60;
    public final int WINDOW_PADDING = 10;

    /////////////////
    // Variables
    ////////////////

    private GPanel gpanel;
    private AdvancedVariable<Integer> topScore;

    /////////////////
    // Game variables
    ////////////////

    private PausableTimer timer;
    private int timeRemaining;
    private int targetRadius;
    private int score;

    /////////////////
    // Constructor
    ////////////////

    public Game() {
        // ---- Setup the GPanel ----
        double[] windowSize = ScreenUtil.getAppWindowSize();
        this.gpanel = new GPanel(FPS, (int) windowSize[0], (int) windowSize[1], false, GameDialogue.appName);
        gpanel.getAppFrame().setBackground(Colors.BACKROUND);
        onWidgetSetup();

        // ---- Load the score and options file ----
        onTopscoreFileLoad();
        GameDialogue.initialLanguageSet((int) Settings.getValue("languageIndex"));
        Fonts.setFonts();

        // ---- Display the menu elements ----
        onGoToMenu();
    }

    /////////////////
    // Events
    ////////////////

    public void onWidgetRefresh() {
        // TODO
    }

    // Called when the current JPanel changes from game to menu
    public void onGoToMenu() {
        this.gpanel.hideAllWidgets();
        this.gpanel.showTaggedWidgets(WidgetTags.MAIN_MENU);
    }

    // Called when switching from the menu panel
    public void onGameStart() {
        // ---- Log & variable setup ----
        Logs.log(Logs.GAME_START);
        this.score = 0;
        this.targetRadius = DEFAULT_TARGET_RADIUS;
        // ----Set widget values ----
        for (TopscoreWidget w : this.gpanel.getWidgetsByClass(TopscoreWidget.class)) {
            w.setTopscore(this.topScore.get());
        }
        for (ScoreWidget w : this.gpanel.getWidgetsByClass(ScoreWidget.class)) {
            w.setScore(this.score);
        }
        for (StarWidget w : this.gpanel.getWidgetsByClass(StarWidget.class)) {
            w.setRadius(this.targetRadius);
        }
        // ---- Start the timer, and reset the target ----
        initializeTimer();
        onTargetClicked(true);
    }

    // Called on restart
    public void onGameRestart() {
        // ---- Log & variable setup ----
        Logs.log(Logs.GAME_RESTART);
        this.score = 0;
        // ----Set widget values ----
        for (TopscoreWidget w : this.gpanel.getWidgetsByClass(TopscoreWidget.class)) {
            w.setTopscore(this.topScore.get());
        }
        for (ScoreWidget w : this.gpanel.getWidgetsByClass(ScoreWidget.class)) {
            w.setScore(this.score);
        }
        // ---- Start the timer, and reset the target ----
        this.timer.forceStop();
        initializeTimer();
        onTargetClicked(true);
    }

    public void onGamePause() {
        // ---- Log ----
        Logs.log(Logs.GAME_PAUSE);
        // ----Set widget values ----
        this.gpanel.hideAllWidgets();
        this.gpanel.showTaggedWidgets(WidgetTags.PAUSE);
        // ---- Timer ----
        this.timer.pause();
    }

    public void onGameResumed() {
        // ---- Log ----
        Logs.log(Logs.GAME_RESUMED);
        // ----Set widget values ----
        this.gpanel.hideAllWidgets();
        this.gpanel.showTaggedWidgets(WidgetTags.GAME);
        // ---- Timer ----
        this.timer.resume();
    }

    public void onGameEnd() {
        // ---- Log ----
        Logs.log(Logs.GAME_OVER);
        // ----Set widget values ----
        this.gpanel.hideAllWidgets();
        this.gpanel.showTaggedWidgets(WidgetTags.GAME_OVER);
        for (ScoreBoard w : this.gpanel.getWidgetsByClass(ScoreBoard.class)) {
            w.setScore(this.score);
            w.setTopScore(this.topScore.get());
        }
        // ---- Timer ----
        this.timer.forceStop();
        // ---- Check for new Topscore ----
        if (this.score > this.topScore.get()) {
            this.topScore.set(this.score);
            onTopscoreFileSave();
        }
        onTopscoreFileLoad();
    }

    public void onTimerIteration() {
        // ---- Log ----
        Logs.log(Logs.TIMER_INTEARION);
        // ----Set widget values ----
        for (TimerWidget w : this.gpanel.getWidgetsByClass(TimerWidget.class)) {
            w.setTime(timeRemaining);
        }
        this.timeRemaining--;
    }

    public void onTargetClicked(boolean init) {
        // ---- Calculate next position ----
        int maxX = this.gpanel.getWidth() - this.targetRadius * 2 - WINDOW_PADDING * 2;
        int maxY = this.gpanel.getHeight() - this.targetRadius * 2 - WINDOW_PADDING * 2;
        Vec2D newPos = new Vec2D();
        newPos.randomize(maxX, maxY);

        int x = newPos.getIntX();
        int y = newPos.getIntY();
        int[] pos = { x, y };

        // ----Set widget values ----
        for (StarWidget w : this.gpanel.getWidgetsByClass(StarWidget.class)) {
            w.setLocation(pos);
        }

        // ---- Update the score and log the click, if not ititial execution ----
        if (!init) {
            Logs.log(Logs.TAGRET_HIT);
            this.score += TARGET_SCORE;
            for (ScoreWidget w : this.gpanel.getWidgetsByClass(ScoreWidget.class)) {
                w.setScore(this.score);
            }
        }
    }

    public void onTargetMisclicked() {
        // ---- Log ----
        Logs.log(Logs.TAGRET_NOT_HIT);
        // ---- Update ----
        this.score = Math.max(0, this.score - TARGET_SCORE);
        for (ScoreWidget w : this.gpanel.getWidgetsByClass(ScoreWidget.class)) {
            w.setScore(this.score);
        }
    }

    public void onTopscoreFileLoad() {
        // ---- Log ----
        Logs.log(Logs.TOPSCORE_FILE_LOAD);
        // ---- Load topscore ----
        this.topScore = new AdvancedVariable<Integer>(Files.TOP_SCORE_FILE);
        try {
            this.topScore.loadFromFile(Integer.class);
        } catch (IOException e) {
            this.topScore.set(0);
        }
        // --- File empty ----
        if (this.topScore.get() == null) {
            this.topScore.set(0);
        }
    }

    public void onTopscoreFileSave() {
        // ---- Log ----
        Logs.log(Logs.TOPSCORE_FILE_SAVED);
        // --- Save file ----
        try {
            this.topScore.save();
        } catch (IOException e) {
            System.out.println("FATAL - Could not save Topscore file");
        }
    }

    public void onLanguageChange(boolean next) {
        // ---- Change dialogues ----
        if (next) {
            GameDialogue.setNextLanguage();
        } else {
            GameDialogue.setPreviousLanguage();
        }
        // ---- Change fonts ----
        Fonts.setFonts();
        // ---- Set app window title ----
        this.gpanel.setName(GameDialogue.appName);
        // ---- Log ----
        Logs.log(Logs.LANGUAGE_SET);
        // ---- Save settings----
        Settings.save();
    }

    public void onWidgetSetup() {

        // MENU MAIN
        int[] size = { this.gpanel.getWidth(), this.gpanel.getHeight() };
        int[] linksPos = { this.gpanel.getWidth() - 90, this.gpanel.getHeight() - 110 };
        int[] settingsPos = { linksPos[0] - 100, linksPos[1] };
        int[] igBtnPos = { this.gpanel.getWidth() / 2 - 300, this.gpanel.getHeight() / 2 - 50 };
        int[] gitBtnPos = { this.gpanel.getWidth() / 2 + 130, this.gpanel.getHeight() / 2 - 50 };
        int[] langFieldPos = { this.gpanel.getWidth() / 2 - LanguageTitle.size[0] / 2,
                this.gpanel.getHeight() / 2 - LanguageTitle.size[1] / 2 };
        int[] leftB = { langFieldPos[0] - ChangeButton.SIZE[0] - 20, langFieldPos[1] };
        int[] rightB = { langFieldPos[0] + LanguageTitle.size[0] + 20,
                langFieldPos[1] };
        // GAME MAIN
        int[] scorePos = { 20, 20 };
        int[] timePos = { 20, 80 };
        int[] topscorePos = { 20, 140 };
        // PAUSE SCREEN
        int[] homeBtnPosPAUSE = { 20, 20 };
        // GAME OVER SCREEN
        int[] scoreBoardPosGO = { 20, this.gpanel.getHeight() - 240 };
        // ------------------------------------------------------------------------------------------------

        this.gpanel.add(Arrays.asList(
                (Renderable) new Backround(size),
                (Renderable) new MenuScreen(size),
                (Renderable) new MenuButton(linksPos, Textures.LINK_ICON),
                (Renderable) new MenuButton(settingsPos, Textures.SETTINGS_ICON),

                // OPTIONS & LINKS PANEL
                (Renderable) new PopUpPanelWindget(size),
                (Renderable) new LanguageTitle(langFieldPos),
                (Renderable) new ChangeButton(leftB, true),
                (Renderable) new ChangeButton(rightB, false),
                (Renderable) new InstagramLink(igBtnPos),
                (Renderable) new GithubLink(gitBtnPos),

                // GAME MAIN
                (Renderable) new TimerWidget(timePos),
                (Renderable) new ScoreWidget(scorePos),
                (Renderable) new TopscoreWidget(topscorePos),
                (Renderable) new StarWidget(),

                // PAUSE SCREEN
                (Renderable) new HomeButton(homeBtnPosPAUSE),
                (Renderable) new PauseScreen(size),

                // GAME OVER SCREEN
                (Renderable) new ScoreBoard(scoreBoardPosGO),
                (Renderable) new GameOverScreen(size)));
    }

    /////////////////
    // Mouse events override methods
    ////////////////

    public void onMouseClicked(MouseEvent e) {

    }

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
        // TODO Get all interactables, filter clicked and visibility and sort
        // TODO by z layer
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    /////////////////
    // Key events override methods
    ////////////////

    public void keyPressed(KeyEvent e) {
        // TODO Check if can be pressed
    }

    public void keyReleased(KeyEvent e) {

    }

    public void keyTyped(KeyEvent e) {

    }

    /////////////////
    // Timer methods
    ////////////////

    /**
     * This method initiliazes the timer. It first sets the time left to the deafult
     * game lenght, and than it starts a timer.
     * </p>
     * On each iteration of the timer, the time left variable is changed, and the
     * time widget on the game panel is updated.
     * </p>
     * When the timer is finished, the event method {@code onGameFinished} will
     * excecute.
     * 
     */
    private void initializeTimer() {
        if (this.timer != null) {
            timer.forceStop();
        }

        this.timeRemaining = GAME_LENGHT;

        Runnable onFinished = () -> {
            onGameEnd();
        };
        Runnable everyRun = () -> {
            onTimerIteration();
        };
        this.timer = new PausableTimer(1000, GAME_LENGHT + 1, onFinished, everyRun);
        timer.start();
    }

}
