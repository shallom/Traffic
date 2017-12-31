package Canvas;

import java.awt.*;


public abstract class Renderable {

    public void updateRenderQue(Renderable object){
        RenderWorld.addToRenderQue(object);
    }

    public enum TrafficType {TRAFFIC_LIGHT, INTERSECTION, ROAD, AUTOMOBILE}

    public abstract TrafficType getType();

    public abstract void paint(Graphics2D g2d);

}
