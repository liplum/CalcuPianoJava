package net.liplum;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static final String TITLE = "CalcuPiano";

    public static CalcuPiano GUI;

    public static int ScreenWidth;

    public static int ScreenHeight;

    public static int winWidth;

    public static int winHeight;

    private static JFrame frame;

    public static void main(String[] args) {

        // load th default theme from system
        setUIStyle(UIManager.getSystemLookAndFeelClassName());

        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);

        loadUIIcon();
        frame.setIconImage(Resources.Icon);
//		frame.setCursor(frame.getToolkit().createCustomCursor(Resources.Cursor,
//				new Point(0,0), "Cursor"));

        loadScreenSize();

        calculateWindowsSize();

        // Create the game
        GUI = new CalcuPiano(winWidth, winHeight);
        frame.add(GUI);

        // put the game in the center of the screen
        frame.setLocation((ScreenWidth - winWidth) / 2, (ScreenHeight - winHeight) / 2);

        frame.pack();

    }

    public static void loadScreenSize() {
        ScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        ScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    public static void calculateWindowsSize() {// Assume:2560x1440
        winWidth = (int) ((ScreenWidth * 0.98f) / 4.0f);// Res:627
        winHeight = (int) ((ScreenHeight * 4.0f) / 5.0f);// Res:1152
    }

    public static void loadUIIcon() {
        Resources.loadUIIcon();
    }

    public static void setUIStyle(String style) {
        try {
            UIManager.setLookAndFeel(style);
        } catch (Exception e) {
            System.out.println("Can't load th theme.");
            e.printStackTrace();
        }
    }
}