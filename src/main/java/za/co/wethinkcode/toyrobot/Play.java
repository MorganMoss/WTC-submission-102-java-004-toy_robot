package za.co.wethinkcode.toyrobot;

/**
 * Play class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import java.util.Scanner;

import za.co.wethinkcode.toyrobot.command.Command;
import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.obstacle.Robot;
import za.co.wethinkcode.toyrobot.world.World;
import za.co.wethinkcode.toyrobot.world.TextWorld;

/**
 * Start the program from here using main
 */
public class Play {
    final static Scanner scanner = new Scanner(System.in);
    
    
    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();
        if (input.isBlank()){
            return getInput(prompt);
        }
        return input;
    }


    public static boolean has(String[] args, String word){
        for (String arg : args)
            if (arg.matches("(.*)(" + word + "(?i))(.*)")){
                return true;
            }
        return false;
    }   


    public static void run(World world){
        Robot robot = new Robot(getInput("What do you want to name your robot?"));
        System.out.println("Hello Kiddo!"); 
        System.out.println(robot.toString());

        String instruction;
        do {
            instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {
                if (!(Command.create(instruction).execute(robot, world))) break;
            } catch (IllegalArgumentException e) {
                robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
            }
            System.out.println(robot);
        } while (true);
        System.out.println(robot);
    }

    
    public static void main(String[] args) {
        final World world;
        final Maze maze;
        
        if (has(args, "EmptyMaze")){
            maze = new EmptyMaze();
            System.out.println("Loaded EmptyMaze");
        } else {
            maze = new EmptyMaze();
        }
        
        if (has(args, "Text")){
            world = new TextWorld(maze);
            System.out.println("Loaded EmptyMaze");
        } else {
            world = new TextWorld(maze);
        }

        run(world);
    }
}
