package za.co.wethinkcode.toyrobot.world;

/**
 * SquareObstacle class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import za.co.wethinkcode.toyrobot.Position;

/**
 * A child class of the RectangleObstacle. 
 * Makes an obstacle with 2 right angled corners of equal width and height
 */
public class SquareObstacle extends RectangleObstacle{
    protected static int DEFAULT_SIZE = 5;

    protected final int size;


    public SquareObstacle(int x, int y) {
        super(
            new Position(x, y),
            new Position(x+DEFAULT_SIZE-1, y+DEFAULT_SIZE-1)
        );
        size = DEFAULT_SIZE;
    }

    public SquareObstacle(Position bottomLeftPosition) {
        super(
            bottomLeftPosition, 
            new Position(
                bottomLeftPosition.getX()+DEFAULT_SIZE-1,
                bottomLeftPosition.getY()+DEFAULT_SIZE-1)
        );
        size = DEFAULT_SIZE;
    }

    public SquareObstacle(Position bottomLeftPosition, int size) {
        super(
            bottomLeftPosition, 
            new Position(
                bottomLeftPosition.getX()+size-1,
                bottomLeftPosition.getY()+size-1)
        );
        this.size = size;
    }


    @Override
    public int getSize() { 
        return size; 
    }
}
