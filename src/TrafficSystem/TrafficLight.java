package TrafficSystem;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import Canvas.Renderable;


public class TrafficLight extends Renderable implements TrafficController{

    private Rectangle trafficLight;
    private ArrayList<Movable> listeningObjects;
    private Color currentLight;
    private String roadToControl;
    private static HashMap<String, TrafficLight> trafficLightHashMap = new HashMap<>();

    private static void installTrafficLight(TrafficLight trafficLight){
        trafficLightHashMap.put(trafficLight.roadToControl, trafficLight);
    }

    public static Instruction getTrafficOrder(String road){
        if(trafficLightHashMap.get(road).currentLight == Color.red){
            return Instruction.STOP;
        }else if(trafficLightHashMap.get(road).currentLight == Color.green){
            return Instruction.GO;
        }
        else{
            return Instruction.STOP;
        }
    }

    public TrafficLight(String roadToControl, int xPos, int yPos, int length, int width){
        this.roadToControl = roadToControl;
        trafficLight = new Rectangle(new Point(xPos,yPos), new Dimension(length, width));
        updateRenderQue(this);
        currentLight = Color.RED;
        listeningObjects = new ArrayList<>();
    }

    @Override
    public void setTrafficOrder() {

    }

    @Override
    public Renderable.TrafficType getType() {
        return TrafficType.TRAFFIC_LIGHT;
    }

    @Override
    public void paint(Graphics2D g2d) {
        System.out.println("Painting Light");
        g2d.setColor(currentLight);
        g2d.fill(trafficLight);
    }
}
