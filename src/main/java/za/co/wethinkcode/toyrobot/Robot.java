package za.co.wethinkcode.toyrobot;

/**
 * Robot class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Defines a robot, that can move around on a 2D plane
 * (And solve mazes)
 */
public class Robot {
    // to delete later
    public static Position CENTRE = new Position(0,0);


    private final String name;

    private Position position;                                                                          
    private Direction currentDirection;
    private String status;

    protected List<Command> history;
    

    public Robot(String name) {
        this.name = name;
        this.status = "Ready";
        this.position = new Position(0,0);                                                                         
        this.currentDirection = Direction.NORTH;
        this.history = new ArrayList<>();
    }


    public String getName(){return this.name;}

    
    public String getStatus(){return this.status;}
    

    public Position getPosition(){return this.position;}


    public Direction getCurrentDirection(){return this.currentDirection;}


    public void setStatus(String status){this.status = status;}
    

    public void setCurrentDirection(Direction direction){
        this.currentDirection = direction;
    }


    public void addToHistory(Command command){this.history.add(command);}


    // public boolean handleCommand(Command command){return command.execute(this, new TextWorld(null));}


    @Override
    public String toString() {
       return "[" + this.position.getX() + "," + this.position.getY() + "] "                            
               + this.name + "> " + this.status;
    }



    
}
