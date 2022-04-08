package net.liplum.piano;

import net.liplum.animation.GradualAnimeFactory;
import net.liplum.controls.Button;
import net.liplum.controls.ButtonGroup;
import net.liplum.controls.IControl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Piano implements IControl {

    private ButtonGroup<Button> pianoKeys;

    private DropDownMenu menu;

    private int upperSidebarWidth, upperSidebarHeight;


    private Color colorBG;

    public Piano(SystemInfoField sf, PianoKeyField f, DropDownMenuField f2) {

        pianoKeys = PianoKeyFactory.newPiano(f.leftUpX, f.leftUpY, f.width, f.height, f.standoffDistance, f.colorUp,
                f.pasters, f.CanExecuteTasks, f.triggerKeyCodes);

        this.colorBG = f2.colorUp;
        this.upperSidebarWidth = sf.upperSidebarWidth;
        this.upperSidebarHeight = sf.upperSidebarHeight;

        int moreKeyX = upperSidebarWidth - f.width;

        menu = new DropDownMenu(f2.launcherKeyCode,
                GradualAnimeFactory.newAnime(4, f2.x, f2.y, f.width, upperSidebarHeight, f2.colorUp,
                        f2.launcher, 0, 0, f.width, upperSidebarHeight),
                moreKeyX, 0, f.width, upperSidebarHeight, true, 0, 0, f2.CanExecuteTasks, f2.colorUp, f2.pasters,
                f2.optionWidth, f2.optionHeight, f2.distance);

    }

    @Override
    public synchronized void render(Graphics g) {
        pianoKeys.render(g);

        g.setColor(colorBG);
        g.fillRect(0, 0, upperSidebarWidth, upperSidebarHeight);


        menu.render(g);

    }

    @Override
    public synchronized void update(long delta) {
        pianoKeys.update(delta);
        menu.update(delta);
    }

    @Override
    public synchronized void mouseClicked(MouseEvent e) {
        pianoKeys.mouseClicked(e);
        menu.mouseClicked(e);
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        pianoKeys.keyPressed(e);
        menu.keyPressed(e);
    }

    public static class PianoKeyField {

        private int leftUpX, leftUpY, width, height, standoffDistance;
        private Color colorUp;
        private BufferedImage[] pasters;
        private IExecuteTask[] CanExecuteTasks;
        private int[] triggerKeyCodes;

        public PianoKeyField(int leftUpX, int leftUpY, int width, int height, int standoffDistance, Color colorUp,
                             BufferedImage[] pasters, IExecuteTask[] canExecuteTasks, int[] triggerKeyCodes) {

            this.leftUpX = leftUpX;
            this.leftUpY = leftUpY;
            this.width = width;
            this.height = height;
            this.standoffDistance = standoffDistance;
            this.colorUp = colorUp;
            this.pasters = pasters;
            this.CanExecuteTasks = canExecuteTasks;
            this.triggerKeyCodes = triggerKeyCodes;
        }
    }

    public static class DropDownMenuField {
        private BufferedImage launcher;
        private BufferedImage[] pasters;
        private IExecuteTask[] CanExecuteTasks;
        private int launcherKeyCode;
        private int x, y, distance;
        private int optionWidth, optionHeight;
        private Color colorUp;

        public DropDownMenuField(
                BufferedImage launcher,
                BufferedImage[] pasters,
                IExecuteTask[] canExecuteTasks,
                int launcherKeyCode,
                int x, int y, int distance,
                int optionWidth, int optionHeight,
                Color colorUp
        ) {

            this.launcher = launcher;
            this.pasters = pasters;
            this.CanExecuteTasks = canExecuteTasks;
            this.launcherKeyCode = launcherKeyCode;
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.optionWidth = optionWidth;
            this.optionHeight = optionHeight;
            this.colorUp = colorUp;
        }

    }

    public static class SystemInfoField {
        private int upperSidebarWidth, upperSidebarHeight;

        public SystemInfoField(int upperSidebarWidth, int upperSidebarHeight) {

            this.upperSidebarWidth = upperSidebarWidth;
            this.upperSidebarHeight = upperSidebarHeight;
        }

    }

}
