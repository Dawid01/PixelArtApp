package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class ColorPicker extends JPanel implements MouseListener {

    Color pickedColor;


    static  float X;
    static  float Y;

    static JPanel panel;

    static  Singleton singleton;
    static JLabel colorLine;
    static JLabel colorTxt;
    static JPanel pickerColor;

    public ColorPicker(JLabel colorLine, JPanel pickerColor, JLabel colorTxt) {
        this.addMouseMotionListener(colorPickerMotion);
        this.pickerColor = pickerColor;
        this.panel = this;
        this.colorLine = colorLine;
        this.colorTxt = colorTxt;

        singleton.getInstance();
        //this.add(circlePickerPoint);
        X = this.getWidth();
        Y = 0;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        Graphics2D gradient = (Graphics2D) g;
        GradientPaint primary = new GradientPaint(
                0f, 0f, Color.WHITE, 200f, 0f, singleton.getInstance().getPaintBarColor());
        GradientPaint shade = new GradientPaint(
                0f, 0f, new Color(0, 0, 0, 0),
                0f, 200f, new Color(0, 0, 0, 255));
        gradient.setPaint(primary);
        gradient.fillRect(0, 0, getWidth(), getHeight());
        gradient.setPaint(shade);
        gradient.fillRect(0, 0, getWidth(), getHeight());
       // g.drawOval((int) 200, (int) 0, 10, 10);

        g.setColor(Color.WHITE);
        g.drawOval((int) X-7,(int)Y-7,14,14);
        g.setColor(Color.BLACK);
        g.drawOval((int) X-6,(int)Y-6,12,12);

    }


    private static MouseMotionListener colorPickerMotion = new MouseMotionListener() {

        @Override
        public void mouseDragged(MouseEvent e) {

            X = e.getX();
            Y = e.getY();
            if(X >= 0 && X <= panel.getWidth() && Y >= 0 && Y <= panel.getHeight()) {
                BufferedImage pickerImage = createImage(panel);
                int color = pickerImage.getRGB((int) X, (int) Y);
                Graphics2D g2d = pickerImage.createGraphics();
                panel.setOpaque(false);
                panel.setBorder(null);
                panel.paintAll(g2d);
                g2d.dispose();
                int red = (color & 0x00ff0000) >> 16;
                int green = (color & 0x0000ff00) >> 8;
                int blue = color & 0x000000ff;
                colorLine.setBackground(new Color(color));
                pickerColor.setBackground(new Color(color));
                colorTxt.setText(String.format("#%02x%02x%02x", red, green, blue));
                singleton.getInstance().setPaintColor(new Color(red, green, blue));
                panel.revalidate();
                panel.repaint();
            }

        }

        @Override
        public void mouseMoved(MouseEvent e) {

        }
    };


    static BufferedImage createImage(JPanel panel) {


        int w = panel.getWidth();
        int h = panel.getHeight();
        BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = bi.createGraphics();
        panel.paint(g);
        return(bi);
    }


    public  void getColor(){

        if(X >= 0 && X <= panel.getWidth() && Y >= 0 && Y <= panel.getHeight()) {
            BufferedImage pickerImage = createImage(panel);
            int color = pickerImage.getRGB((int) X, (int) Y);
            Graphics2D g2d = pickerImage.createGraphics();
            panel.setOpaque(false);
            panel.setBorder(null);
            panel.paintAll(g2d);
            g2d.dispose();
            int red = (color & 0x00ff0000) >> 16;
            int green = (color & 0x0000ff00) >> 8;
            int blue = color & 0x000000ff;
            colorLine.setBackground(new Color(color));
            pickerColor.setBackground(new Color(color));
            colorTxt.setText(String.format("#%02x%02x%02x", red, green, blue));
            singleton.getInstance().setPaintColor(new Color(red, green, blue));
            //circlePickerPoint.setLocation((int) (X - circlePickerPoint.getWidth()/2),(int) Y - (circlePickerPoint.getWidth()/2));
        }
    }


    @Override
    public void mouseClicked(MouseEvent e) {

        X = e.getX();
        Y = e.getY();
        revalidate();
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        X = e.getX();
        Y = e.getY();
        revalidate();
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        X = e.getX();
        Y = e.getY();
        revalidate();
        repaint();
    }

    public static JPanel getPickerColor() {
        return pickerColor;
    }

    public static void setPickerColor(JPanel pickerColor) {
        ColorPicker.pickerColor = pickerColor;
    }
}
