package net.liplum.controls;

import net.liplum.attribute.*;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public interface IControl extends IUpdate, IRender, IKey, IMouse, IMouseMotion {
    @Override
    default void keyPressed(KeyEvent e) {
    }

    @Override
    default void keyReleased(KeyEvent e) {
    }

    @Override
    default void keyTyped(KeyEvent e) {
    }

    @Override
    default void mouseClicked(MouseEvent e) {
    }

    @Override
    default void mouseEntered(MouseEvent e) {
    }

    @Override
    default void mouseExited(MouseEvent arg0) {
    }

    @Override
    default void mousePressed(MouseEvent e) {
    }

    @Override
    default void mouseReleased(MouseEvent e) {
    }

    @Override
    default void mouseDragged(MouseEvent arg0) {
    }

    @Override
    default void mouseMoved(MouseEvent arg0) {
    }

    @Override
    void render(Graphics g);

    @Override
    void update(long delta);
}
