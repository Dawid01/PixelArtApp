package sample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

public class Canvas extends JPanel implements MouseListener{

    private int x;
    private int y;
    private int width;
    private int height;
    private int resolution;
    boolean isGrid;
    boolean isRubber;
    static  boolean canDraw = false;

    static float pixelSize;

    static private int paintMode = 1;

    static ArrayList<PixelPoint> point = new ArrayList<PixelPoint>();

    ArrayList<Float> xyPos = new ArrayList<Float>();


    static JPanel jPanel;

    static Singleton singleton;

    public Canvas(int x, int y, int width, int height, boolean isGrid, int resolution) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.isGrid = isGrid;
        this.resolution = resolution;
        this.setBounds(x,y,width,height);
        jPanel = this;
        singleton.getInstance();

        pixelSize = (float)width/resolution;

        float pixelsCount = width/pixelSize;

        float oldPos = pixelSize/2;

        for (int i = 0; i < pixelsCount; i++){

            if(i != 0) {
                float pos = oldPos + pixelSize;
                xyPos.add(pos);
                oldPos = pos;
            }else {
                float pos = pixelSize/2;
                xyPos.add(pos);
                oldPos = pos;
            }
        }

        addMouseListener(this);
        addMouseMotionListener(motionListener);


    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        //this.setBackground(new Color(0,0,0,0));
        if(isGrid) {
            for (int i = 0; i < height / pixelSize; i++) {

                g.setColor(Color.DARK_GRAY);
                g.drawLine(0, i * (int) pixelSize, height, i * (int) pixelSize);
                g.drawLine(i * (int) pixelSize, 0, i * (int) pixelSize, height);

            }

        }

        for (int j = 0; j < point.size(); j++) {
            float posX = 0;
            float posY = 0;

            float deltaX = 1000000;
            float deltaY = 1000000;
            PixelPoint p = point.get(j);

            for(int i = 0; i < xyPos.size(); i++){

                float newPosX  = deltaPoints(p.getPoint().x,xyPos.get(i),p.getPoint().y, 0);

                if(newPosX < deltaX){
                    deltaX = newPosX;
                    posX = xyPos.get(i);
                }

                jPanel.revalidate();
                jPanel.repaint();
            }

            for(int i = 0; i < xyPos.size(); i++){

                float newPosY  = deltaPoints(p.getPoint().x,0,p.getPoint().y, xyPos.get(i));

                if(newPosY < deltaY){
                    deltaY = newPosY;
                    posY = xyPos.get(i);
                }
            }


            Graphics pixel =  g;

            pixel.setColor(p.getColor());
            pixel.fillRect((int) posX - (int) (pixelSize / 2), (int) posY - (int) (pixelSize / 2), (int) pixelSize, (int) pixelSize);
            //pixel.fillRect((int)((p.x/pixelSize) * pixelSize),(int)((p.y/pixelSize) * pixelSize),(int) pixelSize, (int) pixelSize);

            if(isGrid) {
                g.setColor(Color.DARK_GRAY);
                for (int i = 0; i < height / pixelSize; i++) {
                    g.drawLine(0, i * (int) pixelSize, height, i * (int) pixelSize);
                    g.drawLine(i * (int) pixelSize, 0, i * (int) pixelSize, height);
                }

            }
        }

    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(width, width);
    }

    private static MouseMotionListener motionListener = new MouseMotionListener() {

        @Override
        public void mouseDragged(MouseEvent e) {

            drawNePixel(e);
        }

        @Override
        public void mouseMoved(MouseEvent e) {

            //point.add(new Point(e.getX(), e.getY()));

        }
    };


    static void drawNePixel(MouseEvent e){

        if(canDraw) {
            switch (paintMode){

                case 0:
//                        point.add(new PixelPoint(new Point(e.getX(), e.getY()), singleton.getInstance().getPaintColor()));
//                        jPanel.revalidate();
//                        jPanel.repaint();
                    break;
                case 1:
                    point.add(new PixelPoint(new Point(e.getX(), e.getY()), singleton.getInstance().getPaintColor()));
                    jPanel.revalidate();
                    jPanel.repaint();
                    break;

            }

        }
    }

    Float deltaPoints(float p1x, float p2x, float p1y, float p2y){

        double delta=  Math.sqrt((p1x -p2x)*(p1x-p2x) + (p1y-p2y)*(p1y-p2y));
        return (float)delta;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        if(e.getButton () == MouseEvent.BUTTON1) {
//            point.add(new PixelPoint(new Point(e.getX(), e.getY()), singleton.getInstance().getPaintColor()));
//            revalidate();
//            repaint();
//        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        //setCursor(new Cursor(Cursor.HAND_CURSOR));


    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton () == MouseEvent.BUTTON1) {
            drawNePixel(e);
            canDraw = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        canDraw = false;
    }


    public int getResolution() {
        return resolution;
    }

    public void setResolution(int resolution) {
        this.resolution = resolution;
    }

    public boolean isGrid() {
        return isGrid;
    }

    public void setGrid(boolean grid) {
        isGrid = grid;
    }

    public int getPaintMode() {
        return paintMode;
    }

    public void setPaintMode(int paintMode) {
        this.paintMode = paintMode;
    }
}
