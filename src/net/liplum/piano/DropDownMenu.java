package net.liplum.piano;

import net.liplum.animation.Animation;
import net.liplum.attribute.KeyListener;
import net.liplum.attribute.MouseListener;
import net.liplum.attribute.IRender;
import net.liplum.attribute.IUpdate;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public final class DropDownMenu extends PianoKey implements IRender, IUpdate, KeyListener, MouseListener {

    private PianoKeyGroup<PianoKey> pullOut;// 默认为不可见

    private volatile boolean isPullOut = false;

    public DropDownMenu(int keyCode, Animation pressedAnimation, int x, int y, int width, int height, boolean isVisible,
                        int dx, int dy, CanExecuteTask[] canExecuteTasks, Color colorUp, BufferedImage[] pasters, int optionWidth,
                        int optionHeight, int distance) {

        super(keyCode, pressedAnimation, x, y, width, height, isVisible);
        setTask(this::switchPullOut);

        pullOut = PianoKeyFactory.newNeatPianoKeyGroup(getX() + dx, getY() + dy + height + distance, optionWidth,
                optionHeight, distance, colorUp, pasters, canExecuteTasks, 3, 1);
        pullOut.setAllInVisible();

    }

////////////////////////////////////////////////////////////////////////
//	须实现的内容

    @Override
    public synchronized void update(long delta) {
        super.update(delta);
        pullOut.update(delta);

    }

    @Override
    public synchronized void render(Graphics g) {
        super.render(g);
        pullOut.render(g);

    }

    @Override
    public synchronized void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        pullOut.mouseClicked(e);
//		checkClickedOutSide(e);

    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        super.keyPressed(e);

    }

////////////////////////////////////////////////////////////////////////
//内部调用

//	
//	private synchronized void checkClickedOutSide(MouseEvent e) {
//		if(isVisible()) {
//			if(!getRect().contains(e.getX(), e.getY()))
//				if(isPullOut)
//					switchPullOut();
//		}
//	}

    public void switchPullOut() {
        isPullOut = !isPullOut;

        if (isPullOut) {
            pullOut.setAllVisible();
        } else {
            pullOut.setAllInVisible();
        }
    }

//	内部调用结束
////////////////////////////////////////////////////////////////////////

}
