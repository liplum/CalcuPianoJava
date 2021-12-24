package net.liplum.piano;

import net.liplum.animation.Animation;
import net.liplum.attribute.KeyListener;
import net.liplum.attribute.MouseListener;
import net.liplum.attribute.IRender;
import net.liplum.attribute.IUpdate;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * @author 普冷姆plum
 */
public class PianoKey implements IUpdate, IRender,
        KeyListener, MouseListener {

    public static final int NULL_KEY_CODE = -1;

    private int keyCode = NULL_KEY_CODE;

    private volatile Animation pressedAnimation;

    private volatile int x, y;

    private volatile int width, height;

    private volatile CanExecuteTask task;

    private volatile Rectangle rect;

    private volatile boolean isVisible;


    public PianoKey(int keyCode, Animation pressedAnimation, int x, int y, int width,
                    int height, CanExecuteTask task, boolean isVisible) {
        this(pressedAnimation, x, y, width, height, isVisible);
        this.task = task;
        this.keyCode = keyCode;
    }

    public PianoKey(int keyCode, Animation pressedAnimation, int x, int y, int width,
                    int height, boolean isVisible) {
        this(pressedAnimation, x, y, width, height, isVisible);
        this.keyCode = keyCode;
    }

    public PianoKey(Animation pressedAnimation, int x, int y, int width,
                    int height, CanExecuteTask task, boolean isVisible) {
        this(pressedAnimation, x, y, width, height, isVisible);
        this.task = task;
    }

    public PianoKey(Animation pressedAnimation, int x, int y, int width,
                    int height, boolean isVisible) {
        this.pressedAnimation = pressedAnimation;
        pressedAnimation.setKeepLastFrame();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isVisible = isVisible;
        updateRect();
    }

////////////////////////////////////////////////////////////////////////
//	内部调用


    private synchronized void updateRect() {
        this.rect = new Rectangle(this.x, this.y, this.width, this.height);
        pressedAnimation.setX(x);
        pressedAnimation.setY(y);
    }


    private synchronized void executeTask() {
        if (task != null)
            task.executeTask();
    }

    private synchronized void trigger() {
        executeTask();
        pressedAnimation.reset();
        pressedAnimation.setKeepLastFrame();
    }


//	内部调用结束
////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////
//	子类接口

    protected int getKeyCode() {
        return keyCode;
    }

    protected void setKeyCode(int keyCode) {
        this.keyCode = keyCode;
    }


    protected Animation getPressedAnimation() {
        return pressedAnimation;
    }


    protected void setPressedAnimation(Animation pressedAnimation) {
        this.pressedAnimation = pressedAnimation;
    }

    protected Rectangle getRect() {
        return rect;
    }


//	子类接口结束
////////////////////////////////////////////////////////////////////////


////////////////////////////////////////////////////////////////////////
//外部接口

    public synchronized boolean isVisible() {
        return isVisible;
    }


    public synchronized void setVisible() {
        isVisible = true;
    }

    public synchronized void setInvisible() {
        isVisible = false;
    }

    public synchronized int getX() {
        return x;
    }


    public synchronized void setX(int x) {
        this.x = x;
    }


    public synchronized int getY() {
        return y;
    }


    public synchronized void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }


    /**
     * 类继承时请使用super调用此方法
     */
    @Override
    public synchronized void render(Graphics g) {
        if (isVisible)
            pressedAnimation.render(g);
    }

    /**
     * 类继承时请使用super调用此方法
     */
    @Override
    public synchronized void update(long delta) {
        if (isVisible) {
            pressedAnimation.update(delta);
            updateRect();
        }
    }

    /**
     * 类继承时请使用super调用此方法
     */
    @Override
    public synchronized void mouseClicked(MouseEvent e) {
        if (isVisible) {
            if (rect.contains(e.getX(), e.getY()))
                trigger();
        }
    }

    /**
     * 类继承时请使用super调用此方法
     */
    @Override
    public synchronized void keyPressed(KeyEvent e) {
        if (isVisible) {
            if (keyCode != NULL_KEY_CODE)
                if (e.getKeyCode() == keyCode) {
                    trigger();
                }
        }
    }

    public CanExecuteTask getTask() {
        return task;
    }

    public void setTask(CanExecuteTask task) {
        this.task = task;
    }

//	外部接口结束
////////////////////////////////////////////////////////////////////////


}
