package net.liplum.piano;

import net.liplum.attribute.IRender;
import net.liplum.attribute.IUpdate;

import java.awt.*;

public class DisplayBoard implements IUpdate, IRender {

    private volatile int x, y;
    private int ContainerWidth, ContainerHeight;
    private Color frameColor;
    private float thickness;

    private TextBoard textBoard;

    public DisplayBoard(int x, int y, int containerWidth, int containerHeight, Color frameColor, float thickness) {
        this.x = x;
        this.y = y;
        this.ContainerWidth = containerWidth;
        this.ContainerHeight = containerHeight;
        this.frameColor = frameColor;
        this.thickness = thickness;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(frameColor);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(thickness));

        g2d.drawRect(x, y, ContainerWidth, ContainerHeight);

        textBoard.render(g);
    }

    @Override
    public void update(long delta) {

        textBoard.update(delta);
    }
}
