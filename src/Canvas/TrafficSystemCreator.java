package Canvas;

import GUI.BuildGui;
import MappingSystem.WorldPositioningSystem;
import TrafficSystem.Intersection;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import static TrafficSystem.GlobalConstants.TWO_WAY_INTERSECTION_WIDTH;

public class TrafficSystemCreator extends JPanel implements MouseListener, MouseMotionListener {

    private static TrafficSystemCreator ourInstance = new TrafficSystemCreator();
    private static RegisterCanvasArea focusedArea;
    public static TrafficSystemCreator getInstance(){
        return ourInstance;
    }

    private TrafficSystemCreator(){
//        JPanel PnlTwo = new JPanel(new FlowLayout());
//        JButton BtnFour = new JButton("Four");
//        JButton BtnFive = new JButton("Five");
//        JButton BtnSix = new JButton("Six");
//        PnlTwo.add(BtnFour);
//        PnlTwo.add(BtnFive);
//        PnlTwo.add(BtnSix);
    }

    /**
     * Invoked when the mouse button has been clicked (pressed
     * and released) on a component.
     *
     * @param e
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        // e.getX(), e.getY());
        System.out.println("In traffic: x:y -> " + e.getX() + ":" + e.getY());
        WorldPositioningSystem.addIntersection(new Intersection(2, e.getPoint().x, e.getPoint().y ));
        RenderWorld.getInstance().repaint();
    }

    /**
     * Invoked when a mouse button has been pressed on a component.
     *
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
        System.out.println("Mouse Pressed");
        focusedArea = RegisterCanvasArea.getFocusedArea(e.getX(), e.getY());
    }

    /**
     * Invoked when a mouse button has been released on a component.
     *
     * @param e
     */
    @Override
    public void mouseReleased(MouseEvent e) {

    }

    /**
     * Invoked when the mouse enters a component.
     *
     * @param e
     */
    @Override
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e
     */
    @Override
    public void mouseExited(MouseEvent e) {

    }

    /**
     * Invoked when a mouse button is pressed on a component and then
     * dragged.  <code>MOUSE_DRAGGED</code> events will continue to be
     * delivered to the component where the drag originated until the
     * mouse button is released (regardless of whether the mouse position
     * is within the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * <code>MOUSE_DRAGGED</code> events may not be delivered during a native
     * Drag&amp;Drop operation.
     *
     * @param e
     */
    @Override
    public void mouseDragged(MouseEvent e) {
        //This class should not be in this package
        System.out.println("Mouse Dragged");
        if(focusedArea != null) {
            focusedArea.getRenderable().dragHandler(e);
        }
        focusedArea = RegisterCanvasArea.getFocusedArea(e.getX(), e.getY());
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component
     * but no buttons have been pushed.
     *
     * @param e
     */
    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
