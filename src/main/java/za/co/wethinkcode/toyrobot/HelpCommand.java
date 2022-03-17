package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

/**
 * HelpCommand class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

/**
 * A command that prints a list of all the possible commands.
 */
public class HelpCommand extends Command {                                                              
    public HelpCommand(){super("help");}

    /**
     * Prints a list of applicable commands
     */
    @Override
    public boolean execute(Robot target, IWorld world) {
        target.setStatus("I can understand these commands:\n" +
                "OFF  - Shut down robot\n" +
                "HELP - provide information about commands\n" +
                "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'");
        return true;
    }
}

