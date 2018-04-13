package sample;

import javax.swing.*;
import java.awt.*;

public class CirclePickerPoint extends JPanel {

    private int x;
    private int y;
    private int size;

    public CirclePickerPoint(int x, int y, int size) {
        this.x = x;
        this.y = y;
        this.size = size;
        this.setBackground(new Color(0,0,0,0));
        this.setBounds(x - size/2,y - size/2,size,size);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.WHITE);
        g.drawOval(0,0,8,8);
        g.setColor(Color.BLACK);

        g.drawOval(1,1,7,7);

    }
}


