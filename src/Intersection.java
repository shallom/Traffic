import java.awt.*;
import java.util.ArrayList;

public class Intersection extends Landscape {
    private Rectangle intersection;
    private ArrayList<Road> roads;
    private ArrayList<TrafficController> trafficControllers;

    public Intersection(int numberWayInterSection, int xPos, int yPos, int width){
        intersection = new Rectangle(new Rectangle(new Point(xPos,yPos), new Dimension(width, width)));
        updateRenderQue(this);
        roads = new ArrayList<>();
        trafficControllers = new ArrayList<>();
        buildInterSection(numberWayInterSection, xPos, yPos, width);
    }

    private void buildInterSection(int numberWayIntersection, int xPos, int yPos, int width){
        switch (numberWayIntersection){
            case 1: buildOneWayInterSection(xPos, yPos, width);
        }
    }

    private void buildOneWayInterSection(int xPos, int yPos, int width){
        int length = 500;
        //Build roads
        roads.add(new Road(TrafficFlow.LEFT_TO_RIGHT,  xPos - length, yPos, length, width));
        roads.add(new Road(TrafficFlow.LEFT_TO_RIGHT, xPos + width, yPos, length, width));
        roads.add(new Road(TrafficFlow.BOTTOM_TO_TOP, xPos, yPos - length, width, length)); //top
        roads.add(new Road(TrafficFlow.BOTTOM_TO_TOP, xPos, yPos + width , width, length));

        //Build traffic controllers
        trafficControllers.add(new TrafficLight(xPos + 10, yPos - 5, width - 20, 5));
        trafficControllers.add(new TrafficLight(xPos + width + 5, yPos + 10, 5, width - 20));
    }

    @Override
    public void paint(Graphics2D g2d) {
        System.out.println("painting intersection");
        g2d.setColor(Color.BLACK);
        g2d.fill(intersection);
    }
}
