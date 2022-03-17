package za.co.wethinkcode.toyrobot;

/**
 * Play class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import java.util.Scanner;

import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;

/**
 * Start the program from here using main
 */
public class Play {
    final Scanner scanner;
    final Robot robot;
    final IWorld world;

    public Play(Maze maze){
        this.scanner = new Scanner(System.in);
        this.robot = new Robot(getInput("What do you want to name your robot?"));
        this.world = new TextWorld(maze);
    }


    public void run(){
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
    
    
    private String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();
        if (input.isBlank()){
            return getInput(prompt);
        }
        return input;
    }


    public static void main(String[] args) {
        // final Robot robot = new Robot(getInput("What do you want to name your robot?"));
        
        Maze maze = new EmptyMaze();
        
        for (String arg : args)
            if (arg.matches("(.*)(EmptyMaze(?i))(.*)")){
                maze = new EmptyMaze();
                System.out.println("Loaded EmptyMaze");
                break;
            }
        
        Object world = new TextWorld(maze);

        for (String arg : args)
            if (arg.matches("(.*)(Text(?i))(.*)")){
                world = new TextWorld(maze);
                System.out.println("Loaded TextWorld");
                break;
            }
        

        new Play(maze).run();
    }
}
