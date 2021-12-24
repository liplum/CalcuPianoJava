package net.liplum.state.gui;

import net.liplum.animation.Animation;
import net.liplum.inputhandler.InputHandlerBase;
import net.liplum.io.FileChooser;
import net.liplum.main.Main;
import net.liplum.main.Resources;
import net.liplum.main.Resources.ImageList;
import net.liplum.piano.CanExecuteTask;
import net.liplum.piano.Piano;
import net.liplum.piano.Piano.DropDownMenuField;
import net.liplum.piano.Piano.PianoKeyField;
import net.liplum.piano.Piano.SystemInfoField;
import net.liplum.state.GUIState;
import net.liplum.state.GUIStateMachine;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import static net.liplum.piano.AllPianoKeysTask.*;

public class MainState extends GUIState implements Runnable {

    private static final int MusicNoteCount = 14;
    private static final int[] KeyCodes = {
            KeyEvent.VK_NUMPAD1,
            KeyEvent.VK_NUMPAD2,
            KeyEvent.VK_NUMPAD3,
            KeyEvent.VK_NUMPAD4,
            KeyEvent.VK_NUMPAD5,
            KeyEvent.VK_NUMPAD6,
            KeyEvent.VK_NUMPAD7,

            KeyEvent.VK_NUMPAD8,
            KeyEvent.VK_NUMPAD9,
            KeyEvent.VK_MULTIPLY,
            KeyEvent.VK_ADD,
            KeyEvent.VK_DIVIDE,
            KeyEvent.VK_SUBTRACT,
            KeyEvent.VK_ENTER
    };
    private static int PianoX;
    private static int PianoY;
    private static int PianoKeyWidth;
    private static int PianoKeyHeight;
    private static int PianoKeyDistance;
    private static int windowsWidth;
    private static int windowsHeight;
    private static Color BackgroundColor = new Color(220, 220, 220);
    private static int[] remapKeyCode = {
            KeyEvent.VK_1,
            KeyEvent.VK_2,
            KeyEvent.VK_3,
            KeyEvent.VK_4,
            KeyEvent.VK_5,
            KeyEvent.VK_6,
            KeyEvent.VK_7,

            KeyEvent.VK_8,
            KeyEvent.VK_9,
            KeyEvent.VK_0,
            KeyEvent.VK_EQUALS,
            KeyEvent.VK_BACK_SLASH,
            KeyEvent.VK_MINUS,
            KeyEvent.VK_ENTER

    };
    private static int moreMenuKeyCode = KeyEvent.VK_ESCAPE;
    private Piano piano = null;
    private volatile boolean isDone = false;
    private Thread load;
    private Animation loadAnimation = null;
    private FileChooser MusicScore;

    public MainState(GUIStateMachine StateMachine, Animation a) {
        super(StateMachine);
        this.loadAnimation = a;
    }

    private static final void remapKeyEvent(KeyEvent e) {
        for (int i = 0; i < KeyCodes.length; ++i)
            if (e.getKeyCode() == remapKeyCode[i]) {
                e.setKeyCode(KeyCodes[i]);
            }
    }

    @Override
    public void render(Graphics g) {
        if (!isDone) {
            g.setColor(new Color(0, 0, 0));
            g.fillRect(0, 0, Main.winWidth, Main.winHeight);
            if (loadAnimation != null)
                loadAnimation.render(g);
        } else {
            if (piano != null) {
                g.setColor(BackgroundColor);
                g.fillRect(0, 0, Main.winWidth, Main.winHeight);
                piano.render(g);
            }
        }


    }

    @Override
    public void execute() {
        if (isDone) {
            System.gc();
        }
    }

    @Override
    public void enter() {
        System.out.println("成功进入Main状态");
        input = new Input();
        windowsWidth = Main.winWidth;
        windowsHeight = Main.winHeight;
        load = new Thread(this, "loadResources");
        load.start();
    }

    @Override
    public void exit() {

    }

    @Override
    public void update(long delta) {
        if (!isDone && loadAnimation != null)
            loadAnimation.update(delta);
        if (piano != null)
            piano.update(delta);
    }

    @Override
    public void run() {

        Color Gray = new Color(180, 180, 180, 165);

        MusicScore = new FileChooser(stateMachine.getOwner(), "txt");

////////////////////////////////////////////////////////////////////////////////
//		完成PianoKeyField

        PianoKeyField pianoKeyField = null;

        BufferedImage[] pasters = new BufferedImage[MusicNoteCount];

        CanExecuteTask[] cans = {
                Number1,
                Number2,
                Number3,
                Number4,
                Number5,
                Number6,
                Number7,
                Number8,
                Number9,

                Multiple,
                Add,
                Divide,
                Minus,
                Equal,
        };


        for (int i = 0; i < MusicNoteCount; ++i) {
            pasters[i] = Resources.getImageAt(ImageList.textOf(i));
        }

        PianoKeyHeight = (int) (windowsHeight / 6.5f);//177
        PianoKeyWidth = (int) (PianoKeyHeight * 0.647f);//113

        PianoKeyDistance = (int) (windowsWidth / 80.0f);


        PianoX = (windowsWidth - ((4 * PianoKeyDistance) + (5 * PianoKeyWidth))) / 2;
        PianoY = windowsHeight - ((3 * PianoKeyDistance) + (3 * PianoKeyHeight));


        pianoKeyField = new PianoKeyField(PianoX, PianoY, PianoKeyWidth, PianoKeyHeight, PianoKeyDistance,
                Gray, pasters, cans, KeyCodes);

/////////////////////////////////////////////////////////////////////////////////
//		完成DropDownMenuField

        DropDownMenuField dropDownMenuField = null;

        BufferedImage menuLauncher = Resources.getImageAt(ImageList.MORE);

        BufferedImage[] menuOption = {
                Resources.getImageAt(ImageList.SETTING),
                Resources.getImageAt(ImageList.IMPORT),
                Resources.getImageAt(ImageList.SAVE)
        };

        CanExecuteTask[] menuCans = {
                () -> {
                },
                () -> MusicScore.chooseFile(),
                () -> {
                }
        };

        int optionWidth = PianoKeyWidth;
        int optionHeight = (int) (PianoKeyHeight * (2.5f / 4.0f));


        int menuX = Main.winWidth - PianoKeyWidth;
        int menuY = 0;

        int optionDistance = Main.winHeight / 300;

        dropDownMenuField = new DropDownMenuField(menuLauncher, menuOption,
                menuCans, moreMenuKeyCode, menuX, menuY
                , optionDistance, optionWidth, optionHeight, Gray);

/////////////////////////////////////////////////////////////////////////////////
//		完成SystemInfoField

        SystemInfoField systemInfoField = null;

        systemInfoField = new SystemInfoField(Main.winWidth, PianoKeyHeight / 2);


/////////////////////////////////////////////////////////////////////////////////

        piano = new Piano(systemInfoField, pianoKeyField, dropDownMenuField);

        loadAnimation = null;

        System.gc();

        System.out.println("加载完毕");

        isDone = true;
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
