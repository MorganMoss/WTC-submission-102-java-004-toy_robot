package za.co.wethinkcode.toyrobot.maze;

/**
 * DesignedMaze class source code
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
 * This will create a structured maze.
 */
public class DesignedMaze implements Maze {
/**
 * The basic maze string, can be used to generate an easy maze. 
 */
private static String basicMaze = 
        "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "                                         \n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
    +   "XXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXXX\n"
;
/**
 * The real maze string, can be used to generate a proper maze. 
 */
private static String realMaze = 
        "XXXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXX\n"
    +   "X     X     X X   X X   X     X X   X   X\n"
    +   "XXX X XXX X XXXXX XXX X X X XXX XXX X X X\n"
    +   "X   X   X X X X       X   X         X X X\n"
    +   "X XXXXX X X X XXXXX XXX XXXXXXXXX XXX X X\n"
    +   "X     X X       X   X     X X X   X X X X\n"
    +   "XXX XXXXX X X XXX X XXXXXXX X X X X XXX X\n"
    +   "X   X   X X X   X X   X X     X X       X\n"
    +   "XXX XXX XXXXX XXXXXXX X XXX XXXXXXX X X X\n"
    +   "X               X X   X       X X   X X X\n"
    +   "XXX XXX X X XXXXX X XXX X XXX X X X XXXXX\n"
    +   "X   X   X X         X   X   X X   X     X\n"
    +   "XXX X XXX XXXXXXX X X X XXX X X XXX X X X\n"
    +   "X   X X   X X   X X X X   X X X X   X X X\n"
    +   "XXXXXXX XXX XXX XXXXXXX X XXXXXXXXXXXXX X\n"
    +   "X     X X     X   X   X X         X     X\n"
    +   "X X X X XXX XXX X X X XXX X XXXXX XXXXX X\n"
    +   "X X X           X   X X X X   X       X X\n"
    +   "XXXXXXXXX XXXXX X XXXXX XXX X XXXXXXX X X\n"
    +   "X X X X     X X X X       X X     X X X X\n"
    +   "X X X XXX XXX XXXXXXX X XXXXX X X X XXX X\n"
    +   "X   X X X         X   X       X X       X\n"
    +   "X XXX X XXX X XXXXXXXXX XXXXXXX X XXXXX X\n"
    +   "X X       X X X       X X X   X X X     X\n"
    +   "X XXX X XXX XXXXX XXX X X XXX X XXX XXXXX\n"
    +   "    X X     X     X   X     X     X   X  \n"
    +   "XXX XXX X X XXX XXXXXXXXX X XXX XXXXXXX X\n"
    +   "X   X X X X X X X       X X X     X   X X\n"
    +   "X X X XXX X X X X XXXXXXXXXXX X XXXXX X X\n"
    +   "X X   X   X       X         X X   X X   X\n"
    +   "X X X XXX X XXXXXXX X XXXXXXXXX X X XXX X\n"
    +   "X X X   X X     X   X   X       X     X X\n"
    +   "XXXXX XXX XXX X X XXX XXXXXXXXX X X XXX X\n"
    +   "X X       X   X   X X     X   X X X X   X\n"
    +   "X X XXXXXXXXX XXX X XXXXXXX XXXXX XXXXX X\n"
    +   "X   X     X     X X   X     X   X     X X\n"
    +   "XXXXX XXXXX XXX XXX X X XXX XXX X XXX X X\n"
    +   "X       X X X     X X   X   X   X X   X X\n"
    +   "XXXXX XXX XXX XXXXX XXX XXX X XXXXX X X X\n"
    +   "X     X X X     X X   X X X     X X X   X\n"
    +   "XXX X X X XXXXX X X   X X X XXX X XXXXX X\n"
    +   "X X X       X     X   X X     X   X X X X\n"
    +   "X X XXX XXXXXXXXX X XXX XXX XXX XXX X X X\n"
    +   "X X   X       X   X X X X X X X X   X   X\n"
    +   "X XXX XXXXX XXX XXXXX X X XXX X XXX X XXX\n"
    +   "X X   X   X   X       X   X X   X X     X\n"
    +   "X XXX X XXXXX X XXX XXX XXX XXXXX X XXXXX\n"
    +   "X     X     X     X X         X       X X\n"
    +   "X XXX X X X XXX XXX X XXX X XXXXXXX XXX X\n"
    +   "X X   X X X       X X   X X       X X X X\n"
    +   "X XXX X X XXX X X XXX XXXXXXXXXXX X X X X\n"
    +   "X X   X X X   X X X   X X       X       X\n"
    +   "XXXXX XXX X XXXXXXX XXX X XXXXX X XXXXX X\n"
    +   "X   X X   X     X X   X     X   X   X   X\n"
    +   "X X XXX X XXX XXX XXXXXXXXXXX X X XXXXX X\n"
    +   "X X     X   X     X     X X X X   X X X  \n"
    +   "X XXXXXXXXX X XXX X X XXX X XXX X X X XXX\n"
    +   "X   X       X   X   X X   X   X X X   X X\n"
    +   "XXXXXXX XXX X XXXXX X XXX X X X XXX XXX X\n"
    +   "X   X   X X X X     X X X X X       X X X\n"
    +   "X XXXXXXX X XXXXX XXX X X X XXX XXX X X X\n"
    +   "X   X         X   X X X   X X     X   X X\n"
    +   "X XXXXXXXXXXXXXXXXX X XXX X XXXXXXXXX X X\n"
    +   "X   X X X   X   X       X X     X X     X\n"
    +   "X XXX X XXX X XXX X XXXXX XXX XXX XXXXX X\n"
    +   "X   X X       X X X     X     X       X X\n"
    +   "X X X X X XXXXX X XXXXXXXXX XXX XXX X X X\n"
    +   "X X     X   X               X   X   X   X\n"
    +   "XXXXX XXXXXXXXXXX XXX XXXXXXX XXXXX XXXXX\n"
    +   "X         X X     X         X   X       X\n"
    +   "XXXXX X X X XXX XXX X X XXXXXXX XXXXXXXXX\n"
    +   "X     X X       X   X X   X              \n"
    +   "XXXXX X X X XXX XXX X X XXXXXXX XXXXXXXXX\n"
    +   "X   X X       X X X     X     X       X X\n"
    +   "X X X X X XXXXX X XXXXXXXXX XXX XXX X X X\n"
    +   "X X     X   X               X   X   X   X\n"
    +   "XXXXX XXXXXXXXXXX XXX XXXXXXX XXXXX XXXXX\n"
    +   "X         X X     X         X   X       X\n"
    +   "XXXXX X X X XXX XXX X X XXXXXXX XXXXXXXXX\n"
    +   "X     X X       X   X X   X              \n"
    +   "XXXXXXXXXXXXXXXXXXXXX XXXXXXXXXXXXXXXXXXX\n"
;


    @Override
    public List<Obstacle> getObstacles() {
        String maze = DesignedMaze.realMaze;

        List<Obstacle> list = new ArrayList<Obstacle>();

        int x;
        int y = 40; 
        
        for (String line : maze.split("\n")){
            x = -20;
            for(char c : line.toCharArray()){
                if (c == 'X'){
                    list.add(new SquareObstacle(x*5, y*5));
                }
                x++;
            }
            y--;
        }

        return list;
    }
}
