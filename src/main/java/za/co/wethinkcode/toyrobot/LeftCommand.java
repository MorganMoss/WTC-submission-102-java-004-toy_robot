package za.co.wethinkcode.toyrobot;

/**
 * LeftCommand class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

/**
 * A command that will make a robot turn left
 */
public class LeftCommand extends Command {
    public LeftCommand(){super("left");}
    
    /**
     * Makes the target turn left
     */
    @Override
    public boolean execute(Robot target) {
        target.addToHistory(this);
        target.updateDirection(false);
        return true;
    }    
}
