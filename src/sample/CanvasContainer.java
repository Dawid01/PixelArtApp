package sample;

import javax.swing.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class CanvasContainer extends JPanel implements MouseWheelListener {

    private int width;
    private int height;
    private int X;
    private int Y;

    private float maxScale;
    private float scale;

    public CanvasContainer(int width, int height, int x, int y, float maxScale) {
        this.width = width;
        this.height = height;
        X = x;
        Y = y;
        this.maxScale = maxScale;
        this.setBounds(x,y,width,height);
    }

    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {

            scale += notches;
        } else {

            scale -=notches;
        }
        this.setSize((int)(width * scale), (int)(height * scale));

    }
}
