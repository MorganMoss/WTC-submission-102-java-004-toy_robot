package za.co.wethinkcode.toyrobot;

/**
 * MazeCommand class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import za.co.wethinkcode.toyrobot.maze.MazeRunner;
import za.co.wethinkcode.toyrobot.maze.SimpleMazeRunner;
import za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.IWorld.Direction;

/**
 * A command that will make a robot solve a maze
 */
public class MazerunCommand extends Command{
    public MazerunCommand(){super("mazerun");}

    /**
     * @param edge : The edge to go to.
     */
    public MazerunCommand(String edge){super("mazerun", edge);}


    /**
     * This makes a target move to an edge, default UP, while avoiding obstacles
     */
    @Override
    public boolean execute(Robot target){
        Direction edge;    /**
        * I chose to impl
        */

        switch (this.getArgument().toLowerCase()){
            case "right" :
                edge = Direction.RIGHT;
                break;
            case "bottom" :
                edge = Direction.DOWN;
                break;
            case "left" :
                edge = Direction.LEFT;
                break;
                case "top" :
                edge = Direction.UP;
                break;
                case "" :
                edge = Direction.UP;
                break;
                default:
                throw new IllegalArgumentException("Unsupported command.");
        }

        if (target.getPosition() != IWorld.CENTRE){
            target.reset();
        }

        MazeRunner runner = new SimpleMazeRunner();

        if (runner.mazeRun(target, edge)){
            UpdateResponse status = UpdateResponse.SOLVED;
            status.setMessage("I am at the "+ edge +" edge. (Cost: " + runner.getMazeRunCost() + " steps)");
            target.setStatus(status);
        } else {
            target.setStatus(UpdateResponse.FAILED_NO_SOLUTION);
        }

        return true;
    }
}
