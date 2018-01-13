package Canvas;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;


//Class is a singleton
public class RenderWorld extends JPanel{
    private static RenderWorld ourInstance = new RenderWorld();
    private static ArrayList<Renderable> foreGroundRenderQue;
    private static ArrayList<Renderable> backGroundRenderQue;

    public static RenderWorld getInstance() {
        return ourInstance;
    }

    private RenderWorld() {
        setOpaque(true);
        setBackground(Color.GREEN);
        foreGroundRenderQue = new ArrayList<>();
        backGroundRenderQue = new ArrayList<>();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(getBackground());
        g.fillRect(0, 0, getWidth(), getHeight());
        g.setColor(getForeground());
        for (Renderable object : backGroundRenderQue) {
            Graphics2D g2d = (Graphics2D) g.create();
            object.paint(g2d);
            g2d.dispose();
        }
        for (Renderable object : foreGroundRenderQue) {
            Graphics2D g2d = (Graphics2D) g.create();
            object.paint(g2d);
            g2d.dispose();
        }
    }

    public static void delete(Renderable renderable){
        if(renderable.getType() == Renderable.TrafficType.INTERSECTION || renderable.getType() == Renderable.TrafficType.ROAD ){
            backGroundRenderQue.remove(renderable);
        }else{
            foreGroundRenderQue.remove(renderable);
        }
    }

    public static void addToRenderQue(Renderable object){
        if(object.getType() == Renderable.TrafficType.INTERSECTION || object.getType() == Renderable.TrafficType.ROAD ){
            backGroundRenderQue.add(object);
        }else{
            foreGroundRenderQue.add(object);
        }
    }

    public static void update(){

    }

}
