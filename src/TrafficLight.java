import java.awt.*;
import java.util.ArrayList;

public class TrafficLight extends Renderable implements TrafficController{

    private Rectangle trafficLight;
    private ArrayList<Movable> listeningObjects;
    private Color currentLight;

    public TrafficLight(int xPos, int yPos, int length, int width){
        trafficLight = new Rectangle(new Point(xPos,yPos), new Dimension(length, width));
        updateRenderQue(this);
        currentLight = Color.RED;
        listeningObjects = new ArrayList<>();
    }

    public void addListener(Movable newListener){
        listeningObjects.add(newListener);
    }

    public void removeListener(Movable listener){
        listeningObjects.remove(listener);
    }

    @Override
    public void giveTrafficOrder() {

    }

    @Override
    public void paint(Graphics2D g2d) {
        System.out.println("painting road");
        g2d.setColor(currentLight);
        g2d.fill(trafficLight);
    }
}
