import java.awt.*;


public class Road extends Landscape{

    private Rectangle roadImage;
    private TrafficFlow flow;
    public Road(TrafficFlow flow, int xPos, int yPos, int length, int width){
        roadImage = new Rectangle(new Point(xPos,yPos), new Dimension(length, width));
        this.flow = flow;
        updateRenderQue(this);
        System.out.println("building road");
    }

    @Override
    public void paint(Graphics2D g2d) {
        System.out.println("painting road");
        g2d.setColor(Color.BLACK);
        g2d.fill(roadImage);
    }
}
