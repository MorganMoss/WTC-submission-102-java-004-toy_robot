package za.co.wethinkcode.toyrobot.maze;

import java.util.ArrayList;
import java.util.List;

import za.co.wethinkcode.toyrobot.world.Obstacle;

public class EmptyMaze implements Maze{

    @Override
    public List<Obstacle> getObstacles() {
        return new ArrayList<Obstacle>();
    }

}