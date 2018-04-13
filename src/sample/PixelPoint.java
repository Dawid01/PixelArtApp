package sample;

import java.awt.*;

public class PixelPoint {

    private Point point;
    private Color color;

    public PixelPoint(Point point, Color color) {
        this.point = point;
        this.color = color;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}

