package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class ImagePanel extends JPanel {

    static private int x;
    static private int y;
    static private float width;
    static private float height;
    static private Color color;

    static private ImagePanel imagePanel;


    public ImagePanel(int x, int y, int width, int height, Color color) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
        this.setBounds(x,y,width,height);
        this.setBackground(color);
        this.addMouseMotionListener(panelListner);
        imagePanel = this;


    }

    private static MouseMotionListener panelListner = new MouseMotionListener() {

        @Override
        public void mouseDragged(MouseEvent e) {

            //imagePanel.setBounds(x - 4,y - 4,(int) width + 4,(int) height + 4);

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    };

}
