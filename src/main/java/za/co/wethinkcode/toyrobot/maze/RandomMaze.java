package za.co.wethinkcode.toyrobot.maze;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

public class RandomMaze implements Maze {

    @Override
    public List<Obstacle> getObstacles() {
        Random random = new Random();
        List<Obstacle> list = new ArrayList<Obstacle>();
        for (int i = 0; i < random.nextInt(10); i++){
            list.add(new SquareObstacle(random.nextInt(200)-100, random.nextInt(400)-200));
        }
        return list;
    }

}
