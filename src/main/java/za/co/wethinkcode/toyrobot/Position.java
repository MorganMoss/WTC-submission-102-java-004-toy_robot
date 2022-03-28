package za.co.wethinkcode.toyrobot;
/**
 * Position class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

/**
 * A data type representing a co-ordinate on a 2D plane.
 */
public class Position {
    private final int x;
    private final int y;


    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }
    
    @Override
    public String toString() {
        return x + "," + y;
    }


    /**
     * Checks if a rectange with the given corners overlaps with this position
     * @param bottomLeft : bottom left corner
     * @param topRight : top right corner
     * @return true if they overlap, otherwise false.
     */
    public boolean isIn(Position bottomLeft, Position topRight) {
        boolean withinTop = this.y <= topRight.getY();
        boolean withinBottom = this.y >= bottomLeft.getY();
        boolean withinLeft = this.x <= topRight.getX();
        boolean withinRight = this.x >= bottomLeft.getX();
        return withinTop && withinBottom && withinLeft && withinRight;
    }
}
