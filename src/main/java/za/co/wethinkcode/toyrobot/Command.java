package za.co.wethinkcode.toyrobot;

import za.co.wethinkcode.toyrobot.world.MazerunCommand;

/**
 * Command class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

/**
 * A Command object template
 */
public abstract class Command {
    private final String name;
    private final String args;

    public Command(String name){
        this.name = name; 
        this.args = "";
    } 

    public Command(String name, String args){
        this.name = name; 
        this.args = args;
    }   


    public String getName(){return this.name;}


    public String getArgument(){return this.args;}


    /**
     * This takes in an instruction and makes it into a command object
     * @param instruction : The string given to become a command
     * @return A command object representing the instruction
     */
    public static Command create(String instruction) {
        String[] args = instruction.toLowerCase().trim().split(" ",2);
        switch (args[0]){
            case "shutdown":
                return new ShutdownCommand();
            case "off":
                return new ShutdownCommand();
            case "help":
                return new HelpCommand();
            case "forward":
                return new ForwardCommand(args[1]);
            case "back":
                return new BackCommand(args[1]);
            case "sprint":
                return new SprintCommand(args[1]);
            case "left":
                return new LeftCommand();
            case "right":
                return new RightCommand();
            case "replay":
                if (args.length > 1)
                    return new ReplayCommand(args[1]);
                return new ReplayCommand();
            case "mazerun":
                if (args.length > 1)
                    return new MazerunCommand(args[1]);
                return new MazerunCommand();
            default:
                throw new IllegalArgumentException("Unsupported command: " + instruction);
        }
    }


    /**
     * Calling this will make the target do a task specific to the command called
     * @param target : The target that will execute this command
     * @param world : The world the robot is in
     * @return Always True
     */
    public abstract boolean execute(Robot target);
}
