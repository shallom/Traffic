package GUI;

import MappingSystem.WorldPositioningSystem;
import TrafficSystem.Intersection;
import Canvas.RenderWorld;
import Canvas.TrafficSystemCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class BuildGui {

    private static BuildGui ourInstance = new BuildGui();
    private static JFrame contentFrame;

    public static BuildGui getInstance(){
        return ourInstance;
    }

    private BuildGui(){
    }

    public static void createFrame(int width, int height){
        contentFrame = new JFrame("Traffic");
        contentFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        contentFrame.setPreferredSize(new Dimension(width, height));
        contentFrame.setLocationRelativeTo(null);
        contentFrame.add(RenderWorld.getInstance());
        contentFrame.pack();
        contentFrame.setVisible(true); //need to set this to true when we are ready to start running
        contentFrame.addComponentListener(new ComponentAdapter()
        {
            public void componentResized(ComponentEvent evt) {
                System.out.println("JFrame has  been  resized: " + contentFrame.getSize());
                RenderWorld.getInstance().repaint();
            }
        });
        contentFrame.addMouseListener(TrafficSystemCreator.getInstance());
        contentFrame.addMouseMotionListener(TrafficSystemCreator.getInstance());
        contentFrame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {
                System.out.println("JFrame has  been  made visible first  time");
            }

            @Override
            public void windowClosing(WindowEvent e) {
                System.out.println("JFrame is closing.");
            }

            @Override
            public void windowClosed(WindowEvent e) {
                System.out.println("JFrame is closed.");
            }

            @Override
            public void windowIconified(WindowEvent e) {
                System.out.println("JFrame is  minimized.");
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                System.out.println("JFrame is restored.");
            }

            @Override
            public void windowActivated(WindowEvent e) {
                System.out.println("JFrame is activated.");
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                System.out.println("JFrame is deactivated.");
            }
        });

    }

    public static JFrame getContentFrame() {
        return contentFrame;
    }

    public void run(){
       // try {
        contentFrame.setVisible(true);
        Timer timer = new Timer(50, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.out.println("Repainting");
                RenderWorld.getInstance().repaint();
            }
        });
        timer.start();
    }
}
