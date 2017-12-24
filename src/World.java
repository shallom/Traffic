import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class World {

    private JFrame worldFrame;
    private ArrayList<Intersection> intersections;

    public World(int width, int height){
        worldFrame = new JFrame("Traffic");
        worldFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        worldFrame.setSize(width, height);
        worldFrame.setLocationRelativeTo(null);
        worldFrame.setVisible(false); //need to set this to true when we are ready to start running
        worldFrame.setBackground(Color.green);
        worldFrame.add(RenderCanvas.getInstance());
        intersections = new ArrayList<>();
        WorldPositioningSystem.setWorldDimensions(width, height);
        worldFrame.addComponentListener(new ComponentAdapter()
        {
            public void componentResized(ComponentEvent evt) {
                System.out.println("JFrame has  been  resized");
                RenderCanvas.repaintWorld();
            }
        });

        worldFrame.addWindowListener(new WindowListener() {
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

    public void newInterSection(){
        intersections.add(new Intersection(1, 250, 250, 100));
    }

    public void run(){
       // try {
        worldFrame.setVisible(true);
        Timer timer = new Timer(50, new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.out.println("Repainting");
                RenderCanvas.getInstance().repaint();
            }
        });
        timer.start();
    }
}
