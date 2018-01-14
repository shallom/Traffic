package TrafficSystem;

import MappingSystem.GridPoint;
import MappingSystem.WorldPositioningSystem;
import Canvas.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static TrafficSystem.GlobalConstants.INITIAL_ROAD_LENGTH;
import static TrafficSystem.GlobalConstants.TWO_WAY_INTERSECTION_WIDTH;

public class Intersection extends Renderable{
    private Rectangle intersection;
    private ArrayList<Road> roads;
    private ArrayList<TrafficLight> trafficLights;
    private Point intersectionPoint;
    private ArrayList<InFillExFill<Road>> trafficSystemRoadFills;


    public Intersection(int numberWayInterSection, int xPos, int yPos){
        updateRenderQue();

        roads = new ArrayList<>();
        intersectionPoint = new Point(xPos, yPos);
        trafficLights = new ArrayList<>();
        trafficSystemRoadFills = new ArrayList<>();
        buildInterSection(numberWayInterSection, intersectionPoint);
    }

    private void buildInterSection(int numberWayIntersection, Point point){
        switch (numberWayIntersection){
            case 2: buildTwoWayInterSection(point);
        }
    }

    private void buildTwoWayInterSection(Point point){
        //Build roads
        Point p = new Point(point);
        Dimension d = new Dimension(TWO_WAY_INTERSECTION_WIDTH, TWO_WAY_INTERSECTION_WIDTH);
        intersection = new Rectangle(new Rectangle(p, d));
        registerArea(p, d);
        Road roadIn1 = new Road("left-right-1", TrafficFlow.LEFT_TO_RIGHT,  point.x - INITIAL_ROAD_LENGTH, point.y, INITIAL_ROAD_LENGTH, TWO_WAY_INTERSECTION_WIDTH);
        roads.add(roadIn1);

        Road roadOut1 = new Road("left-right-2", TrafficFlow.LEFT_TO_RIGHT, point.x + TWO_WAY_INTERSECTION_WIDTH, point.y, INITIAL_ROAD_LENGTH, TWO_WAY_INTERSECTION_WIDTH);
        roads.add(roadOut1);

        Road roadOut2 = new Road("bottom-to-top-1", TrafficFlow.BOTTOM_TO_TOP, point.x, point.y - INITIAL_ROAD_LENGTH, TWO_WAY_INTERSECTION_WIDTH, INITIAL_ROAD_LENGTH); //top
        roads.add(roadOut2);

        Road roadIn2 = new Road("bottom-to-top-2", TrafficFlow.BOTTOM_TO_TOP, point.x, point.y + TWO_WAY_INTERSECTION_WIDTH , TWO_WAY_INTERSECTION_WIDTH, INITIAL_ROAD_LENGTH);
        roads.add(roadIn2);


        //Build traffic controllers
        trafficLights.add(new TrafficLight("left-right-1",point.x + 10, point.y - 5, TWO_WAY_INTERSECTION_WIDTH - 20, 5));
        trafficLights.add(new TrafficLight("bottom-to-top-1",point.x + TWO_WAY_INTERSECTION_WIDTH + 5, point.y + 10, 5, TWO_WAY_INTERSECTION_WIDTH - 20));

        InFillExFill<Road> fill1 = new InFillExFill<>(roadIn1);
        fill1.addExFill(roadOut1);
        fill1.addExFill(roadOut2);

        InFillExFill<Road> fill2 = new InFillExFill<>(roadIn2);
        fill2.addExFill(roadOut2);
        fill2.addExFill(roadOut1);

        trafficSystemRoadFills.add(fill1);
        trafficSystemRoadFills.add(fill2);

    }

    public void getDirection(String fromRoad, Movable.MOVEMENT direction){
    }

    @Override
    public Renderable.TrafficType getType() {
        return TrafficType.INTERSECTION;
    }

    @Override
    public void delete() {
        System.out.println("Deleting Intersection");
        if(!isDeleted()){
            //delete the traffic light
            for(TrafficLight trafficLight : trafficLights){
                trafficLight.delete();
            }
            //delete the intersection grid mapping
            for(InFillExFill inFillExFill : trafficSystemRoadFills){
                Road inFillRoad = (Road)(inFillExFill.getInFill());
                ArrayList exFillRoads = inFillExFill.getExFills();
                int exFillsCount = exFillRoads.size();
                ArrayList<GridPoint> exFillsGridPoints = new ArrayList<>();
                for (Object exFillRoad : exFillRoads) {
                    exFillsGridPoints.add(((Road) exFillRoad).getIntersectionNode(intersectionPoint));
                }
                GridPoint inFillGridPoint = inFillRoad.getIntersectionNode(intersectionPoint);
                for(int count = 0; count < exFillsCount; ++count){
                    WorldPositioningSystem.delete(inFillGridPoint, exFillsGridPoints);
                }
                //delete the roads
                for(Road road : roads){
                    road.delete();
                }
            }
            RenderWorld.delete(this);
            unregisterCanvasArea();
        }
        setDeleted();
    }


    @Override
    public void paint(Graphics2D g2d) {
        System.out.println("painting intersection");
        g2d.setColor(Color.BLACK);
        g2d.fill(intersection);
    }

    @Override
    protected void dragHandler(MouseEvent e) {
        delete();
        RenderWorld.getInstance().repaint();

        //add new intersection
        WorldPositioningSystem.addIntersection(new Intersection(2, e.getX() - TWO_WAY_INTERSECTION_WIDTH / 2, e.getY() - TWO_WAY_INTERSECTION_WIDTH));
        RenderWorld.getInstance().repaint();
    }
}
