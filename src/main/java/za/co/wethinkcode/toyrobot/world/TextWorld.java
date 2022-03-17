package za.co.wethinkcode.toyrobot.world;

import java.util.List;

import za.co.wethinkcode.toyrobot.position.Coordinate;
import za.co.wethinkcode.toyrobot.obstacle.Obstacle;
import za.co.wethinkcode.toyrobot.obstacle.RectangleObstacle;
import za.co.wethinkcode.toyrobot.obstacle.MovableObstacle;
import za.co.wethinkcode.toyrobot.maze.Maze;

public class TextWorld implements World {
    private final Obstacle bounds;
    private final List<Obstacle> obstacles;


    public TextWorld(Maze maze) {
        this.bounds = new RectangleObstacle(BOTTOM_LEFT, TOP_RIGHT);
        this.obstacles = maze.getObstacles();
    }


    @Override
    public List<Obstacle> getObstacles() {
        return obstacles;
    }


    @Override
    public void showObstacles() { 
        for (Obstacle obstacle : obstacles){
            System.out.println(obstacle);
        }
    }


    @Override
    public boolean isAtEdge(MovableObstacle robot) {
        int x = robot.getBottomLeftCoordinate().getX();
        int y = robot.getBottomLeftCoordinate().getY();
        return (bounds.getBottomLeftCoordinate().getX() == x) 
            || (bounds.getBottomLeftCoordinate().getY() == y)
            || (bounds.getTopRightCoordinate().getX() == x)
            || (bounds.getTopRightCoordinate().getY() == y);
    }
    

    @Override
    public UpdateResponse isNewPositionAllowed(MovableObstacle robot, Coordinate position) {
        if (!bounds.blocksCoordinate(position)){
            return UpdateResponse.FAILED_OUTSIDE_WORLD;
        }

        for (Obstacle obstacle : obstacles){
            if ((obstacle.blocksPath(robot.getBottomLeftCoordinate(), position)) 
                || obstacle.blocksPath(robot.getTopRightCoordinate(), position)){
                return UpdateResponse.FAILED_OBSTRUCTED;
            }
        }

        return UpdateResponse.SUCCESS;
    }
}
