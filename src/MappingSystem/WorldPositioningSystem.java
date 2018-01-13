package MappingSystem;

import TrafficSystem.*;
import Canvas.Renderable;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Stack;


public class WorldPositioningSystem {
    private static WorldPositioningSystem ourInstance = new WorldPositioningSystem();
    private static HashSet<GridPoint> pointGridMapping = new HashSet<>();
    private static ArrayList<Intersection> intersections;
    public static WorldPositioningSystem getInstance() {
        return ourInstance;
    }

    //private static int currentUniqueID = 1;

    public enum WPS_RESULT {ADDED, OCCUPIED, AT_EDGE, OFF_MAP, MOVE}

    private WorldPositioningSystem(){
        intersections = new ArrayList<>();
    }

    public static void addIntersection(Intersection intersection){
        intersections.add(intersection);
    }

    public static void delete(GridPoint startingPoint, GridPoint endPoint){

    }

    public static void delete(GridPoint startingPoint, ArrayList<GridPoint> potentialEndPoints){

    }

    public static void delete(GridPoint startingPoint){

    }

    public static void createPathBetween(GridPoint point1, GridPoint point2){
        //remember to install the points
    }

    public static TrafficFlow determineOrientation(Point point1, Point point2){
        return null;
    }

    public static void installGridNode(GridPoint node){
        pointGridMapping.add(node);
    }

    public static Stack<Point> getInstruction(String currentRoad, Movable.MOVEMENT nextDirection){
        return null;
    }

    private static boolean canMoveTo(GridPoint nextPoint){
        if(nextPoint.isOccupied()){
            return false;
        }
        return true;
    }

//    public static int getUniqueID(){
//        return currentUniqueID++;
//    }


    public static WPS_RESULT move(GridPoint nextPoint){
        if(nextPoint == null){
            return WPS_RESULT.AT_EDGE;
        }
        if(!canMoveTo(nextPoint)){
            return WPS_RESULT.OCCUPIED;
        }
        return WPS_RESULT.MOVE;
    }

    public static void installRenderable(ArrayList<GridPoint> objectLocation, Renderable object){
        for(GridPoint point : objectLocation){
            point.setForeGroundObject(object);
            WPS_RESULT result;
            do{
                result = move(point);
            }while (result == WPS_RESULT.OCCUPIED);
        }
    }

}
