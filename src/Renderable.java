
import java.awt.*;

public abstract class Renderable {

    public void updateRenderQue(Renderable object){
        RenderCanvas.addToRenderQue(object);
    }

    public abstract void paint(Graphics2D g2d);

}
