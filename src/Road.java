import java.awt.*;


public class Road extends Landscape{

    private Rectangle roadImage;
    private TrafficFlow flow;
    private String name;
    private GridPoint startNode;
    private GridPoint endNode;

    public Road(String name, TrafficFlow flow, int xPos, int yPos, int length, int width){
        this.name = name;
        roadImage = new Rectangle(new Point(xPos,yPos), new Dimension(length, width));
        this.flow = flow;
        updateRenderQue(this);
        System.out.println("building road");
        if(flow == TrafficFlow.LEFT_TO_RIGHT) {
            startNode = new GridPoint(new Point(xPos, yPos + width / 2), GridPoint.GridPointType.ConnectorPoint);
            endNode = new GridPoint(new Point(xPos + length, yPos + width / 2), GridPoint.GridPointType.RoadEndPoint);
        }else{
            endNode = new GridPoint(new Point(xPos + width / 2, yPos), GridPoint.GridPointType.RoadEndPoint);
            startNode = new GridPoint(new Point(xPos + width / 2, yPos + width), GridPoint.GridPointType.ConnectorPoint);
        }
        WorldPositioningSystem.installGridNode(startNode);
        WorldPositioningSystem.installGridNode(endNode);
        WorldPositioningSystem.createPathBetween(endNode, startNode);
    }

    public GridPoint getIntersectionNode(GridPoint intersectionNode){
        double distance1 = Math.sqrt(Math.pow(intersectionNode.getPoint().x - startNode.getPoint().x, 2) + Math.pow(intersectionNode.getPoint().y - startNode.getPoint().y, 2));
        double distance2 = Math.sqrt(Math.pow(intersectionNode.getPoint().x - endNode.getPoint().x, 2) + Math.pow(intersectionNode.getPoint().y - endNode.getPoint().y, 2));
        if(distance1 > distance2){
            return endNode;
        }
        return startNode;
    }

    @Override
    public void paint(Graphics2D g2d) {
        System.out.println("painting road");
        g2d.setColor(Color.BLACK);
        g2d.fill(roadImage);
    }
}
