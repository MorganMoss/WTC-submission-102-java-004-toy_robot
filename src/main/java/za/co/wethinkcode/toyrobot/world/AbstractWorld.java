package za.co.wethinkcode.toyrobot.world;

import java.util.List;

import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.Position;

public abstract class AbstractWorld implements IWorld {
    private final Maze maze;

    protected static Position BOTTOM_LEFT = new Position(-100, -200);
    protected static Position TOP_RIGHT = new Position(100, 200);

    protected final Obstacle bounds;
    
    protected List<Obstacle> obstacles;
    protected Position position;
    protected Direction direction;
    protected UpdateResponse status = UpdateResponse.READY;
    
    public AbstractWorld(Maze maze) {
        bounds = new RectangleObstacle(BOTTOM_LEFT, TOP_RIGHT);
        this.maze = maze;
        reset();
    }

    
    @Override
    public List<Obstacle> getObstacles() {
        return obstacles;
    }
    
    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Direction getCurrentDirection() {
        return direction;
    }

    public UpdateResponse getStatus() {
        return status;
    }

    @Override
    public boolean isAtEdge() {
        int x = position.getX();
        int y = position.getY();
        return (bounds.getBottomLeftX() == x) 
        || (bounds.getBottomLeftY() == y)
        || (bounds.getTopRightX() == x)
        || (bounds.getTopRightY() == y);
    }
    
    @Override
    public boolean isNewPositionAllowed(Position position) {
        if (!bounds.blocksPosition(position)){
            status = UpdateResponse.FAILED_OUTSIDE_WORLD;
            return false;
        }
        
        for (Obstacle obstacle : obstacles){
            if ((obstacle.blocksPath(this.position, position)) 
            || obstacle.blocksPath(this.position, position)){
                status = UpdateResponse.FAILED_OBSTRUCTED;
                return false;
            }
        }
        
        status = UpdateResponse.SUCCESS;
        return true;
    }
    
    
    @Override
    public UpdateResponse updatePosition(int nrSteps) {
        final Position newPosition = new Position(
            position.getX() + nrSteps * direction.getX(),
            position.getY() + nrSteps * direction.getY());
            
            if (isNewPositionAllowed(newPosition)){
                position = newPosition;
            if (nrSteps < 0){
                status.setMessage("Moved back by "+ (-nrSteps) +" steps.");
            }
            else {
                status.setMessage("Moved forward by "+ nrSteps +" steps.");
            }
        }
        
        return status;
    }

    @Override
    public void updateDirection(boolean turnRight) {
        if (turnRight) {
            direction = Direction.right(direction);
            status = UpdateResponse.TURNED_RIGHT;
        } else {
            direction = Direction.left(direction);
            status = UpdateResponse.TURNED_LEFT;
        } 
    }
    
    @Override
    public void reset() {
        obstacles = maze.getObstacles();
        position = CENTRE;
        direction = Direction.UP;
        if (this.obstacles.size() > 0) this.showObstacles();
    }
    
    @Override
    public abstract void showObstacles();
}

