package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

/**
 * ReplayCommand class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

/**
 * A command that moves the robot forward
 */
public class BackCommand extends Command {
    public BackCommand(){super("back");}

   /**
     * @param steps : The distance the robot moves back
     */
    public BackCommand(String steps){super("back", steps);}
        
    /**
     * Moves target backward in the direction
     * its facing by the amount of steps given
     */
    @Override
    public boolean execute(Robot target, IWorld world) {
        target.addToHistory(this);
        int steps = Integer.parseInt(getArgument());

        switch (world.updatePosition(-steps)){
            case FAILED_OBSTRUCTED:
                target.setStatus("Moved back by "+steps+" steps.");
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
