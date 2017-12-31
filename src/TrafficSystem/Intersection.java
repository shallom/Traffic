package TrafficSystem;

import MappingSystem.GridPoint;
import MappingSystem.WorldPositioningSystem;
import Canvas.Renderable;

import java.awt.*;
import java.util.ArrayList;

import static TrafficSystem.GlobalConstants.INITIAL_ROAD_LENGTH;
import static TrafficSystem.GlobalConstants.TWO_WAY_INTERSECTION_WIDTH;

public class Intersection extends Renderable{
    private Rectangle intersection;
    private ArrayList<Road> roads;
    private ArrayList<TrafficController> trafficControllers;
    private GridPoint intersectionNode;

    public Intersection(int numberWayInterSection, int xPos, int yPos){
        updateRenderQue(this);
        roads = new ArrayList<>();
        trafficControllers = new ArrayList<>();
        buildInterSection(numberWayInterSection, xPos, yPos);
    }

    private void buildInterSection(int numberWayIntersection, int xPos, int yPos){
        switch (numberWayIntersection){
            case 2: buildTwoWayInterSection(xPos, yPos);
        }
    }

    private void buildTwoWayInterSection(int xPos, int yPos){
        intersectionNode = new GridPoint(new Point(xPos + TWO_WAY_INTERSECTION_WIDTH / 2, yPos + TWO_WAY_INTERSECTION_WIDTH / 2), GridPoint.GridPointType.IntersectionPoint);
        intersectionNode = new GridPoint(new Point(xPos + TWO_WAY_INTERSECTION_WIDTH / 2, yPos + TWO_WAY_INTERSECTION_WIDTH / 2), GridPoint.GridPointType.IntersectionPoint);
        WorldPositioningSystem.installGridNode(intersectionNode);
        //Build roads
        intersection = new Rectangle(new Rectangle(new Point(xPos,yPos), new Dimension(TWO_WAY_INTERSECTION_WIDTH, TWO_WAY_INTERSECTION_WIDTH)));
        Road road = new Road("left-right-1", TrafficFlow.LEFT_TO_RIGHT,  xPos - INITIAL_ROAD_LENGTH, yPos, INITIAL_ROAD_LENGTH, TWO_WAY_INTERSECTION_WIDTH);
        roads.add(road);
        WorldPositioningSystem.createPathBetween(road.getIntersectionNode(intersectionNode), intersectionNode);

        road = new Road("left-right-2", TrafficFlow.LEFT_TO_RIGHT, xPos + TWO_WAY_INTERSECTION_WIDTH, yPos, INITIAL_ROAD_LENGTH, TWO_WAY_INTERSECTION_WIDTH);
        roads.add(road);
        WorldPositioningSystem.createPathBetween(road.getIntersectionNode(intersectionNode), intersectionNode);

        road = new Road("bottom-to-top-1", TrafficFlow.BOTTOM_TO_TOP, xPos, yPos - INITIAL_ROAD_LENGTH, TWO_WAY_INTERSECTION_WIDTH, INITIAL_ROAD_LENGTH); //top
        roads.add(road);
        WorldPositioningSystem.createPathBetween(road.getIntersectionNode(intersectionNode), intersectionNode);

        road = new Road("bottom-to-top-2", TrafficFlow.BOTTOM_TO_TOP, xPos, yPos + TWO_WAY_INTERSECTION_WIDTH , TWO_WAY_INTERSECTION_WIDTH, INITIAL_ROAD_LENGTH);
        roads.add(road);
        WorldPositioningSystem.createPathBetween(road.getIntersectionNode(intersectionNode), intersectionNode);

        //Build traffic controllers
        trafficControllers.add(new TrafficLight("left-right-1",xPos + 10, yPos - 5, TWO_WAY_INTERSECTION_WIDTH - 20, 5));
        trafficControllers.add(new TrafficLight("bottom-to-top-1",xPos + TWO_WAY_INTERSECTION_WIDTH + 5, yPos + 10, 5, TWO_WAY_INTERSECTION_WIDTH - 20));
    }

    public void getDirection(String fromRoad, Movable.MOVEMENT direction){

    }

    @Override
    public Renderable.TrafficType getType() {
        return TrafficType.INTERSECTION;
    }

    @Override
    public void paint(Graphics2D g2d) {
        System.out.println("painting intersection");
        g2d.setColor(Color.BLACK);
        g2d.fill(intersection);
    }
}
