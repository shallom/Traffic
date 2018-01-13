package Canvas;

import org.jetbrains.annotations.Contract;

import java.awt.*;
import java.util.ArrayList;

public class RegisterCanvasArea {

    private static ArrayList<RegisterCanvasArea> registeredCanvasAreas = new ArrayList<>();

    private Point p;
    private Dimension d;
    private Renderable r;

    private RegisterCanvasArea(Point p, Dimension d, Renderable r){
        this.p = p;
        this.d = d;
        this.r = r;
    }

    public static RegisterCanvasArea RegisterCanvasArea(Point p, Dimension d, Renderable r){
        RegisterCanvasArea newArea = new RegisterCanvasArea(p, d, r);
        registeredCanvasAreas.add(newArea);
        return newArea;
    }

    public Renderable getRenderable(){
        return r;
    }

    public static void delete(RegisterCanvasArea registeredCanvasArea){
        registeredCanvasAreas.remove(registeredCanvasArea);
    }

    @Contract(pure = true)
    public static RegisterCanvasArea getFocusedArea(int x, int y){
        for(RegisterCanvasArea registeredArea : registeredCanvasAreas) {
            if (registeredArea.p.x <= x && x <= registeredArea.p.x + registeredArea.d.width && registeredArea.p.y <= y && y <= registeredArea.p.y + registeredArea.d.height) {
                return registeredArea;
            }
        }
        return null;
    }

}
