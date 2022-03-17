package za.co.wethinkcode.toyrobot.world;

import java.util.List;

import za.co.wethinkcode.toyrobot.Position;

import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.Direction;

public class TextWorld implements IWorld {
    private final Maze maze;
    private final List<Obstacle> obstacles;
    
    private final MovableEntity robot;


    public TextWorld(Maze maze) {
        this.maze = maze;
        this.obstacles = maze.getObstacles();
        this.robot = new MovableEntity(CENTRE, Direction.NORTH) ;
    }


    @Override
    public void reset() {
        robot.moveTo(CENTRE);
    }

    @Override
    public Position getPosition() {
        return robot.getPosition();
    }

    @Override
    public Direction getCurrentDirection() {
        return robot.getDirection();
    }

    @Override
    public List<Obstacle> getObstacles() {
        return obstacles;
    }


    @Override
    public void showObstacles() {
    }


    @Override
    public boolean isAtEdge() {
        return false;
    }
    

    private boolean isOutOfBounds(Position position){
        return false;
    }


    @Override
    public boolean isNewPositionAllowed(Position position) {
        if (isOutOfBounds(position)){
            return false;
        }

        for (Obstacle obstacle : obstacles){
            if (obstacle.blocksPath(this.getPosition(), position)){
                return false;
            }
        }

        return true;
    }

    
    @Override
    public UpdateResponse updatePosition(int steps) {
        final Position newPosition = new Position(
            this.robot.getPosition().getX() + steps * robot.getDirection().getX(),
            this.robot.getPosition().getY() + steps * robot.getDirection().getY());

        if (isNewPositionAllowed(newPosition)){
            this.robot.moveTo(newPosition);
            return UpdateResponse.SUCCESS;
        }

        if (isOutOfBounds(newPosition))
            return UpdateResponse.FAILED_OUTSIDE_WORLD;
        return UpdateResponse.FAILED_OBSTRUCTED;
    }


    @Override
    public void updateDirection(boolean turnRight) {
        if (turnRight) robot.turnTo(Direction.right(robot.getDirection()));
        else robot.turnTo(Direction.left(robot.getDirection()));
    }




}
