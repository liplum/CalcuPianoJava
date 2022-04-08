package net.liplum.piano;

import net.liplum.attribute.IMouseMotion;
import net.liplum.attribute.IRender;
import net.liplum.attribute.IUpdate;

import java.awt.*;
import java.awt.event.MouseEvent;

public class ProgressBar implements IUpdate, IRender, IMouseMotion {

    private volatile int shellX, shellY, shellWidth, shellHeight;

    private int windowsWidth, windowsHeight;

    private Bar bar;

    private boolean isVisible = false;

    /**
     * @param barColor
     * @param shellColor
     * @param shellX
     * @param shellY
     * @param shellWidth
     * @param shellHeight
     * @param windowsWidth
     * @param windowsHeight
     */
    public ProgressBar(Color barColor, Color shellColor, int shellX, int shellY, int shellWidth, int shellHeight,
                       int windowsWidth, int windowsHeight) {
        this.shellX = shellX;
        this.shellY = shellY;
        this.shellWidth = shellWidth;
        this.shellHeight = shellHeight;
        this.windowsWidth = windowsWidth;
        this.windowsHeight = windowsHeight;

        this.bar = new Bar(shellX, shellY, shellWidth, shellHeight, barColor);
    }

    @Override
    public void render(Graphics g) {
        if (isVisible)
            bar.render(g);

    }

    @Override
    public void update(long delta) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public void setInvisible() {
        this.isVisible = false;
    }

    public void setVisible() {
        this.isVisible = true;
    }

    public boolean isVisible() {
        return isVisible;
    }

    private static class Bar implements IRender {
        volatile int x, y;
        int width, height;
        Color barColor;

        int minY, maxY;

        public Bar(int initialX, int initialY, int width, int height, Color barColor) {
            this.x = initialX;
            this.y = initialY;
            this.width = width;
            this.height = height;
            this.barColor = barColor;
            this.minY = initialY;
            this.maxY = initialY + height;
        }

        @Override
        public void render(Graphics g) {
            g.setColor(barColor);
            g.fillRect(x, y, width, height);
        }

        public synchronized void modifyY(int deltaY) {
            y += deltaY;
            y = Math.min(y, maxY);
            y = Math.max(y, minY);
        }
    }

}
