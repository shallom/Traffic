package MappingSystem;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

import Canvas.Renderable;

public class GridPoint {

    private Point point;
    private ArrayList<GridPoint> adjacentPoints;
    private GridPoint adjacentPoint;
    private GridPointType gridPointType;
    private boolean isTrafficDependent;
    private Renderable currentForeGroundObject;

    public enum GridPointType {SINGLE_CONNECTOR, MULTIPLE_CONNECTOR}


    public GridPoint(Point point, GridPointType gridPointType, boolean isTrafficDependent){
        this.gridPointType = gridPointType;
        this.point = point;
        adjacentPoints = new ArrayList<>();
        this.isTrafficDependent = isTrafficDependent;
        currentForeGroundObject = null;
    }

    public void setForeGroundObject(Renderable foreGroundObject){
        currentForeGroundObject = foreGroundObject;
    }

    public boolean isOccupied (){
        return currentForeGroundObject != null;
    }

    public GridPoint getAdjacentPoint(){
        if(gridPointType == GridPointType.SINGLE_CONNECTOR) {
            return adjacentPoint;
        }else{
            return adjacentPoints.get(ThreadLocalRandom.current().nextInt(0, adjacentPoints.size()));
        }
    }

    public boolean isTrafficDependent() {
        return isTrafficDependent;
    }

    public GridPointType getGridPointType() {
        return gridPointType;
    }

    public ArrayList<GridPoint> getAdjacentPoints() {
        return adjacentPoints;
    }

    public void setAdjacentPoint(GridPoint adjacentPoint){
        this.adjacentPoint = adjacentPoint;
    }

    public void addAdjacentNode(GridPoint node){
        adjacentPoints.add(node);
    }

    public Point getPoint() {
        return point;
    }
}
