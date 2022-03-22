package za.co.wethinkcode.toyrobot;

/**
 * ReplayCommand class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse;

/**
 * A command that replays commands from a getHistory()
 * of commands when executed
 */
public class ReplayCommand extends Command{
    public ReplayCommand(){super("replay");}
    
    /**
     * @param args : Silent, Reversed and a range (e.g. 2-1 or 2)
     * separated by spaces preferably
     */
    public ReplayCommand(String args){super("replay", args);}

    /**
     * Takes a list of commands and executes them on a target
     * @param commands : The list of commands
     * @param target : The robot that will execute these commands
     * @param world : The world the robot is in
     */
    private static void replayCommands(List<Command> commands, Robot target){
        for (Command command : commands){
            target.handleCommand(command);
            target.popHistory();
            System.out.println(target);
        }
    }

    /**
     * Gets the range of the getHistory() that will be replayed.
     * @param target : The robot whose getHistory() is being observed
     * @return The low end of the range and high end of the range respectively.
     */
    private List<Integer> getRange(Robot target){
        List<Integer> numbers = new ArrayList<>();
        List<Integer> range = new ArrayList<>();
        int high = target.getHistory().size();
        int low = 0; 

        try (Scanner scanner = new Scanner(this.getArgument()).useDelimiter("\\D+")) {
            while (scanner.hasNextInt()){
                numbers.add(scanner.nextInt());
            }
        }

        if (numbers.size() > 2){
            throw new IllegalArgumentException("Unsupported command arguments: " + this.getArgument());
        }

        if (numbers.size() == 1){
            low = target.getHistory().size() - numbers.get(0); 
        } else if (numbers.size() == 2){
            high = target.getHistory().size() - numbers.get(1);
            low = target.getHistory().size() - numbers.get(0);   
        } 

        if (low>high || low < 0 || high > target.getHistory().size()){
            throw new IllegalArgumentException("Unsupported command arguments: " + this.getArgument());
        }
        
        range.add(low);
        range.add(high);

        return range;
    }


    /**
     * This will replay commands according to the 
     * history of the target and the input given
     */
    @Override
    public boolean execute(Robot target) {
        final List<Integer> range = this.getRange(target);
        final int low = range.get(0);
        final int high = range.get(1);
        final List<Command> commands = new ArrayList<Command>(target.getHistory().subList(low, high));

        if (this.getArgument().matches("(.*)(reversed(?i))(.*)")){
            Collections.reverse(commands);
        }
        
        replayCommands(commands, target);

        UpdateResponse r = UpdateResponse.SUCCESS;
        r.setMessage("replayed "+commands.size()+" commands.");
        target.setStatus(r);
        
        return true;
    }

}
