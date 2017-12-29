import java.awt.*;
import java.util.ArrayList;

public class Intersection extends Landscape {
    private Rectangle intersection;
    private ArrayList<Road> roads;
    private ArrayList<TrafficController> trafficControllers;
    private GridPoint intersectionNode;

    public Intersection(int numberWayInterSection, int xPos, int yPos, int width){
        intersection = new Rectangle(new Rectangle(new Point(xPos,yPos), new Dimension(width, width)));
        updateRenderQue(this);
        roads = new ArrayList<>();
        trafficControllers = new ArrayList<>();
        buildInterSection(numberWayInterSection, xPos, yPos, width);
        intersectionNode = new GridPoint(new Point(xPos + width / 2, yPos + width / 2), GridPoint.GridPointType.IntersectionPoint);
        WorldPositioningSystem.installGridNode(intersectionNode);
    }

    private void buildInterSection(int numberWayIntersection, int xPos, int yPos, int width){
        switch (numberWayIntersection){
            case 1: buildOneWayInterSection(xPos, yPos, width);
        }
    }

    private void buildOneWayInterSection(int xPos, int yPos, int width){
        int length = 500;
        //Build roads
        Road road = new Road("left-right-1", TrafficFlow.LEFT_TO_RIGHT,  xPos - length, yPos, length, width);
        roads.add(road);
        WorldPositioningSystem.createPathBetween(road.getIntersectionNode(intersectionNode), intersectionNode);

        road = new Road("left-right-2", TrafficFlow.LEFT_TO_RIGHT, xPos + width, yPos, length, width);
        roads.add(road);
        WorldPositioningSystem.createPathBetween(road.getIntersectionNode(intersectionNode), intersectionNode);

        road = new Road("bottom-to-top-1", TrafficFlow.BOTTOM_TO_TOP, xPos, yPos - length, width, length); //top
        roads.add(road);
        WorldPositioningSystem.createPathBetween(road.getIntersectionNode(intersectionNode), intersectionNode);

        road = new Road("bottom-to-top-2", TrafficFlow.BOTTOM_TO_TOP, xPos, yPos + width , width, length);
        roads.add(road);
        WorldPositioningSystem.createPathBetween(road.getIntersectionNode(intersectionNode), intersectionNode);

        //Build traffic controllers
        trafficControllers.add(new TrafficLight("left-right-1",xPos + 10, yPos - 5, width - 20, 5));
        trafficControllers.add(new TrafficLight("bottom-to-top-1",xPos + width + 5, yPos + 10, 5, width - 20));

    }

    public void getDirection(String fromRoad, Movable.MOVEMENT direction){

    }

    @Override
    public void paint(Graphics2D g2d) {
        System.out.println("painting intersection");
        g2d.setColor(Color.BLACK);
        g2d.fill(intersection);
    }
}
