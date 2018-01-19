package TrafficSystem;

import java.awt.*;
import java.awt.event.MouseEvent;

import Canvas.*;
import MappingSystem.GridPoint;
import MappingSystem.WorldPositioningSystem;


public class Road extends Renderable{

    private Rectangle roadImage;
    private TrafficFlow flow;
    private String name;
    private GridPoint startNode;
    private GridPoint endNode;

    public Road(String name, TrafficFlow flow, int xPos, int yPos, int length, int width){
        this.name = name;
        Point p = new Point(xPos,yPos);
        Dimension d = new Dimension(length, width);
        roadImage = new Rectangle(p, d);
        registerArea(p, d);
        this.flow = flow;
        updateRenderQue();
        System.out.println("building road");
        if(flow == TrafficFlow.LEFT_TO_RIGHT) {
            startNode = new GridPoint(new Point(xPos, yPos + width / 2), GridPoint.GridPointType.SINGLE_CONNECTOR, false);
            endNode = new GridPoint(new Point(xPos + length, yPos + width / 2), GridPoint.GridPointType.MULTIPLE_CONNECTOR, true);
        }else{
            endNode = new GridPoint(new Point(xPos + width / 2, yPos), GridPoint.GridPointType.MULTIPLE_CONNECTOR, true);
            startNode = new GridPoint(new Point(xPos + width / 2, yPos + width), GridPoint.GridPointType.SINGLE_CONNECTOR, false);
        }
        WorldPositioningSystem.installGridNode(startNode);
        WorldPositioningSystem.installGridNode(endNode);
        WorldPositioningSystem.createPathBetween(endNode, startNode);
    }

    public GridPoint getIntersectionNode(Point intersectionPoint){
        double distance1 = Math.sqrt(Math.pow(intersectionPoint.x - startNode.getPoint().x, 2) + Math.pow(intersectionPoint.y - startNode.getPoint().y, 2));
        double distance2 = Math.sqrt(Math.pow(intersectionPoint.x - endNode.getPoint().x, 2) + Math.pow(intersectionPoint.y - endNode.getPoint().y, 2));
        if(distance1 > distance2){
            return endNode;
        }
        return startNode;
    }

    public GridPoint getEndNode() {
        return endNode;
    }

    public GridPoint getStartNode() {
        return startNode;
    }

    @Override
    public Renderable.TrafficType getType() {
        return TrafficType.ROAD;
    }

    @Override
    public void delete() {
        System.out.println("Deleting Road");
        if(!isDeleted()){
            WorldPositioningSystem.delete(startNode, endNode);
            RenderWorld.delete(this);
            WorldPositioningSystem.delete(startNode);
            WorldPositioningSystem.delete(endNode);
            unregisterCanvasArea();
        }
        setDeleted();
    }

    @Override
    public void render(Graphics2D g2d) {
        System.out.println("painting road");
        g2d.setColor(Color.BLACK);
        g2d.fill(roadImage);
    }

    @Override
    protected void dragHandler(MouseEvent e) {
        System.out.println("Road Dragged");
    }
}
