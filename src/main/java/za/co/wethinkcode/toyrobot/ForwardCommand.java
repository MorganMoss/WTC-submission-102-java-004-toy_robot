package za.co.wethinkcode.toyrobot;

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
    public boolean execute(Robot target) {
        if (getArgument().matches("[0-9]+")){
            target.addToHistory(this);
            int steps = Integer.parseInt(getArgument());
            target.updatePosition(steps);
        } else {
            throw new IllegalArgumentException("Please enter an integer for steps.");
        }
        return true;
    }
}