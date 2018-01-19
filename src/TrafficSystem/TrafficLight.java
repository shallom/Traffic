package TrafficSystem;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import Canvas.*;


public class TrafficLight extends Renderable implements TrafficController{

    private Rectangle trafficLight;
    private ArrayList<Movable> listeningObjects;
    private Color currentLight;
    private String roadToControl;
    private static HashMap<String, TrafficLight> trafficLightHashMap = new HashMap<>();

    private static void installTrafficLight(TrafficLight trafficLight){
        trafficLightHashMap.put(trafficLight.roadToControl, trafficLight);
    }

    public static Instruction getTrafficInstruction(String road){
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
        updateRenderQue();
        installTrafficLight(this);
        this.roadToControl = roadToControl;
        Point p = new Point(xPos,yPos);
        Dimension d = new Dimension(length, width);
        trafficLight = new Rectangle(p, d);
        currentLight = Color.RED;
        listeningObjects = new ArrayList<>();
    }

    @Override
    public void setTrafficControl() {

    }

    @Override
    public Renderable.TrafficType getType() {
        return TrafficType.TRAFFIC_LIGHT;
    }

    @Override
    public void delete() {
        System.out.println("Deleting Light");
        if(!isDeleted()){
            trafficLightHashMap.remove(roadToControl);
            RenderWorld.delete(this);
        }
        setDeleted();
    }

    @Override
    public void render(Graphics2D g2d) {
        System.out.println("Painting Light");
        g2d.setColor(currentLight);
        g2d.fill(trafficLight);
    }

    @Override
    protected void dragHandler(MouseEvent e) {
        System.out.println("Light Dragged");
    }
}
