import java.awt.*;


public class WorldPositioningSystem {
    private static WorldPositioningSystem ourInstance = new WorldPositioningSystem();
    public static WorldPositioningSystem getInstance() {
        return ourInstance;
    }

    private static int[][] worldGrid;
    private static int worldWidth;
    private static int worldHeight;
    private static int currentUniqueID = 1;

    public enum WPS_RESULT {ADDED, OCCUPIED, AT_EDGE, OFF_MAP, MOVED}

    private WorldPositioningSystem(){
    }

    public static void setWorldDimensions(int width, int height){
        worldHeight = height;
        worldWidth = width;
        worldGrid = new int[height][width];
        for(int curRow = 0; curRow < height; ++curRow){
            for(int curCol = 0; curCol < width; ++curCol){
                worldGrid[curRow][curCol] = 0;
            }
        }
    }

    private static boolean canFitOnWorld(Point point, Dimension dimension){
        if(worldHeight <= point.y){
            return false;
        }
        if(worldWidth <= point.x){
            return false;
        }
        if(worldHeight <= point.y + dimension.height){
            return false;
        }
        if(worldWidth <= point.x + dimension.width){
            return false;
        }
        if(point.x < 0 || point.y < 0){
            return false;
        }
        return true;
    }

    private static void alterWorld(Point point, Dimension dimension, int uniqueID){
        for(int curRow = point.y; curRow < dimension.height; ++curRow){
            for(int curCol = point.x; curCol < dimension.width; ++curCol){
                worldGrid[curRow][curCol] = uniqueID;
            }
        }
    }

    private static boolean canMoveTo(Point point, Dimension dimension, int uniqueID){
        for(int curRow = point.y; curRow < dimension.height; ++curRow){
            for(int curCol = point.x; curCol < dimension.width; ++curCol){
                if(worldGrid[curRow][curCol] != uniqueID || worldGrid[curRow][curCol] != 0){
                    return false;
                }
            }
        }
        return true;
    }

    public static int getUniqueID(){
        return currentUniqueID++;
    }

    public static WPS_RESULT addToWorld(Point point, Dimension dimension, int uniqueID){
        if(!canFitOnWorld(point, dimension)){
            return WPS_RESULT.OFF_MAP;
        }
        alterWorld(point, dimension, uniqueID);
        return WPS_RESULT.ADDED;
    }

    public static WPS_RESULT move(Point oldPoint, Point newPoint, Dimension oldDimension, Dimension newDimension, int uniqueID){
        if(!canFitOnWorld(newPoint, newDimension)){
            return WPS_RESULT.AT_EDGE;
        }
        if(!canMoveTo(newPoint, newDimension, uniqueID)){
            return WPS_RESULT.OCCUPIED;
        }
        alterWorld(oldPoint, oldDimension, 0);
        alterWorld(newPoint, newDimension, uniqueID);
        return WPS_RESULT.MOVED;
    }


}
