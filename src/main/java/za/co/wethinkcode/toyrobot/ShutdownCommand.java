package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

/**
 * ShutdownCommand class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

/**
 * A command that deactivates the robot
 */
public class ShutdownCommand extends Command {
    public ShutdownCommand(){super("off");}

    /**
     * Deactivates robot
     */
    @Override
    public boolean execute(Robot target, IWorld world) {
        target.setStatus("Shutting down...");
        return false;
    }
}
