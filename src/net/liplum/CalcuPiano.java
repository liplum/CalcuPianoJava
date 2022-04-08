package net.liplum;

import net.liplum.Launcher.PlumStarEngineLauncher;
import net.liplum.audio.AudioMessageManger;
import net.liplum.state.gui.LoadState;

public class CalcuPiano extends PlumStarEngineLauncher{

    private Thread AudioThread;

    private InputHandler inputHandler;

    public CalcuPiano(int width, int height) {
        super(width, height);
    }

    @Override
    public void addNotify() {
        super.addNotify();

        initAudioSystem();
        initInputHandler();

        StateMachine.addNewState(new LoadState(StateMachine));

        requestFocus();
    }


    private void initAudioSystem() {
        AudioThread = new Thread(AudioMessageManger::run, "AudioSystem");
        AudioThread.start();
    }

    private void initInputHandler() {
        inputHandler = new InputHandler(StateMachine);
        addKeyListener(inputHandler);
        addMouseListener(inputHandler);
        addMouseMotionListener(inputHandler);
    }

    @Override
    protected void extraUpdate() {
    }

    @Override
    protected void extraRender() {
    }

}