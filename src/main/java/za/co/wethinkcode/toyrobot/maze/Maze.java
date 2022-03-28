package za.co.wethinkcode.toyrobot.maze;

import java.util.List;

import za.co.wethinkcode.toyrobot.world.Obstacle;

/**
 * Interface to represent a maze. 
 * A World will be loaded with a Maze's obstacles.
 */
public interface Maze {
    /**
     * @return the list of obstacles, or an empty list if no obstacles exist.
     */
    List<Obstacle> getObstacles();
}
