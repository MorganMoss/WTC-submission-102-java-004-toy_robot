package za.co.wethinkcode.toyrobot;

/**
 * HelpCommand class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import za.co.wethinkcode.toyrobot.world.IWorld;

/**
 * A command that provides a list of applicable commands.
 */
public class HelpCommand extends Command {

    public HelpCommand() {
        super("help");
    }

    @Override
    public boolean execute(Robot target) {
        target.setStatus(IWorld.UpdateResponse.HELP);
        return true;
    }
}
