package sample;

import java.awt.*;

public class Singleton {
    private static Singleton ourInstance = new Singleton();

    private Color paintColor;
    private Color paintBarColor;

    public static Singleton getInstance() {
        return ourInstance;
    }

    private Singleton() {

        paintColor = Color.green;;
        paintBarColor = Color.green;
    }

    public Color getPaintColor() {
        return paintColor;
    }

    public void setPaintColor(Color paintColor) {
        this.paintColor = paintColor;
    }

    public Color getPaintBarColor() {
        return paintBarColor;
    }

    public void setPaintBarColor(Color paintBarColor) {
        this.paintBarColor = paintBarColor;
    }
}
