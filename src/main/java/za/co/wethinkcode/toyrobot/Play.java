package za.co.wethinkcode.toyrobot;

/**
 * Play class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import java.util.Scanner;

import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.maze.DesignedMaze;
import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.maze.RandomMaze;
import za.co.wethinkcode.toyrobot.maze.SimpleMaze;

import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;
import za.co.wethinkcode.toyrobot.world.TurtleWorld;

/**
 * The program is run from this class. It handles input and output.
 */
public class Play {
    static Scanner scanner;
    
    /**
     * Program run from here. 
     * Makes a maze that is given to a world that is given to a robot
     * That robot will then take commands as input.
     * @param args : The command line arguments.
     * It allows you to specify what maze and world you wish to use.
     */
    public static void main(String[] args) {
        final IWorld world;

        // args = new String[]{"designedmaze", "turtle"};
        
        Maze maze = new EmptyMaze();
        for (String arg : args){
            switch (arg.toLowerCase()){
                case "emptymaze":
                    maze = new EmptyMaze();
                    System.out.println("Loaded EmptyMaze.");
                    break;
                case "simplemaze":
                maze = new SimpleMaze();
                    System.out.println("Loaded SimpleMaze.");
                    break;
                case "randommaze":
                    maze = new RandomMaze();
                    System.out.println("Loaded RandomMaze.");
                    break;
                case "designedmaze":
                    maze = new DesignedMaze();
                    System.out.println("Loaded DesignedMaze.");
                    break;
            }
        }
        
        boolean flag = false;
        for (String arg : args){
            if (arg.toLowerCase().matches("turtle")){
                flag = true;
                break;
            }
        }
        
        if (!flag){
            world = new TextWorld(maze);
        } else {
            world = new TurtleWorld(maze);
        }
        scanner = new Scanner(System.in);
        Robot robot;
        String name = getInput("What do you want to name your robot?");
        robot = new Robot(name, world);
        System.out.println("Hello Kiddo!");

        print(robot.toString());

        Command command;
        do {
            String instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {
                command = Command.create(instruction);
                robot.handleCommand(command);
            } catch (IllegalArgumentException e) {
                robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
            }
            print(robot.toString());
        } while (robot.getStatus() != IWorld.UpdateResponse.SHUTDOWN);
        
        if (flag){
            System.exit(0);
        }
    }

    /**
     * This will prompt the user with given message, and then get input
     * @param prompt : the message the user will be prompted with
     * @return The user's non-empty input
     */
    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }
    
    /**
     * Will print out a given message to console
     * @param message : The message to be printed to console
     */
    public static void print(String message){
        System.out.println(message);
    }
}
