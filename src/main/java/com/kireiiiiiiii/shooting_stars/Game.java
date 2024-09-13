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
import java.util.ArrayList;

import com.kireiiiiiiii.shooting_stars.common.AdvancedVariable;
import com.kireiiiiiiii.shooting_stars.common.GPanel;
import com.kireiiiiiiii.shooting_stars.common.PausableTimer;
import com.kireiiiiiiii.shooting_stars.common.Settings;
import com.kireiiiiiiii.shooting_stars.common.Vec2D;
import com.kireiiiiiiii.shooting_stars.constants.Colors;
import com.kireiiiiiiii.shooting_stars.constants.Files;
import com.kireiiiiiiii.shooting_stars.constants.Fonts;
import com.kireiiiiiiii.shooting_stars.constants.GameDialogue;
import com.kireiiiiiiii.shooting_stars.constants.Interact;
import com.kireiiiiiiii.shooting_stars.constants.Keybinds;
import com.kireiiiiiiii.shooting_stars.constants.Logs;
import com.kireiiiiiiii.shooting_stars.constants.Textures;
import com.kireiiiiiiii.shooting_stars.constants.WidgetTags;
import com.kireiiiiiiii.shooting_stars.interfaces.Interactable;
import com.kireiiiiiiii.shooting_stars.interfaces.Renderable;
import com.kireiiiiiiii.shooting_stars.tools.ScreenUtil;
import com.kireiiiiiiii.shooting_stars.ui.Backround;
import com.kireiiiiiiii.shooting_stars.ui.game.GameOverScreen;
import com.kireiiiiiiii.shooting_stars.ui.game.HomeButton;
import com.kireiiiiiiii.shooting_stars.ui.game.PauseScreen;
import com.kireiiiiiiii.shooting_stars.ui.game.ScoreBoard;
import com.kireiiiiiiii.shooting_stars.ui.game.ScoreWidget;
import com.kireiiiiiiii.shooting_stars.ui.game.StarWidget;
import com.kireiiiiiiii.shooting_stars.ui.game.TimerWidget;
import com.kireiiiiiiii.shooting_stars.ui.game.TopscoreWidget;
import com.kireiiiiiiii.shooting_stars.ui.menu.MenuButton;
import com.kireiiiiiiii.shooting_stars.ui.menu.MenuScreen;
import com.kireiiiiiiii.shooting_stars.ui.menu.PopUpPanelWindget;
import com.kireiiiiiiii.shooting_stars.ui.menu.links_panel.GithubLink;
import com.kireiiiiiiii.shooting_stars.ui.menu.links_panel.InstagramLink;
import com.kireiiiiiiii.shooting_stars.ui.menu.settings_panel.ChangeButton;
import com.kireiiiiiiii.shooting_stars.ui.menu.settings_panel.LanguageTitle;

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
    private boolean paused;

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

    // Called when the current JPanel changes from game to menu
    public void onGoToMenu() {
        Keybinds.setEnabledAll(false);
        Keybinds.setEnabled(true, Keybinds.START_KEY);

        for (MenuButton w : this.gpanel.getWidgetsByClass(MenuButton.class)) {
            w.setInteract(true);
        }
        this.gpanel.hideAllWidgets();
        this.gpanel.showTaggedWidgets(WidgetTags.MAIN_MENU);
    }

    public void onGoToOptions() {
        for (MenuButton w : this.gpanel.getWidgetsByClass(MenuButton.class)) {
            w.setInteract(false);
        }
        this.gpanel.showTaggedWidgets(WidgetTags.OPTIONS);
    }

    public void onGoToLinks() {
        for (MenuButton w : this.gpanel.getWidgetsByClass(MenuButton.class)) {
            w.setInteract(false);
        }
        this.gpanel.showTaggedWidgets(WidgetTags.LINKS);
    }

    // Called when switching from the menu panel
    public void onGameStart() {
        // ---- Log & variable setup ----
        Logs.log(Logs.GAME_START);
        this.score = 0;
        this.paused = false;
        this.targetRadius = DEFAULT_TARGET_RADIUS;
        // ----Set keybinds ----
        Keybinds.setEnabledAll(false);
        Keybinds.setEnabled(true, Keybinds.PAUSE_KEY);
        Keybinds.setEnabled(true, Keybinds.RESTART_KEY);
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
        this.gpanel.hideAllWidgets();
        this.gpanel.showTaggedWidgets(WidgetTags.GAME);
        // ---- Start the timer, and reset the target ----
        initializeTimer();
        onTargetClicked(true);
    }

    // Called on restart
    public void onGameRestart() {
        // ---- Log & variable setup ----
        Logs.log(Logs.GAME_RESTART);
        this.score = 0;
        // ----Set keybinds ----
        Keybinds.setEnabledAll(false);
        Keybinds.setEnabled(true, Keybinds.PAUSE_KEY);
        Keybinds.setEnabled(true, Keybinds.RESTART_KEY);
        // ----Set widget values ----
        for (TopscoreWidget w : this.gpanel.getWidgetsByClass(TopscoreWidget.class)) {
            w.setTopscore(this.topScore.get());
        }
        for (ScoreWidget w : this.gpanel.getWidgetsByClass(ScoreWidget.class)) {
            w.setScore(this.score);
        }
        this.gpanel.hideAllWidgets();
        this.gpanel.showTaggedWidgets(WidgetTags.GAME);
        // ---- Start the timer, and reset the target ----
        this.timer.forceStop();
        initializeTimer();
        onTargetClicked(true);
    }

    public void onTogglePause() {
        if (paused) {
            onGameResumed();
        } else {
            onGamePause();
        }
        this.paused = !this.paused;
    }

    public void onGamePause() {
        // ---- Log ----
        Logs.log(Logs.GAME_PAUSE);
        // ----Set widget values ----
        Keybinds.setEnabled(false, Keybinds.RESTART_KEY);
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
        Keybinds.setEnabled(true, Keybinds.RESTART_KEY);
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
        Keybinds.setEnabled(false, Keybinds.PAUSE_KEY);
        // ----Set widget values ----
        this.gpanel.hideAllWidgets();
        this.gpanel.showTaggedWidgets(WidgetTags.GAME_OVER);
        for (ScoreBoard w : this.gpanel.getWidgetsByClass(ScoreBoard.class)) {
            w.setScore(this.score);
            w.setTopScore(this.topScore.get());
        }
        this.gpanel.hideAllWidgets();
        this.gpanel.showTaggedWidgets(WidgetTags.GAME_OVER);
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
            System.out.println(this.score);
            this.score += TARGET_SCORE * 2;
            System.out.println(this.score);
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

    /**
     * Method to determine if a interactable was clicked. This is achieved by
     * sorting out the not visible or not interacted widgets, and than sorting then
     * based on their Z-Index. The one on top is the one the user interacted with.
     * 
     * @param e - {@code MouseEvent} of the click
     */
    public void onMouseClicked(MouseEvent e) {
        ArrayList<Renderable> widgets = this.gpanel.getInteractables();
        // Clear not visible widgets
        for (int i = 0; i < widgets.size(); i++) {
            Renderable r = widgets.get(i);
            if (!r.isVisible()) {
                widgets.remove(i);
                i--;
            }
        }
        if (widgets.size() <= 0) {
            return;
        }

        // Sort based on Z-Index - user clicked the one displayed on top right? :3
        int size = widgets.size();
        boolean swapped;
        for (int i = 0; i < size - 1; i++) {
            swapped = false;
            for (int j = 0; j < size - i - 1; j++) {
                if (widgets.get(j).getZIndex() > widgets.get(j + 1).getZIndex()) {
                    // Swap the elements
                    Renderable temp = widgets.get(j);
                    widgets.set(j, widgets.get(j + 1));
                    widgets.set(j + 1, temp);
                    swapped = true;
                }
            }
            if (!swapped) {
                break;
            }
        }

        // Convert to Interactable objects, and filter out all not clicked
        ArrayList<Interactable> buttons = new ArrayList<Interactable>();
        for (int i = 0; i < widgets.size(); i++) {
            Interactable btn = (Interactable) widgets.get(i);
            if (btn.wasInteracted(e)) {
                buttons.add(btn);
            }
        }
        if (buttons.size() > 0) {
            Interactable interacted = buttons.get(0);
            interacted.getInteraction().run();
        }

        // Update the score
        if (!paused) {
            this.score -= this.score <= 0 ? 0 : TARGET_SCORE;
            for (ScoreWidget w : this.gpanel.getWidgetsByClass(ScoreWidget.class)) {
                w.setScore(this.score);
            }
        }

    }

    public void onWidgetSetup() {

        // Screen dimension for clarity
        int width = this.gpanel.getWidth();
        int height = this.gpanel.getHeight();

        // GLOBAL
        int[] appSize = { width, height };
        // MAIN MENU
        int[] menu_linkBtn = { width - 90, height - 110 };
        int[] menu_optionsBtn = { menu_linkBtn[0] - 100, menu_linkBtn[1] };
        // OPTIONS
        int[] options_instagramBtn = { width / 2 - 300, height / 2 - 50 };
        int[] options_gitBtn = { width / 2 + 130, height / 2 - 50 };
        int[] options_currlan = { width / 2 - LanguageTitle.size[0] / 2, height / 2 - LanguageTitle.size[1] / 2 };
        int[] options_prevlanBtn = { options_currlan[0] - ChangeButton.SIZE[0] - 20, options_currlan[1] };
        int[] options_nextlanBtn = { options_currlan[0] + LanguageTitle.size[0] + 20, options_currlan[1] };
        // GAME
        int[] game_score = { 20, 20 };
        int[] game_time = { 20, 80 };
        int[] game_topscore = { 20, 140 };
        // PAUSE
        int[] pause_menuBtn = { 20, 20 };
        // GAMEOVER
        int[] gameov_scoreboard = { 20, height - 240 };
        // ------------------------------------------------------------------------------------------------

        this.gpanel.add(Arrays.asList(

                // MAIN MENU
                (Renderable) new Backround(appSize),
                (Renderable) new MenuScreen(appSize),
                (Renderable) new MenuButton(menu_linkBtn, Textures.LINK_ICON, Interact.LINKS), // LINKS
                (Renderable) new MenuButton(menu_optionsBtn, Textures.SETTINGS_ICON, Interact.OPTIONS), // OPTIONS

                // OPTIONS & LINKS PANEL
                (Renderable) new PopUpPanelWindget(appSize),
                (Renderable) new LanguageTitle(options_currlan),
                (Renderable) new ChangeButton(options_prevlanBtn, true),
                (Renderable) new ChangeButton(options_nextlanBtn, false),
                (Renderable) new InstagramLink(options_instagramBtn),
                (Renderable) new GithubLink(options_gitBtn),

                // GAME MAIN
                (Renderable) new TimerWidget(game_time),
                (Renderable) new ScoreWidget(game_score),
                (Renderable) new TopscoreWidget(game_topscore),
                (Renderable) new StarWidget(),

                // PAUSE SCREEN
                (Renderable) new HomeButton(pause_menuBtn),
                (Renderable) new PauseScreen(appSize),

                // GAME OVER SCREEN
                (Renderable) new ScoreBoard(gameov_scoreboard),
                (Renderable) new GameOverScreen(appSize)));
    }

    /////////////////
    // Mouse events override methods
    ////////////////

    public void mouseDragged(MouseEvent e) {

    }

    public void mouseMoved(MouseEvent e) {

    }

    public void mouseClicked(MouseEvent e) {

    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {
        onMouseClicked(e);
    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }

    /////////////////
    // Key events override methods
    ////////////////

    public void keyPressed(KeyEvent e) {
        Keybinds.interact(e);
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
