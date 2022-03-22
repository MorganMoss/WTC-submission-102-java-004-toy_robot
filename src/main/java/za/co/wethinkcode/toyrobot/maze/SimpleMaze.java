package za.co.wethinkcode.toyrobot.maze;

import java.util.ArrayList;
import java.util.List;

import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

public class SimpleMaze implements Maze {

    @Override
    public List<Obstacle> getObstacles() {
        List<Obstacle> list = new ArrayList<Obstacle>();
        list.add(new SquareObstacle(1,1));
        return list;
    }

}
