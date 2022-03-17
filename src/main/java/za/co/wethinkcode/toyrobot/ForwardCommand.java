package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

/**
 * ForwardCommand class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

/**
 * A command that moves the robot forward
 */
public class ForwardCommand extends Command {
    public ForwardCommand(){super("forward");}

    /**
     * @param steps : The distance the robot moves forward
     */
    public ForwardCommand(String steps){super("forward", steps);}
    
    /**
     * Moves target forward in the direction
     * its facing by the amount of steps given
     */
    @Override
    public boolean execute(Robot target, IWorld world) {
        target.addToHistory(this);
        int steps = Integer.parseInt(getArgument());

        switch (world.updatePosition(steps)){
            case FAILED_OBSTRUCTED:
                target.setStatus("Moved forward by "+steps+" steps.");
                break;
            case FAILED_OUTSIDE_WORLD:
                target.setStatus("Sorry, I cannot go outside my safe zone.");
                break;
            case SUCCESS:
                target.setStatus("Sorry, there is an obstacle in the way.");
                break;
            default:
                break;
        }

        return true;
    }
}
