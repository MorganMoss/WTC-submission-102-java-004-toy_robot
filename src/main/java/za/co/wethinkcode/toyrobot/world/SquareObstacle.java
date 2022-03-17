package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

public class SquareObstacle implements Obstacle{

    private final Position position;
    private final int size;


    public SquareObstacle(int x, int y) {
        position = new Position(x, y);
        this.size = 5;
    }

    public SquareObstacle(Position position) {
        this.position = position;
        this.size = 5;
    }

    public SquareObstacle(Position position, int size) {
        this.position = position;
        this.size = size;
    }


    public Position getPosition() { return position; }

    @Override
    public int getBottomLeftX() { return position.getX(); }

    @Override
    public int getBottomLeftY() { return position.getY(); }

    @Override
    public int getSize() { return size; }

    /**
     * Checks if a position is blocked by an obstacle 
     * @param position : The position to be checked
     * @return true if blocked
     */
    @Override
    public boolean blocksPosition(Position position) {
        return position.isIn(
            new Position(this.position.getX(), this.position.getY()+size-1),
            new Position(this.position.getX()+size-1, this.position.getY())
        );
    }

    /**
     * Checks if a path is blocked by this obstacle
     * @param start The initial position to be checked
     * @param end The final position to be checked
     * @return true if blocked
     */
    @Override
    public boolean blocksPath(Position start, Position end) {
        int step = 1;
        if ((start.getY() > end.getY()) || (start.getX() > end.getX()) ){
            step = -1;
        }

        if (start.getX() == end.getX()){
            final int x = start.getX();
            for (int y = start.getY(); y != end.getY() ; y += step)
                if (this.blocksPosition(new Position(x, y)))
                    return true;
        } else {
            final int y = start.getY();
            for (int x = start.getX(); x != end.getX() ; x += step)
                if (this.blocksPosition(new Position(x, y)))
                    return true;
        }

        return false;
    }

}
