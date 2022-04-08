package net.liplum.controls;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class ButtonGroup<KeyType extends Button>
        implements IControl {
    protected ArrayList<KeyType> pianoKeys;

    @SafeVarargs
    public ButtonGroup(KeyType... keys) {
        pianoKeys = new ArrayList<>((Arrays.asList(keys)));
    }

    public void add(KeyType k) {
        pianoKeys.add(k);
    }

    public void remove(int index) {
        pianoKeys.remove(index);
    }

    public void clear() {
        pianoKeys.clear();
    }

    public void remove(KeyType k) {
        pianoKeys.remove(k);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (KeyType key : pianoKeys)
            key.mouseClicked(e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (KeyType key : pianoKeys)
            (key).keyPressed(e);
    }

    public void append(ButtonGroup<KeyType> pkg) {
        pianoKeys.addAll(pkg.pianoKeys);
    }

    @Override
    public void render(Graphics g) {
        for (KeyType key : pianoKeys)
            key.render(g);
    }

    @Override
    public void update(long delta) {
        for (KeyType key : pianoKeys) {
            key.update(delta);
        }
    }

    public void setAllVisible() {
        for (KeyType key : pianoKeys)
            key.setVisible(true);
    }

    public void setAllInVisible() {
        for (KeyType key : pianoKeys)
            key.setVisible(false);
    }

    public KeyType get(int index) {
        return pianoKeys.get(index);
    }

    public boolean contains(KeyType key) {
        return pianoKeys.contains(key);
    }

    public int size() {
        return pianoKeys.size();
    }

    public int indexOf(KeyType key) {
        return pianoKeys.indexOf(key);
    }

    public boolean isEmpty() {
        return pianoKeys.isEmpty();
    }

    public void set(int index, KeyType element) {
        pianoKeys.set(index, element);
    }
}
