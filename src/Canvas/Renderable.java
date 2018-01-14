package Canvas;

import java.awt.*;


public abstract class Renderable extends MouseEventHandler {

    private boolean deleted = false;
    private RegisterCanvasArea registeredCanvasArea;

    protected void setDeleted(){
        deleted = true;
    }

    public boolean isDeleted(){
        return deleted;
    }

    public void updateRenderQue(){
        RenderWorld.addToRenderQue(this);
    }

    public void registerArea(Point p, Dimension d){
        registeredCanvasArea = RegisterCanvasArea.RegisterCanvasArea(p, d, this);
    }

    public void unregisterCanvasArea(){
        RegisterCanvasArea.delete(registeredCanvasArea);
    }

    public enum TrafficType {TRAFFIC_LIGHT, INTERSECTION, ROAD, AUTOMOBILE}

    public abstract TrafficType getType();

    public abstract void delete();

    public abstract void paint(Graphics2D g2d);

}
