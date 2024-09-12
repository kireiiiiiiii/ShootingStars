// /*
// * Author: Matěj Šťastný
// * Date created: 4/23/2024
// * Github link: https://github.com/kireiiiiiiii/ShootingStars
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// *
// */

// package com.kireiiiiiiii.shooting_stars.ui;

// import java.awt.Image;
// import java.awt.Taskbar;
// import javax.swing.*;

// import com.kireiiiiiiii.shooting_stars.constants.Textures;

// /**
// * A template class of what should be implemented in a custom {@code JFrame}.
// * TODO: Make class header
// *
// */
// public class AppFrame extends JFrame {

// /////////////////
// // Fields
// ////////////////

// private String title;

// /////////////////
// // Constructors
// ////////////////

// /**
// * Empty contructor. Calls the deafult parameterized constructor with the
// width
// * and height zero, and the title of an empty {@code String}.
// *
// */
// public AppFrame() {
// this(0, 0, "");
// }

// /**
// * Deafult parameterized contructor. Initializes the size with given values,
// * sets the title with given value, sets resizable to {@code false}, sets the
// * location to the center of the screen and sets the {@code OnCloseOperation}
// to
// * {@code EXIT_ON_CLOSE}.
// *
// * @param windowWidth - width of the {@code JFrame} window.
// * @param windowHeight - height of the {@code JFrame} window.
// * @param title - title of the {@JFrame} window.
// */
// public AppFrame(int windowWidth, int windowHeight, String title) {
// this.title = title;
// setSize(windowWidth, windowHeight);
// setResizable(false);
// setTitle(title);
// setLocationRelativeTo(null);
// setDefaultCloseOperation(EXIT_ON_CLOSE);
// setVisible(true);
// setIcon(Textures.ICON);

// // this.add(new OldGamePanel(this));
// }

// /////////////////
// // Accesor methods
// ////////////////

// /**
// * Returns the title of the {@code JFrame} window.
// *
// */
// public String getTitle() {
// return this.title;
// }

// /////////////////
// // Modifier methods
// ////////////////

// /**
// * Changes the title of the {@code JFrame} window.
// *
// * @param title - new title of the window.
// */
// public void changeTitle(String title) {
// this.title = title;
// setTitle(this.title);
// }

// /**
// * Changes the window icon.
// *
// * @param path - path of the icon
// */
// public void setIcon(Image icon) {
// Taskbar taskbar = Taskbar.getTaskbar();
// taskbar.setIconImage(icon);
// }

// }
