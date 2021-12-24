package net.liplum.main;

import net.liplum.Launcher.PlumStarEngineLauncher;
import net.liplum.audio.AudioMessageManger;
import net.liplum.state.gui.LoadState;

public class CalcuPiano extends PlumStarEngineLauncher implements Runnable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 音效系统的独立线程
     */
    private Thread AudioThread;

    /**
     * 输入处理器<br>
     * 它会处理游戏所有键盘和鼠标的事件
     */
    private InputHandler inputHandler;

    /**
     * @param width  设定窗口的宽度
     * @param height 设定窗口的高度
     */
    public CalcuPiano(int width, int height) {
        super(width, height);
    }

    /**
     * 我也不知道为什么要Override这个方法<br>
     * 它会执行各种初始化工作
     */
    @Override
    public void addNotify() {
        super.addNotify();

        initAudioSystem();
        initInputHandler();

//		进入加载模式
        StateMachine.addNewState(new LoadState(StateMachine));

        requestFocus();
    }

    /**
     * 初始化音效系统<br>
     * <font color=red>注意：它会开始新的线程（音效系统）</font>
     */
    private void initAudioSystem() {
        AudioThread = new Thread(AudioMessageManger.Instance(), "AudioSystem");
        AudioThread.start();
    }

    /**
     * 初始化 输入处理器<br>
     * <font color=red>它会为当前的JComponent(即GUIPanel类)注册键盘、鼠标的监听器</font>
     */
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