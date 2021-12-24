package net.liplum.state.gui;

import net.liplum.animation.Animation;
import net.liplum.animation.IsometricAnimationFactory;
import net.liplum.main.Main;
import net.liplum.main.Resources;
import net.liplum.main.Resources.ImageList;
import net.liplum.state.GUIState;
import net.liplum.state.GUIStateMachine;

import java.awt.*;

public class LoadState extends GUIState implements Runnable {

    private volatile boolean isDone = false;

    private Thread load;

    private Animation loadAnimation = null;

    public LoadState(GUIStateMachine StateMachine) {
        super(StateMachine);
        // TODO Auto-generated constructor stub
    }

    @Override
    public synchronized void render(Graphics g) {
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, Main.winWidth, Main.winHeight);
        if (loadAnimation != null)
            loadAnimation.render(g);
    }

    @Override
    public synchronized void execute() {
        if (isDone) {
            System.gc();
            stateMachine.replaceNewState(new MainState(stateMachine, loadAnimation));
        }
    }

    @Override
    public synchronized void enter() {
        System.out.println("成功进入Load状态");
        load = new Thread(this, "loadResources");
        load.start();
    }

    @Override
    public synchronized void exit() {

    }

    @Override
    public synchronized void update(long delta) {
        if (loadAnimation != null)
            loadAnimation.update(delta);
    }


    @Override
    public void run() {
//		加载图像
        Resources.loadImage();
//		加载动画	
        long duration = 250;
        int AnimationX = (Main.winWidth - Resources.getImageAt(ImageList.LOADING1).getWidth()) / 2;
        int AnimationY = (Main.winHeight - Resources.getImageAt(ImageList.LOADING1).getHeight()) / 2;

        IsometricAnimationFactory animationFactory = new IsometricAnimationFactory(duration);

        loadAnimation = animationFactory.newAnimation(AnimationX, AnimationY,
                Resources.getImageAt(ImageList.LOADING1), Resources.getImageAt(ImageList.LOADING2), Resources.getImageAt(ImageList.LOADING3));

//		加载14个音符
        Resources.loadMusicalNote();

        System.out.println("加载完毕");
        isDone = true;
    }

}
