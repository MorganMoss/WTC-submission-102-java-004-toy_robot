package za.co.wethinkcode.toyrobot.world;

/**
 * TextWorld class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import za.co.wethinkcode.toyrobot.Play;
import za.co.wethinkcode.toyrobot.maze.Maze;

/**
 * An implementation of the AbstractWorld class. 
 * Creates a world using text as the interface.
 */
public class TextWorld extends AbstractWorld {
    public TextWorld(Maze maze) {
        super(maze);
    }


    @Override
    public void showObstacles() { 
        for (Obstacle obstacle : obstacles){
            Play.print(obstacle.toString());
        }
    }
}
