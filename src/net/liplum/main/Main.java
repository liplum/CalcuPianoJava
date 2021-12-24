package net.liplum.main;

import net.liplum.Launcher.PlumStarEngineLauncher;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static final String TITLE = "CalcuPiano";

    public static PlumStarEngineLauncher GUI;

    public static int ScreenWidth;

    public static int ScreenHeight;

    public static int winWidth;

    public static int winHeight;

    private static JFrame frame;

    public static void main(String[] args) {

//		读取系统样式
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

        // 创建游戏界面
        GUI = new CalcuPiano(winWidth, winHeight);
        frame.add(GUI);

        // 将游戏界面放置于中央
        frame.setLocation((ScreenWidth - winWidth) / 2, (ScreenHeight - winHeight) / 2);

        frame.pack();

    }

    /**
     * 读取屏幕大小
     */
    public static void loadScreenSize() {
        ScreenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        ScreenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
    }

    /**
     * 计算窗口大小
     */
    public static void calculateWindowsSize() {// 测试分辨率：2560x1440
        winWidth = (int) ((ScreenWidth * 0.98f) / 4.0f);// 测试：627
        winHeight = (int) ((ScreenHeight * 4.0f) / 5.0f);// 测试：1152
    }

    public static void loadUIIcon() {
        Resources.loadUIIcon();
    }

    public static void setUIStyle(String style) {
        try {
            UIManager.setLookAndFeel(style);
        } catch (Exception e) {
            System.out.println("无法读取样式！");
            e.printStackTrace();
        }
    }
}