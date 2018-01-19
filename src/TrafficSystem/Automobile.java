package TrafficSystem;

import MappingSystem.GridPoint;
import MappingSystem.WorldPositioningSystem;
import Canvas.Renderable;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Automobile extends Renderable implements TrafficControlListener, Movable {

    private ArrayList<GridPoint> curLocation;
    private Rectangle automobileImage;
    private String currentRoad;
    private boolean rotate;
    private double degreeToRotate;

    public Automobile(String currentRoad, ArrayList<GridPoint> curLocation){
        rotate = false;
        updateRenderQue();
        this.currentRoad = currentRoad;
        this.curLocation = curLocation;
        TrafficFlow direction = WorldPositioningSystem.determineOrientation(curLocation.get(0).getPoint(), curLocation.get(curLocation.size()- 1).getPoint());
        if(direction == TrafficFlow.LEFT_TO_RIGHT){
            automobileImage = new Rectangle(new Point(curLocation.get(curLocation.size() -1).getPoint().x, curLocation.get(curLocation.size() -1).getPoint().y - (GlobalConstants.CAR_WIDTH / 2)),
                    new Dimension(GlobalConstants.CAR_LENGTH, GlobalConstants.CAR_WIDTH));
        }else {
            automobileImage = new Rectangle(new Point(curLocation.get(curLocation.size() - 1).getPoint().x - (GlobalConstants.CAR_WIDTH / 2), curLocation.get(curLocation.size() - 1).getPoint().y),
                    new Dimension(GlobalConstants.CAR_WIDTH, GlobalConstants.CAR_LENGTH));
        }
        WorldPositioningSystem.installRenderable(curLocation, this);
    }


    @Override
    public Renderable.TrafficType getType() {
        return null;
    }

    @Override
    public void delete() {

    }

    @Override
    public void render(Graphics2D g2d) {
        System.out.println("painting automobile");
        g2d.setColor(Color.BLACK);
        g2d.fill(automobileImage);
        if(rotate){
            Point origin = curLocation.get(curLocation.size() / 2).getPoint();
            g2d.rotate(degreeToRotate, origin.x, origin.y);
            rotate = false;
        }
    }

    @Override
    public void onNewTrafficEvent(Instruction trafficInstruction) {

    }

    private void calibrateDimension(Point nextPoint){
        Point origin = curLocation.get(curLocation.size() / 2).getPoint();
        Point savedNextPoint = new Point(nextPoint);
        nextPoint.x -= origin.x;
        nextPoint.y -= origin.y;
        nextPoint.x = Math.abs(nextPoint.x);
        nextPoint.y = Math.abs(nextPoint.y);
        if(savedNextPoint.y < origin.y || savedNextPoint.x < origin .y){
            rotate = true;
            degreeToRotate = Math.toDegrees(Math.atan2(nextPoint.x, nextPoint.y));
        }else if(savedNextPoint.y > origin.y || savedNextPoint.x > origin .y){
            rotate = true;
            degreeToRotate = -Math.toDegrees(Math.atan2(nextPoint.x, nextPoint.y));
        }
    }

    private void drive(GridPoint nextLocation){
        calibrateDimension(nextLocation.getPoint());
        WorldPositioningSystem.WPS_RESULT result = WorldPositioningSystem.move(nextLocation);
        if (result == WorldPositioningSystem.WPS_RESULT.MOVE) {
            GridPoint oldLastPoint = curLocation.get(0);
            oldLastPoint.setForeGroundObject(null);
            curLocation.remove(oldLastPoint);
            nextLocation.setForeGroundObject(this);
            curLocation.add(nextLocation);
        } else if (result == WorldPositioningSystem.WPS_RESULT.OCCUPIED) {
            rotate = false;
        } else if (result == WorldPositioningSystem.WPS_RESULT.AT_EDGE) {
            if(curLocation.get(curLocation.size() - 1).getPoint().x == curLocation.get(0).getPoint().x) {
                //moving up and done
                automobileImage.height = automobileImage.height / 2;
            }else if(curLocation.get(curLocation.size() - 1).getPoint().y == curLocation.get(0).getPoint().y) {
                //moving left to right
                automobileImage.width = automobileImage.width / 2;
            }
        }
    }

    private GridPoint determineNextPoint(){
        return curLocation.get(curLocation.size() - 1).getAdjacentPoint();
    }

    @Override
    public void move() {
        if(!curLocation.get(curLocation.size() - 1).isTrafficDependent() ||
                (curLocation.get(curLocation.size() - 1).isTrafficDependent() &&
                        TrafficLight.getTrafficInstruction(currentRoad) == Instruction.GO)){
            drive(determineNextPoint());
        }
    }

    @Override
    protected void dragHandler(MouseEvent e) {

    }
}
