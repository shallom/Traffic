package MappingSystem;

import java.awt.*;
import java.util.ArrayList;

public class GridPoint {

    private Point point;
    private ArrayList<GridPoint> adjacentPoint;
    private GridPointType gridPointType;

    public enum GridPointType {ConnectorPoint, RoadEndPoint, IntersectionPoint}


    public GridPoint(Point point, GridPointType gridPointType){
        this.gridPointType = gridPointType;
        this.point = point;
        adjacentPoint = new ArrayList<>();
    }



    public GridPoint getAdjacentPoint(){
        return adjacentPoint.get(0);
    }

    public ArrayList<GridPoint> getAdjacentPoints() {
        return adjacentPoint;
    }

    public GridPointType getGridPointType() {
        return gridPointType;
    }

    public void addAdjacentNode(GridPoint node){
        adjacentPoint.add(node);
    }

    public Point getPoint() {
        return point;
    }
}
