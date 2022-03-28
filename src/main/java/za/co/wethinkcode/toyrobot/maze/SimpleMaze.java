package za.co.wethinkcode.toyrobot.maze;

/**
 * SimpleMaze class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import java.util.ArrayList;
import java.util.List;

import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

/**
 * An implementation of the Maze interface. 
 * A World will be loaded with a Maze's obstacles. 
 * This will create a maze with a single obstacle at the 1,1 position.
 */
public class SimpleMaze implements Maze {

    @Override
    public List<Obstacle> getObstacles() {
        List<Obstacle> list = new ArrayList<Obstacle>();
        list.add(new SquareObstacle(1,1));
        return list;
    }

}
