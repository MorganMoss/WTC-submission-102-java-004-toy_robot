package za.co.wethinkcode.toyrobot.maze;

/**
 * RandomMaze class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

/**
 * An implementation of the Maze interface. 
 * A World will be loaded with a Maze's obstacles. 
 * This will create a maze with up to 10 randomly positioned obstacles.
 */
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
