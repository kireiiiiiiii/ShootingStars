/*
 * Author: Matěj Šťastný
 * Date created: 5/16/2024
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

package com.example.Interface.Buttons;

import java.awt.*;
import javax.sound.sampled.*;
import com.example.Tools.FontUtil;
import java.awt.event.MouseEvent;
import java.io.File;

/**
 * A custom {@code JPanel} buton with types determined by an enum class
 * {@code ButtonType}. Has hover on functions, and sound functions.
 * 
 */
public class CustomButton {

    /////////////////
    // Fields
    ////////////////

    private int xPos;
    private int yPos;
    private int width;
    private int height;
    private String name;
    private String label;
    private boolean isHovered;
    private boolean hasLabel;
    private boolean hasHoverSound;
    private Font labelFont;
    private LabelPoisiton labelPos;
    private ButtonType type;
    private Color primaryColor;
    private Color secondaryColor;
    private File hoveredSound;

    /////////////////
    // Contructors
    ////////////////

    /**
     * Deafult parameterized contructor.
     * 
     * @param name      - name of the button, is not used anywhere, it's to identify
     *                  the button.
     * @param x         - buttons {@code X} position on the {@code JPanel}.
     * @param y         - buttons {@code Y} position on the {@code JPanel}.
     * @param width     - width of the button.
     * @param height    - height of the button.
     * @param type      - enum object of the type of the button.
     * @param primary   - primary {@code Color} of the button, also used as
     *                  secondary hover {@code Color}.
     * @param secondary - secondary {@code Color} of the button, also used as
     *                  primary hover {@code Color}.
     */
    public CustomButton(String name, int x, int y, int width, int height, ButtonType type, Color primary,
            Color secondary) {
        this.xPos = x;
        this.yPos = y;
        this.width = width;
        this.height = height;
        this.name = name;
        this.type = type;
        this.primaryColor = primary;
        this.secondaryColor = secondary;

    }

    /////////////////
    // Paint method
    ////////////////

    /**
     * Paints the button on the {@code JFrame}, usually intended to call by the
     * {@code JPanel}s {@code paintComponent} method.
     * 
     * @param g2 - {@code Graphics2D} object of the graphics that the button is
     *           being painted on.
     */
    public void updateButton(Graphics2D g2) {

        Color primary = this.isHovered ? this.secondaryColor : this.primaryColor;
        Color secondary = this.isHovered ? this.primaryColor : this.secondaryColor;

        switch (this.type) {
            case MENU:
                g2.setColor(primary);
                g2.fillRoundRect(this.xPos, this.yPos, this.width, this.height, 20, 20);
                g2.setColor(secondary);
                g2.drawRoundRect(this.xPos, this.yPos, this.width, this.height, 20, 20);
                break;

            default:
                g2.setColor(primary);
                g2.fillRect(this.xPos, this.yPos, this.width, this.height);
                g2.setColor(secondary);
                g2.drawRect(this.xPos, this.yPos, this.width, this.height);
                break;
        }

        if (this.hasLabel) {
            Color textColor = this.isHovered ? this.primaryColor : this.secondaryColor;
            g2.setColor(textColor);
            g2.setFont(this.labelFont);
            FontMetrics labelFontMetrics = g2.getFontMetrics();
            int[] textPos = new int[2];

            switch (this.labelPos) {
                case CENTER:
                    textPos = FontUtil.getCenteredPos(this.width, this.height, labelFontMetrics, this.label);
                    break;

                // TODO : Add all label positions

                default:
                    textPos[0] = 0;
                    textPos[1] = 0;
                    break;
            }
            textPos[0] += this.xPos;
            textPos[1] += this.yPos + 5;
            // the 5 is for a miner offset error when counting the y pos of the labe;

            g2.drawString(this.label, textPos[0], textPos[1]);
        }

    }

    /////////////////
    // Accessor methods
    ////////////////

    /**
     * Accesor method for the name of the button.
     * 
     * @return the name of the button.
     */
    public String getName() {
        return this.name;
    }

    public boolean getHovered() {
        return this.isHovered;
    }

    /**
     * Accesor method for the label, if the button doesn't have one initialized,
     * returns {@code null}.
     * 
     * @return text of the label, or {@code null} if not initialized.
     */
    public String getLabel() {
        return this.hasLabel ? null : this.label;
    }

    /**
     * Accesor method for the position of the label, if the button doesn't have ine
     * initialized, returnes {@code null}.
     * 
     * @return a {@code LabelPosition} enum value.
     */
    public LabelPoisiton getLabelPosition() {
        return this.hasLabel ? null : this.labelPos;
    }

    /////////////////
    // Modifier methods
    ////////////////

    /**
     * Sets a new button name.
     * 
     * @param name - new name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the isHovered value of the button. If there is a hover sound
     * initialized, and the boolean is changing from false to true plays the sound.
     * 
     * @param isHovered - new value.
     */
    public void setHovered(boolean isHovered) {
        boolean previousHoverValue = this.isHovered;
        this.isHovered = isHovered;
        if (previousHoverValue == false && this.isHovered == true) {
            playHovered();
        }
    }

    /**
     * Sets the label field in the button, if the button doesn't have a label,
     * doesn't do anything.
     * 
     * @param label - new label.
     */
    public void setLabel(String label) {
        if (hasLabel)
            this.label = label;
    }

    /**
     * Sets the new position of the button label.
     * 
     * @param labelPos - new label position.
     */
    public void setLabelPos(LabelPoisiton labelPos) {
        if (hasLabel)
            this.labelPos = labelPos;
    }

    /////////////////
    // Other public methods
    ////////////////

    /**
     * Checks, if the button is being hovered by the parameter {@code X} and
     * {@code Y} values.
     * 
     * @param mouseX - {@code X} value of the checked position.
     * @param mouseY - {@code Y} value of the checked position.
     * @return boolean value of is being hover on.
     */
    public boolean isHovered(int mouseX, int mouseY) {
        return mouseX >= xPos && mouseX <= (xPos + width) && mouseY >= yPos && mouseY <= (yPos + height);
    }

    /**
     * Checks, if a button position was clicked from a {@code MouseEvent} object.
     * 
     * @param mEvent - {@code MouseEvent} object of the target click.
     * @return a {@code boolean} value if the button was clicked.
     */
    public boolean isClicked(MouseEvent mEvent) {
        int mouseX = mEvent.getX();
        int mouseY = mEvent.getY();

        boolean withinXBounds = mouseX >= xPos && mouseX <= (xPos + width);
        boolean withinYBounds = mouseY >= yPos && mouseY <= (yPos + height);

        return withinXBounds && withinYBounds;
    }

    /**
     * Centers the button in the field given, usually the dimensons of a
     * {@code JPanel}.
     * 
     * @param panelWidth  - width of the target field.
     * @param panelHeight - height of the target field.
     */
    public void centerButton(int panelWidth, int panelHeight) {
        int[] centeredPos = getCentered(panelWidth, panelHeight);
        this.xPos = centeredPos[0];
        this.yPos = centeredPos[1];
    }

    /**
     * Adds a text label to the button with a given Font, label text and it's
     * position determined by an enum.
     * 
     * @param label    - label text
     * @param font     - font of the text
     * @param labelPos - position of the label determined by an enum class
     *                 {@code labelPos}.
     */
    public void addLabel(String label, Font font, LabelPoisiton labelPos) {
        this.hasLabel = true;
        this.labelFont = font;
        this.label = label;
        this.labelPos = labelPos;
    }

    /**
     * Removes the label from the button, and resets all fields used to make a
     * label.
     * 
     */
    public void removeLabel() {
        this.hasLabel = false;
        this.labelFont = null;
        this.label = null;
        this.labelPos = null;
    }

    /**
     * Adds a new hover sound to the button, if the file is not a ".wav" type,
     * doesn't do anything.
     * 
     * @param soundFile - target sound file.
     */
    public void addHoverSound(File soundFile) {
        if (!soundFile.getAbsolutePath().endsWith(".wav")) {
            return;
        }
        this.hasHoverSound = true;
        this.hoveredSound = soundFile;
    }

    /**
     * Adds a new hover sound to the button, if the file is not a ".wav" type,
     * doesn't do anything.
     * 
     * @param path - path of the sound file.
     */
    public void addHoverSound(String path) {
        addHoverSound(new File(path));
    }

    /**
     * Removes the hover sound of the button.
     * 
     */
    public void removeHoverSound() {
        this.hasHoverSound = false;
        this.hoveredSound = null;
    }

    /////////////////
    // Private methods
    ////////////////

    /**
     * Gets the centered position of the button in a field determined by parametes.
     * 
     * @param panelWidth  - width of the field.
     * @param panelHeight - height of the field.
     * @return an integer array size 2 with index 0 being x position, and index 1
     *         being y position.
     */
    private int[] getCentered(int panelWidth, int panelHeight) {
        int x = (panelWidth - this.width) / 2;
        int y = (panelHeight - this.height) / 2;
        int[] pos = { x, y };
        return pos;
    }

    /**
     * Plays the saved hovered sound on a new thread, if no hoversound initialized,
     * does nothing
     * 
     */
    private void playHovered() {
        if (!this.hasHoverSound) {
            return;
        }

        Thread soundThread = new Thread(() -> {
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(this.hoveredSound);
                AudioFormat format = audioInputStream.getFormat();
                DataLine.Info info = new DataLine.Info(Clip.class, format);
                Clip clip = (Clip) AudioSystem.getLine(info);

                clip.open(audioInputStream);
                clip.start();

                // Delay for a short period to allow the sound to play
                Thread.sleep(1000); // Adjust this value as needed

                clip.close();
                audioInputStream.close();
            } catch (Exception e) {
                System.out.println("Sound failed");
            }
        });
        soundThread.start();
    }

}
