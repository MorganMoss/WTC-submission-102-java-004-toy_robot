package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

/**
 * RightCommand class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

/**
 * A command that will make a robot turn right
 */
public class RightCommand extends Command {
    public RightCommand(){super("right");}
    
    /**
     * Makes the target turn right
     */
    @Override
    public boolean execute(Robot target, IWorld world) {
        target.addToHistory(this);
        world.updateDirection(false);
        target.setStatus("Turned right.");
        return true;
    }    
}