package net.liplum.state.gui;

import net.liplum.Keys;
import net.liplum.R;
import net.liplum.infos.LoadInfo;
import net.liplum.inputhandler.InputHandlerBase;
import net.liplum.io.FileChooser;
import net.liplum.Main;
import net.liplum.piano.Piano;
import net.liplum.state.GUIState;
import net.liplum.state.GUIStateMachine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainState extends GUIState {
    public Piano piano;
    public FileChooser musicScore;

    public MainState(GUIStateMachine StateMachine, LoadInfo loadInfo) {
        super(StateMachine);
        piano = loadInfo.piano;
        musicScore = loadInfo.musicScore;
    }

    private static void remapKeyEvent(KeyEvent e) {
        for (int i = 0; i < Keys.KeyCodes.length; ++i)
            if (e.getKeyCode() == Keys.RemapKeyCode[i]) {
                e.setKeyCode(Keys.KeyCodes[i]);
            }
    }

    @Override
    public void render(Graphics g) {
        g.setColor(R.BackgroundColor);
        g.fillRect(0, 0, Main.winWidth, Main.winHeight);
        piano.render(g);
    }

    @Override
    public void execute() {

    }

    @Override
    public void enter() {
        System.out.println("Enter Main state.");
        input = new Input();
    }

    @Override
    public void exit() {

    }

    @Override
    public void update(long delta) {
        if (piano != null)
            piano.update(delta);
    }

    private class Input extends InputHandlerBase {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (piano != null)
                piano.mouseClicked(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            remapKeyEvent(e);
            if (piano != null)
                piano.keyPressed(e);
        }
    }
}
