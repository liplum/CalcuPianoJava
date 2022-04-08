package net.liplum.piano;

import net.liplum.animation.Animation;
import net.liplum.controls.Button;
import net.liplum.controls.ButtonGroup;
import net.liplum.controls.IControl;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public final class DropDownMenu extends Button implements IControl {

    private final ButtonGroup<Button> pullOut;// 默认为不可见

    private boolean isPullOut = false;

    public DropDownMenu(int hotkey, Animation pressedAnimation, int x, int y, int width, int height, boolean isVisible,
                        int dx, int dy, IExecuteTask[] canExecuteTasks, Color colorUp, BufferedImage[] pasters, int optionWidth,
                        int optionHeight, int distance) {

        super(pressedAnimation, x, y, width, height);
        setHotkeyCode(hotkey);
        setTask(this::switchPullOut);

        pullOut = PianoKeyFactory.newNeatPianoKeyGroup(getX() + dx, getY() + dy + height + distance, optionWidth,
                optionHeight, distance, colorUp, pasters, canExecuteTasks, 3, 1);
        pullOut.setAllInVisible();

    }

    @Override
    public void update(long delta) {
        super.update(delta);
        pullOut.update(delta);

    }

    @Override
    public void render(Graphics g) {
        super.render(g);
        pullOut.render(g);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);
        pullOut.mouseClicked(e);
//		checkClickedOutSide(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);

    }

/*	private void checkClickedOutSide(MouseEvent e) {
		if(isVisible()) {
			if(!getRect().contains(e.getX(), e.getY()))
				if(isPullOut)
					switchPullOut();
		}
	}*/

    public void switchPullOut() {
        isPullOut = !isPullOut;

        if (isPullOut) {
            pullOut.setAllVisible();
        } else {
            pullOut.setAllInVisible();
        }
    }
}
