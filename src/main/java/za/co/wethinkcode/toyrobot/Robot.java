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

import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse;

/**
 * This is a robot that holds a name, a history, a status and the world that contains it.
 * It can handle commands given to it
 */
public class Robot {
    private final IWorld world;

    private List<Command> history;
    private UpdateResponse status;
    private String name;

    
    public Robot(String name, IWorld world) {
        this.world = world;
        this.name = name;
        this.status = UpdateResponse.READY;
        this.history = new ArrayList<>();
    }

    
    public UpdateResponse getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = UpdateResponse.FAILED;
        this.status.setMessage(status);
    }

    public void setStatus(IWorld.UpdateResponse status) {
        this.status = status;
    }
    
    public String getName() { 
        return name;
    }

    public List<Command> getHistory() { 
        return history; 
    }
        
    /**
     * Adds command to history
     * @param command : the command to be added
     */
    public void addToHistory(Command command) {
        this.history.add(command);
    }
    
    /**
     * Removes last element added to history
     */
    public void popHistory() {
        history.remove(history.size()-1);
    }
    
    public IWorld.Direction getCurrentDirection() {
        return this.world.getCurrentDirection();
    }

    public Position getPosition() {
        return this.world.getPosition();
    }

    public List<Obstacle> getObstacles(){
        return this.world.getObstacles();
    }

    /**
     * This will execute a given command.
     * The command will change the status of the robot.
     * This will not handle output of that status
     * @param command : the command to be executed 
     * @return true if the robot does not want to shut down
     */
    public boolean handleCommand(Command command) {
        command.execute(this);
        return !(world.getStatus() == UpdateResponse.SHUTDOWN);
    }

    /**
     * Updates the position of your robot in the world by moving the nrSteps in the robots current direction.
     * @param nrSteps steps to move in current direction
     * @return true if this does not take the robot over the world's limits, or into an obstacle.
     */
    public boolean updatePosition(int nrSteps){
        status = world.updatePosition(nrSteps);
        return status == UpdateResponse.SUCCESS;
    }

    /**
     * Updates the current direction your robot is facing in the world by cycling through the directions UP, RIGHT, BOTTOM, LEFT.
     * @param turnRight if true, then turn 90 degrees to the right, else turn left.
     */
    public void updateDirection(boolean turnRight) {
        world.updateDirection(turnRight);
        status = world.getStatus();
    }

    /**
     * Reset the world by:
     * - moving current robot position to center 0,0 coordinate
     * - removing all obstacles
     * - setting current direction to UP
     */
    public void reset(){
        this.world.reset();
    }


    @Override
    public String toString() {
        return "[" + world.getPosition().getX() + "," + world.getPosition().getY() + "] "
            + this.name + "> " + this.status.getMessage();
    }
}