package net.liplum.controls;

import net.liplum.animation.Animation;
import net.liplum.piano.IExecuteTask;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Button implements IControl {
    protected int hotkeyCode = -1;

    protected Animation pressedAnimation;

    protected int x, y;

    protected int width, height;

    protected IExecuteTask task;

    protected Rectangle rect;

    protected boolean isVisible = true;
    protected boolean isEnable = true;

    public Button(Animation pressedAnimation, int x, int y, int width,
                  int height) {
        this.pressedAnimation = pressedAnimation;
        pressedAnimation.setKeepLastFrame();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        updateRect();
    }

    protected void updateRect() {
        this.rect = new Rectangle(this.x, this.y, this.width, this.height);
        pressedAnimation.setX(x);
        pressedAnimation.setY(y);
    }

    public void executeTask() {
        if (task != null)
            task.executeTask();
    }

    public void trigger() {
        executeTask();
        pressedAnimation.reset();
        pressedAnimation.setKeepLastFrame();
    }

    @Override
    public synchronized void render(Graphics g) {
        if (isVisible)
            pressedAnimation.render(g);
    }

    @Override
    public synchronized void update(long delta) {
        if (isVisible) {
            pressedAnimation.update(delta);
            updateRect();
        }
    }

    @Override
    public synchronized void mouseClicked(MouseEvent e) {
        if (isVisible) {
            if (rect.contains(e.getX(), e.getY()))
                trigger();
        }
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        if (isVisible) {
            if (e.getKeyCode() == hotkeyCode) {
                trigger();
            }
        }
    }

    public IExecuteTask getTask() {
        return task;
    }

    public void setTask(IExecuteTask task) {
        this.task = task;
    }

    public Animation getPressedAnimation() {
        return pressedAnimation;
    }

    public void setPressedAnimation(Animation pressedAnimation) {
        this.pressedAnimation = pressedAnimation;
    }

    public Rectangle getRect() {
        return rect;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getHotkeyCode() {
        return hotkeyCode;
    }

    public void setHotkeyCode(int hotkeyCode) {
        this.hotkeyCode = hotkeyCode;
    }
}
