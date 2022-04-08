package net.liplum;

import net.liplum.state.GUIStateMachine;

import java.awt.event.*;

public class InputHandler implements KeyListener, MouseListener, MouseMotionListener {

    private GUIStateMachine StateMachine;

    public InputHandler(GUIStateMachine GSM) {
        StateMachine = GSM;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (StateMachine != null)
            StateMachine.mouseClicked(e);

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (StateMachine != null)
            StateMachine.mousePressed(e);

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (StateMachine != null)
            StateMachine.mouseReleased(e);

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (StateMachine != null)
            StateMachine.mouseExited(e);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (StateMachine != null)
            StateMachine.mouseExited(e);

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (StateMachine != null)
            StateMachine.keyTyped(e);

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (StateMachine != null)
            StateMachine.keyPressed(e);

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (StateMachine != null)
            StateMachine.keyReleased(e);

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (StateMachine != null)
            StateMachine.mouseDragged(e);

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (StateMachine != null)
            StateMachine.mouseMoved(e);

    }

}
