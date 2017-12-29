import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;
import java.util.concurrent.ThreadLocalRandom;

public class Automobile extends Renderable implements TrafficControlListener, Movable {

    private GridPoint curLocation;
    private Dimension dimension;
    private int uniqueID;
    private String currentRoad;

    public Automobile(String currentRoad, GridPoint curLocation) throws OffTheGridException {
        this.currentRoad = currentRoad;
        TrafficFlow direction = WorldPositioningSystem.determineOrientation(curLocation.getPoint(), curLocation.getAdjacentPoint().getPoint());
        if(direction == TrafficFlow.LEFT_TO_RIGHT){
            dimension = new Dimension(300, 100);
        }else {
            dimension = new Dimension(100, 300);
        }
        this.curLocation = curLocation;
        uniqueID = WorldPositioningSystem.getUniqueID();
        WorldPositioningSystem.WPS_RESULT result;
        do{
            result = WorldPositioningSystem.addToWorld(curLocation.getPoint(), dimension, uniqueID);
            if(result == WorldPositioningSystem.WPS_RESULT.OFF_MAP){
                throw new OffTheGridException("Automobile position is of grid");
            }
        } while(result == WorldPositioningSystem.WPS_RESULT.OCCUPIED);
    }



    @Override
    public void paint(Graphics2D g2d) {

    }

    @Override
    public void onNewTrafficEvent(Instruction trafficInstruction) {

    }

    private void swap(){
        int temp = dimension.height;
        dimension.height = dimension.width;
        dimension.width = temp;
    }

    private void calibrateDimension(Point nextPoint){
        if(nextPoint.x == curLocation.getPoint().x){
            //moving left to right
            if(dimension.width < dimension.height){
                swap();
            }
        }else if(nextPoint.y == curLocation.getPoint().y){
            //moving up and down
            if(dimension.width > dimension.height){
                swap();
            }
        }
    }

    private void drive(GridPoint nextLocation){
        Dimension savedCopyOfDimension = dimension;
        calibrateDimension(nextLocation.getPoint());
        WorldPositioningSystem.WPS_RESULT result = WorldPositioningSystem.move(curLocation.getPoint(), nextLocation.getPoint(), savedCopyOfDimension, dimension, uniqueID);
        if (result == WorldPositioningSystem.WPS_RESULT.MOVED) {
            curLocation = nextLocation;
        } else if (result == WorldPositioningSystem.WPS_RESULT.OCCUPIED) {
            dimension = savedCopyOfDimension;
        } else if (result == WorldPositioningSystem.WPS_RESULT.AT_EDGE) {
            dimension.height = dimension.height / 2;
            dimension.width = dimension.width / 2;
        }
    }

    private GridPoint determineNextPoint(){
        ArrayList<GridPoint> gridPoints = curLocation.getAdjacentPoints();
        return gridPoints.get(ThreadLocalRandom.current().nextInt(0, gridPoints.size()));
    }

    @Override
    public void move() {
        if(curLocation.getGridPointType() == GridPoint.GridPointType.IntersectionPoint || curLocation.getGridPointType() == GridPoint.GridPointType.ConnectorPoint ||(curLocation.getGridPointType() == GridPoint.GridPointType.RoadEndPoint && TrafficLight.getTrafficOrder(currentRoad) == Instruction.GO)){
            drive(determineNextPoint());
        }
    }
}
