package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Command;
import za.co.wethinkcode.toyrobot.ForwardCommand;
import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.maze.MazeRunner;
import za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse;
import za.co.wethinkcode.toyrobot.world.IWorld.Direction;


/**
 * SprintCommand class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

/**
 * A command that will make a robot sprint
 */
public class MazerunCommand extends Command implements MazeRunner {
    private int count = 0;

    public MazerunCommand(){super("mazerun");}

    /**
     * @param edge : The edge to go to.
     */
    public MazerunCommand(String edge){super("mazerun", edge);}
    

    // private void 

    @Override
    public boolean mazeRun(Robot target, Direction edgeDirection) {
        for (int i = 0; i < 2; i++){
            count ++;
            target.handleCommand(new ForwardCommand("100"));
            System.out.println(target);
        }
        return true;
    }
    
    @Override
    public int getMazeRunCost() {
        return count;
    }
    
    
    /**
     * This makes a target move to an edge, default UP, while avoiding obstacles
     */
    @Override
    public boolean execute(Robot target){
        Direction edge = Direction.UP;
        switch (this.getArgument().toLowerCase()){
            case "right" :
                edge = Direction.RIGHT;
                break;
            case "down" :
                edge = Direction.DOWN;
                break;
            case "left" :
                edge = Direction.LEFT;
                break;
            default:
                edge  = Direction.UP;
                break;
        }

        if (mazeRun(target, edge)){
            UpdateResponse status = UpdateResponse.SOLVED;
            status.setMessage("I am at the "+ edge +" edge. (Cost: " 
                + getMazeRunCost() + " steps)");
            target.setStatus(status);
        } else {
            target.setStatus(UpdateResponse.FAILED_NO_SOLUTION);
        }
        return true;
    }
}
