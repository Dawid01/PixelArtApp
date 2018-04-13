package sample;

import com.sun.java.swing.Painter;

import javax.swing.*;
import java.awt.*;

public class Slider extends JSlider {

    private int x;
    private int y;
    private int width;
    private int height;
    private int minValue;
    private int maxValue;
    private int value;


    public Slider(int x, int y, int width, int height, int minValue, int maxValue, int value) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.value = value;

        this.setBounds(x, y, width, height);
        this.setMinimum(minValue);
        this.setMaximum(maxValue);
        this.setValue(value);
//        UIDefaults sliderDefaults = new UIDefaults();
//        sliderDefaults.put("Slider.thumbWidth", 20);
//        sliderDefaults.put("Slider.thumbHeight", 20);
//
//
//        sliderDefaults.put("Slider.thumbWidth", 20);
//        sliderDefaults.put("Slider.thumbHeight", 20);
//        sliderDefaults.put("Slider:SliderThumb.backgroundPainter", new com.sun.java.swing.Painter<JComponent>() {
//            public void paint(Graphics2D g, JComponent c, int w, int h) {
//                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                g.setStroke(new BasicStroke(2f));
//                g.setColor(Color.RED);
//                g.fillOval(1, 1, w-3, h-3);
//                g.setColor(Color.WHITE);
//                g.drawOval(1, 1, w-3, h-3);
//            }
//        });
//        sliderDefaults.put("Slider:SliderTrack.backgroundPainter", new Painter<JComponent>() {
//            public void paint(Graphics2D g, JComponent c, int w, int h) {
//                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//                g.setStroke(new BasicStroke(2f));
//                g.setColor(Color.GRAY);
//                g.fillRoundRect(0, 6, w-1, 8, 8, 8);
//                g.setColor(Color.WHITE);
//                g.drawRoundRect(0, 6, w-1, 8, 8, 8);
//            }
//        });
//        this.putClientProperty("Nimbus.Overrides",sliderDefaults);
//        this.putClientProperty("Nimbus.Overrides.InheritDefaults",false);

        UIDefaults d = new UIDefaults();
        d.put("Slider:SliderTrack[Enabled].backgroundPainter", new Painter<JSlider>() {
            @Override
            public void paint(Graphics2D g, JSlider c, int w, int h) {
                g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g.setStroke(new BasicStroke(2f));
                g.setColor(Color.GRAY);
                g.fillRoundRect(0, 6, w - 1, 8, 8, 8);
                g.setColor(Color.WHITE);
                g.drawRoundRect(0, 6, w - 1, 8, 8, 8);
            }

        });

        this.putClientProperty("Nimbus.Overrides",d);
        this.putClientProperty("Nimbus.Overrides.InheritDefaults",false);

    }


}
