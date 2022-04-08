package net.liplum.state.gui;

import net.liplum.Keys;
import net.liplum.Main;
import net.liplum.R;
import net.liplum.Resources;
import net.liplum.Resources.ImageList;
import net.liplum.animation.Animation;
import net.liplum.animation.LinerAnimeFactory;
import net.liplum.infos.LoadInfo;
import net.liplum.io.FileChooser;
import net.liplum.piano.IExecuteTask;
import net.liplum.piano.Piano;
import net.liplum.state.GUIState;
import net.liplum.state.GUIStateMachine;

import java.awt.*;
import java.awt.image.BufferedImage;

import static net.liplum.piano.AllPianoKeysTask.*;

public class LoadState extends GUIState {

    private volatile boolean isDone = false;
    private Animation loadAnimation;
    public final LoadInfo loadInfo = new LoadInfo();

    public LoadState(GUIStateMachine StateMachine) {
        super(StateMachine);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, Main.winWidth, Main.winHeight);
        if (loadAnimation != null)
            loadAnimation.render(g);
    }

    @Override
    public void execute() {
        if (isDone) {
            stateMachine.replaceNewState(
                    new MainState(stateMachine, loadInfo)
            );
        }
    }

    @Override
    public void exit() {

    }

    @Override
    public void update(long delta) {
        if (loadAnimation != null)
            loadAnimation.update(delta);
    }

    @Override
    public void enter() {
        System.out.println("Enter loading state.");
        windowsWidth = Main.winWidth;
        windowsHeight = Main.winHeight;
        Thread load = new Thread(() -> {
            FileChooser musicScore;
            Piano piano;

            Resources.loadImage();
            int AnimationX = (Main.winWidth - Resources.getImageAt(ImageList.LOADING1).getWidth()) / 2;
            int AnimationY = (Main.winHeight - Resources.getImageAt(ImageList.LOADING1).getHeight()) / 2;

            long duration = 250;
            LinerAnimeFactory animationFactory = new LinerAnimeFactory(duration);

            loadAnimation = animationFactory.newAnime(AnimationX, AnimationY,
                    Resources.getImageAt(ImageList.LOADING1), Resources.getImageAt(ImageList.LOADING2), Resources.getImageAt(ImageList.LOADING3));

            Resources.loadMusicalNote();

            System.out.println("Loaded contents successfully.");


            musicScore = new FileChooser(stateMachine.getOwner(), "txt");

            BufferedImage[] pasters = new BufferedImage[Keys.MusicNoteCount];

            IExecuteTask[] pianoKeyTasks = {
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

            for (int i = 0; i < Keys.MusicNoteCount; ++i) {
                pasters[i] = Resources.getImageAt(ImageList.textOf(i));
            }

            PianoKeyHeight = (int) (windowsHeight / 6.5f);//177
            PianoKeyWidth = (int) (PianoKeyHeight * 0.647f);//113

            PianoKeyInterval = (int) (windowsWidth / 80.0f);

            PianoX = (windowsWidth - ((4 * PianoKeyInterval) + (5 * PianoKeyWidth))) / 2;
            PianoY = windowsHeight - ((3 * PianoKeyInterval) + (3 * PianoKeyHeight));

            Piano.PianoKeyField pianoKeyField = new Piano.PianoKeyField(
                    PianoX, PianoY, PianoKeyWidth, PianoKeyHeight, PianoKeyInterval,
                    R.Gray, pasters, pianoKeyTasks, Keys.KeyCodes
            );

            BufferedImage menuLauncher = Resources.getImageAt(ImageList.MORE);

            BufferedImage[] menuOption = {
                    Resources.getImageAt(ImageList.SETTING),
                    Resources.getImageAt(ImageList.IMPORT),
                    Resources.getImageAt(ImageList.SAVE)
            };

            IExecuteTask[] menuCans = {
                    () -> {
                    },
                    musicScore::chooseFile,
                    () -> {
                    }
            };

            int optionWidth = PianoKeyWidth;
            int optionHeight = (int) (PianoKeyHeight * (2.5f / 4.0f));


            int menuX = Main.winWidth - PianoKeyWidth;
            int menuY = 0;

            int optionDistance = Main.winHeight / 300;

            Piano.DropDownMenuField dropDownMenuField = new Piano.DropDownMenuField(menuLauncher, menuOption,
                    menuCans, Keys.MoreMenuKeyCode, menuX, menuY
                    , optionDistance, optionWidth, optionHeight, R.Gray);


            Piano.SystemInfoField systemInfoField = new Piano.SystemInfoField(Main.winWidth, PianoKeyHeight / 2);

            piano = new Piano(systemInfoField, pianoKeyField, dropDownMenuField);

            System.out.println("Loaded.");

            loadInfo.musicScore = musicScore;
            loadInfo.piano = piano;

            isDone = true;
        }, "loadResources");
        load.start();
    }

    public static int PianoX;
    public static int PianoY;
    public static int PianoKeyWidth;
    public static int PianoKeyHeight;
    public static int PianoKeyInterval;
    public static int windowsWidth;
    public static int windowsHeight;
}
