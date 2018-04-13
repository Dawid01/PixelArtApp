package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class ColorGradient extends JPanel {

    ColorPicker selectColor;

    static private int X;
    static private int Y;
    static JPanel panel;

   static Singleton singleton;
   static ColorPicker picker;

    public ColorGradient(ColorPicker picker) {

        this.panel = this;
        this.addMouseMotionListener(colorPickerMotion);
        this.picker = picker;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D gradient = (Graphics2D) g;
        int w = getWidth();
        int h = getHeight();

        Color[] colors = {new Color(0xFFFF0000), new Color(0xFFFF00FF), new Color(0xFF0000FF),new Color(0xFF00FFFF)
                ,new Color(0xFF00FF00),new Color(0xFFFFFF00),new Color(0xFFFF0000)};

        Point2D start = new Point2D.Float(0, 0);
        Point2D end = new Point2D.Float(0, 200);
        float[] dist = {0.02f, 0.28f,0.42f,0.56f,0.7f,0.82f,0.96f};
        LinearGradientPaint p =
                new LinearGradientPaint(start, end, dist, colors);
        gradient.setPaint(p);
        gradient.fillRect(0, 0, w, h);

        g.drawRect(0,Y , 30,5);

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
                singleton.getInstance().setPaintBarColor(new Color(red, green, blue));
                //circlePickerPoint.setLocation((int) (X - circlePickerPoint.getWidth()/2),(int) Y - (circlePickerPoint.getWidth()/2));
                //picker.getCirclePickerPoint().setVisible(false);
                picker.revalidate();
                picker.repaint();
                picker.getColor();
                //picker.getCirclePickerPoint().setVisible(true);

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
}
