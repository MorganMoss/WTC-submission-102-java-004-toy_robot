package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.IWorld;

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
    public boolean execute(Robot target, IWorld world){
        target.addToHistory(this);

        for (int i = Integer.parseInt(getArgument()); i > 0; i--)
            switch (world.updatePosition(i)){
                case FAILED_OBSTRUCTED:
                    target.setStatus("Moved forward by "+i+" steps.");
                    if (i != 1) System.out.println(target);
                    break;
                case FAILED_OUTSIDE_WORLD:
                    target.setStatus("Sorry, I cannot go outside my safe zone.");
                    System.out.println(target);
                    return true;
                case SUCCESS:
                    target.setStatus("Sorry, there is an obstacle in the way.");
                    System.out.println(target);
                    return true;
                default:
                    break;
        }
        return true;
    }

    
}
