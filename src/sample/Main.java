package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.*;

public class Main implements MouseWheelListener {

    static JLabel tools;
    static JLabel colorsLayout;
    static JLabel colorLine;
    static JLabel colorTxt;

    static JPanel pickerColor;

    static JFrame window;

    static ImagePanel activedButton;

    static Canvas canvas;

    static Singleton singleton;

    static CanvasContainer canvasContainer;

    //buttons
    static ImagePanel drawButton;
    static ImagePanel fillingButton;
    static ImagePanel rubberButton;
    static ImagePanel gridButton;


    static int scale;

    public static void main(String[] args) {

        window = new JFrame("PixelArtApp");
        window.setPreferredSize(new Dimension(1799, 800));
        window.pack();
        window.setVisible(true);
        CreateUI(window);
        singleton.getInstance();

        window.addComponentListener(new ComponentListener() {
            @Override
            public void componentResized(ComponentEvent e) {

                tools.setSize(100,window.getHeight());
                activedButton.setBounds(20,tools.getHeight() - 140,60,60);
                colorsLayout.setBounds(window.getWidth()- 300,9,300,window.getHeight());
                colorLine.setSize(window.getWidth(), 9);
               // canvasContainer.setBounds(window.getWidth()/2 - canvasContainer.getWidth()/2, window.getHeight()/2 - canvasContainer.getHeight()/2, canvasContainer.getWidth(), canvasContainer.getHeight());
                canvas.setBounds(window.getWidth()/2 - canvas.getWidth()/2, window.getHeight()/2 - canvas.getHeight()/2, canvas.getHeight(), canvas.getWidth());
                canvas.invalidate();

            }

            @Override
            public void componentMoved(ComponentEvent e) {

            }

            @Override
            public void componentShown(ComponentEvent e) {

            }

            @Override
            public void componentHidden(ComponentEvent e) {

            }
        });

    }

    static void CreateUI(JFrame window){

        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setOpaque(true);
        backgroundLabel.setSize(window.getWidth(),window.getHeight());
        backgroundLabel.setBackground(new Color(79, 91, 98));
        window.add(backgroundLabel);

        canvasContainer = new CanvasContainer(window.getWidth()/2 - 250, window.getHeight()/2 - 250,500,500,5);
        //backgroundLabel.add(canvasContainer);
        canvas =  new Canvas(0,0, 500, 500, true,50);
        //canvasContainer.add(canvas);
        canvas.setBackground(Color.WHITE);
        backgroundLabel.add(canvas);

        tools = new JLabel();
        tools.setOpaque(true);
        tools.setBackground(new Color(38, 50, 56));
        tools.setBounds(0,9,100,window.getHeight());
        Border toolsBorder = BorderFactory.createMatteBorder(0, 0, 0, 1, new Color(0, 10, 18));
        tools.setBorder(toolsBorder);
        backgroundLabel.add(tools,0);

        colorLine = new JLabel();
        colorLine.setOpaque(true);
        colorLine.setSize(window.getWidth(), 9);
        colorLine.setBackground(singleton.getInstance().getPaintColor());
        backgroundLabel.add(colorLine,1);

        colorsLayout = new JLabel();
        colorsLayout.setOpaque(true);
        colorsLayout.setBackground(new Color(38, 50, 56));
        colorsLayout.setBounds(window.getWidth()- 300,9,300,window.getHeight());
        Border colorsLayoutBorder = BorderFactory.createMatteBorder(0, 1, 0, 0, new Color(0, 10, 18));
        colorsLayout.setBorder(colorsLayoutBorder);
        backgroundLabel.add(colorsLayout,2);

        CreateColorsLayout(colorsLayout);
        CreateTools(tools,window);
        canvas.setBounds(window.getWidth()/2 - canvas.getWidth()/2, window.getHeight()/2 - canvas.getHeight()/2, canvas.getHeight(), canvas.getWidth());


    }



    static void CreateTools(JLabel tools, JFrame window){


        drawButton =  new ImagePanel(20,20,60,60,new Color(0, 10, 18));
        JLabel drawLabel =  new JLabel(new ImageIcon(Main.class.getResource("/images/drawikon.png")));
        drawButton.add(drawLabel);
        tools.add(drawButton);

        fillingButton =  new ImagePanel(20,120,60,60,new Color(0, 10, 18,0));
        JLabel fillingLabel =  new JLabel(new ImageIcon(Main.class.getResource("/images/fillingikon.png")));
        fillingButton.add(fillingLabel);
        tools.add(fillingButton);

        rubberButton =  new ImagePanel(20,220,60,60,new Color(0, 10, 18,0));
        JLabel RubberLabel =  new JLabel(new ImageIcon(Main.class.getResource("/images/rubberikon.png")));
        rubberButton.add(RubberLabel);
        tools.add(rubberButton);

        gridButton =  new ImagePanel(20,320,60,60,new Color(0, 10, 18,0));
        JLabel gridLabel =  new JLabel(new ImageIcon(Main.class.getResource("/images/gridikon.png")));
        gridButton.add(gridLabel);
        tools.add(gridButton);

        ButtonsListners();


        activedButton =  new ImagePanel(20,tools.getHeight() - 140,60,60,new Color(0, 10, 18,0));
        JLabel activedLabel =  new JLabel(new ImageIcon(Main.class.getResource("/images/drawikon.png")));
        activedButton.add(activedLabel);
        tools.add(activedButton);
       window.setSize(1800,800);
       window.invalidate();
       window.validate();
       window.repaint();
       SwingUtilities.updateComponentTreeUI(window);


    }

    static void CreateColorsLayout(JLabel colorsLayout){

        pickerColor = new JPanel();
        colorTxt =  new JLabel("#0DFF00");

        ColorPicker colorPicker =  new ColorPicker(colorLine,pickerColor,colorTxt);
        colorPicker.setBounds(20, 20, 200, 200);
        colorsLayout.add(colorPicker);

        ColorGradient colorGradient =  new ColorGradient(colorPicker);
        colorGradient.setBounds(240, 20, 20, 200);
        colorsLayout.add(colorGradient);

        pickerColor.setBounds(30,235,70,35);
        pickerColor.setBackground(Color.GREEN);
        Border pickerColorBorder = BorderFactory.createLineBorder(new Color(0, 10, 18),5);
        pickerColor.setBorder(pickerColorBorder);
        colorsLayout.add(pickerColor);

        colorTxt.setBounds(110,235,120,35);
        colorTxt.setForeground(Color.WHITE);
        colorTxt.setFont(new Font("Serif", Font.ITALIC, 20));
        colorsLayout.add(colorTxt);

        Slider alphaSlider =  new Slider(25,290,180,20,0,255,255);
        colorsLayout.add(alphaSlider);

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        int notches = e.getWheelRotation();
        if (notches < 0) {

            scale += notches;
        } else {

            scale -=notches;
        }
        scale = notches;
        //canvas.setSize((int)(canvas.getWidth() * scale), (int)(canvas.getHeight() * scale));
        canvas.setBounds(window.getWidth()/2 * scale - canvas.getWidth()/2 * scale, window.getHeight()/2 * scale - canvas.getHeight()/2 * scale, canvas.getHeight() * scale, canvas.getWidth() * scale);

    }

   static void ButtonsListners(){

        gridButton.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                canvas.isGrid = !canvas.isGrid;
                canvas.revalidate();
                canvas.repaint();

                if(canvas.isGrid){
                    canvas.setBackground(Color.WHITE);
                }else {
                    canvas.setBackground(Color.black);
                }
                super.mouseClicked(e);
            }
        });

       drawButton.addMouseListener(new MouseInputAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {

               canvas.setPaintMode(1);
               JLabel activedLabel =  new JLabel(new ImageIcon(Main.class.getResource("/images/drawikon.png")));
               activedLabel.remove(0);
               activedButton.add(activedLabel);
           }
       });

       rubberButton.addMouseListener(new MouseInputAdapter() {
           @Override
           public void mouseClicked(MouseEvent e) {

               canvas.setPaintMode(0);
               JLabel activedLabel =  new JLabel(new ImageIcon(Main.class.getResource("/images/rubberikon.png")));
               activedLabel.remove(0);
               activedButton.add(activedLabel);
           }
       });

    }

}
