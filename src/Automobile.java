import java.awt.*;
import java.util.ArrayList;
import java.util.Stack;

public class Automobile extends Renderable implements TrafficControlListener, Movable {

    private Stack<Point> path;
    private Point curLocation;
    private Dimension dimension;
    private int uniqueID;

    public Automobile(Stack<Point> path, TrafficFlow direction) throws OffTheGridException {
        this.path = path;
        if(direction == TrafficFlow.LEFT_TO_RIGHT){
            dimension = new Dimension(300, 100);
        }else {
            dimension = new Dimension(100, 300);
        }
        curLocation = path.pop();
        uniqueID = WorldPositioningSystem.getUniqueID();
        WorldPositioningSystem.WPS_RESULT result;
        do{
            result = WorldPositioningSystem.addToWorld(curLocation, dimension, uniqueID);
            if(result == WorldPositioningSystem.WPS_RESULT.OFF_MAP){
                throw new OffTheGridException("Automobile position is of grid");
            }
        } while(result == WorldPositioningSystem.WPS_RESULT.OCCUPIED);
    }

    public void setNewPath(Stack<Point> path){
        this.path = path;
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
        if(nextPoint.x == curLocation.x){
            //moving left to right
            if(dimension.width < dimension.height){
                swap();
            }
        }else if(nextPoint.y == curLocation.y){
            //moving up and down
            if(dimension.width > dimension.height){
                swap();
            }
        }
    }

    @Override
    public void move() {
        Point nextLocation = path.peek();
        Dimension savedCopyOfDimension = dimension;
        calibrateDimension(nextLocation);
        WorldPositioningSystem.WPS_RESULT result = WorldPositioningSystem.move(curLocation, nextLocation, savedCopyOfDimension, dimension, uniqueID);
        if(result == WorldPositioningSystem.WPS_RESULT.MOVED){
            curLocation = nextLocation;
            path.pop();
        }else if(result == WorldPositioningSystem.WPS_RESULT.OCCUPIED){
            dimension = savedCopyOfDimension;
        }else if(result == WorldPositioningSystem.WPS_RESULT.AT_EDGE){
            dimension.height = dimension.height / 2;
            dimension.width = dimension.width / 2;
        }
    }
}
