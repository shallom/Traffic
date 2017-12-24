
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;

//Class is a singleton
public class RenderCanvas extends JPanel{
    private static RenderCanvas ourInstance = new RenderCanvas();
    private static ArrayList<Renderable> renderQue;
    private static HashSet<Renderable> world;

    public static RenderCanvas getInstance() {
        return ourInstance;
    }

    private RenderCanvas() {
        renderQue = new ArrayList<>();
        world = new HashSet<>();
        setBackground( Color.GREEN );
    }

    @Override
    public void paintComponent(Graphics g) {
        for (Renderable object : renderQue) {
            Graphics2D g2d = (Graphics2D) g.create();
            object.paint(g2d);
            g2d.dispose();
        }
        renderQue.clear();
    }

    public static void addToRenderQue(Renderable object){
        renderQue.add(object);
        world.add(object);
    }

    public static void repaintWorld(){
        renderQue.addAll(world);
    }

    public static void update(){

    }

}
