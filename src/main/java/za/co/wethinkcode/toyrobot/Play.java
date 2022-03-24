package za.co.wethinkcode.toyrobot;

import java.util.Scanner;

import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.maze.DesignedMaze;
import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.maze.RandomMaze;
import za.co.wethinkcode.toyrobot.maze.SimpleMaze;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;
import za.co.wethinkcode.toyrobot.world.TurtleWorld;

public class Play {
    static Scanner scanner;

    public static void main(String[] args) {
        final IWorld world;

        // args = new String[]{"designedmaze", "turtle"};
        args = new String[]{"designedmaze"};


        Maze maze = new EmptyMaze();
        for (String arg : args){
            switch (arg.toLowerCase()){
                case "simplemaze":
                    maze = new SimpleMaze();
                    break;
                case "randommaze":
                    maze = new RandomMaze();
                    break;
                case "designedmaze":
                    maze = new DesignedMaze();
                    break;
            }
        }
        
        boolean flag = false;
        for (String arg : args){
            if (arg.toLowerCase() == "turtle"){
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

        System.out.println(robot.toString());

        Command command;
        do {
            String instruction = getInput(robot.getName() + "> What must I do next?").strip().toLowerCase();
            try {
                command = Command.create(instruction);
                robot.handleCommand(command);
            } catch (IllegalArgumentException e) {
                robot.setStatus("Sorry, I did not understand '" + instruction + "'.");
            }
            System.out.println(robot);
        } while (robot.getStatus() != IWorld.UpdateResponse.SHUTDOWN);

    }

    private static String getInput(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();

        while (input.isBlank()) {
            System.out.println(prompt);
            input = scanner.nextLine();
        }
        return input;
    }
}
