package sample;

import javax.swing.*;
import java.awt.*;

public class CanvasGrid extends JPanel {


    private int size;
    private int pixelSize;

    public CanvasGrid(int size, int pixelSize) {
        this.size = size;
        this.pixelSize = pixelSize;

        this.setBounds(0,0,size,size);
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        float count = size/pixelSize;

        for(int i = 0; i < count; i++){

            g.drawLine(0,i * pixelSize,size,i * pixelSize);
        }

    }


}
