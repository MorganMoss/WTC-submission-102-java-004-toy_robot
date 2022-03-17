package za.co.wethinkcode.toyrobot;

/**
 * Direction class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

/**
 * A Direction object, uses NESW, has an x and y to co-ordinate which direction
 * something must be moved on a 2D plane.
 */   
public enum Direction {
    NORTH (0,1), 
    EAST (1,0), 
    SOUTH (0,-1), 
    WEST (-1,0),
    UP (0,1), 
    RIGHT (1,0), 
    DOWN (0,-1), 
    LEFT (-1,0);

    private int x;
    private int y;

    Direction(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public static Direction left(Direction direction){
        switch(direction){
            case NORTH : return WEST;
            case EAST: return  NORTH;
            case SOUTH: return EAST;
            case WEST: return SOUTH;
            case UP : return LEFT;
            case RIGHT: return  UP;
            case DOWN: return RIGHT;
            case LEFT: return DOWN;
            default: return null;
        }
    }

    public static Direction right(Direction direction){
        switch(direction){
            case NORTH : return EAST;
            case EAST: return  SOUTH;
            case SOUTH: return WEST;
            case WEST: return NORTH;
            case UP : return RIGHT;
            case RIGHT: return DOWN;
            case DOWN: return  LEFT;
            case LEFT: return  UP;
            default: return null;
        }
    }
}
