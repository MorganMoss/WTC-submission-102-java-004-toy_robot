package za.co.wethinkcode.toyrobot;

/**
 * SprintCommand class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse;

/**
 * A command that will make a robot sprint
 */
public class SprintCommand extends Command {
    public SprintCommand(){super("sprint");}

    /**
     * @param steps : The distance of the initial step forward
     */
    public SprintCommand(String steps){super("sprint", steps);}
    
    /**
     * This makes a target move forward by steps, then steps -1, then steps -2 
     * and so on until it reaches one
     */
    @Override
    public boolean execute(Robot target){
        if (getArgument().matches("[0-9]+")){
            int steps = Integer.parseInt(getArgument());
            for (int i = steps; i > 0; i--){
                target.handleCommand(new ForwardCommand(Integer.toString(i)));
                if (target.getStatus() != UpdateResponse.SUCCESS){
                    break;
                }
                if (i > 1) Play.print(target.toString());
            }
        } else {
            throw new IllegalArgumentException("Please enter an integer for steps.");
        }
        return true;
    }

    
}
