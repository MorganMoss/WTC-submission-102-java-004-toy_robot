package za.co.wethinkcode.toyrobot.maze;

/**
 * EmptyMaze class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import java.util.ArrayList;
import java.util.List;

import za.co.wethinkcode.toyrobot.world.Obstacle;

/**
 * An implementation of the Maze interface. 
 * A World will be loaded with a Maze's obstacles. 
 * This will create a blank maze.
 */
public class EmptyMaze implements Maze{
    @Override
    public List<Obstacle> getObstacles() {
        return new ArrayList<Obstacle>();
    }
}
