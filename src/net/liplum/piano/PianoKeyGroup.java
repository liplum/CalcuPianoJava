package net.liplum.piano;

import net.liplum.attribute.KeyListener;
import net.liplum.attribute.MouseListener;
import net.liplum.attribute.IRender;
import net.liplum.attribute.IUpdate;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class PianoKeyGroup<KeyType extends PianoKey> implements IUpdate, IRender, KeyListener, MouseListener {

    protected volatile ArrayList<KeyType> pianoKeys;

    public PianoKeyGroup() {
        pianoKeys = new ArrayList<>();
    }

    @SafeVarargs
    public PianoKeyGroup(KeyType... keys) {
        pianoKeys = new ArrayList<>((Arrays.asList(keys)));
    }

    public synchronized void add(KeyType k) {
        pianoKeys.add(k);
    }

    public synchronized void remove(int index) {
        pianoKeys.remove(index);
    }

    public synchronized void clear() {
        pianoKeys.clear();
    }

    public synchronized void remove(KeyType k) {
        pianoKeys.remove(k);
    }

    @Override
    public synchronized void mouseClicked(MouseEvent e) {
        for (KeyType key : pianoKeys)
            key.mouseClicked(e);
    }

    @Override
    public synchronized void keyPressed(KeyEvent e) {
        for (KeyType key : pianoKeys)
            key.keyPressed(e);
    }

    public synchronized void append(PianoKeyGroup<KeyType> pkg) {
        pianoKeys.addAll(pkg.pianoKeys);
    }

    @Override
    public synchronized void render(Graphics g) {
        for (KeyType key : pianoKeys)
            key.render(g);
    }

    @Override
    public synchronized void update(long delta) {
        for (KeyType key : pianoKeys) {
            key.update(delta);
        }
    }

    public synchronized void setAllVisible() {
        for (KeyType key : pianoKeys)
            key.setVisible();
    }

    public synchronized void setAllInVisible() {
        for (KeyType key : pianoKeys)
            key.setInvisible();
    }

    public synchronized KeyType get(int index) {
        return pianoKeys.get(index);
    }

    public synchronized void contains(KeyType key) {
        pianoKeys.contains(key);
    }

    public synchronized int size() {
        return pianoKeys.size();
    }

    public synchronized int indexOf(KeyType key) {
        return pianoKeys.indexOf(key);
    }

    public synchronized boolean isEmpty() {
        return pianoKeys.isEmpty();
    }

    public synchronized void set(int index, KeyType element) {
        pianoKeys.set(index, element);
    }
}
