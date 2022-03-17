package za.co.wethinkcode.toyrobot.maze;

import java.util.List;

import za.co.wethinkcode.toyrobot.obstacle.Obstacle;

/**
 * Interface to represent a maze. A World will be loaded with a Maze, and will delegate the work to check if a path is blocked by certain obstacles etc to this maze instance.
 */
public interface Maze {
    /**
     * @return the list of obstacles, or an empty list if no obstacles exist.
     */
    List<Obstacle> getObstacles();

}
