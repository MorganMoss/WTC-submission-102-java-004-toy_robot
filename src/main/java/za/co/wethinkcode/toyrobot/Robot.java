package za.co.wethinkcode.toyrobot;

import java.util.ArrayList;
import java.util.List;

import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse;

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
    
    public void addToHistory(Command command) {
        this.history.add(command);
    }
    
    public void popHistory() {
        history.remove(history.size()-1);
    }
    
    
    public IWorld.Direction getCurrentDirection() {
        return this.world.getCurrentDirection();
    }

    public Position getPosition() {
        return world.getPosition();
    }


    public boolean handleCommand(Command command) {
        command.execute(this);
        return !(world.getStatus() == UpdateResponse.SHUTDOWN);
    }

    public boolean updatePosition(int nrSteps){
        status = world.updatePosition(nrSteps);
        return status == UpdateResponse.SUCCESS;
    }

    public void updateDirection(boolean turnRight) {
        world.updateDirection(turnRight);
        status = world.getStatus();
    }

    @Override
    public String toString() {
        return "[" + world.getPosition().getX() + "," + world.getPosition().getY() + "] "
            + this.name + "> " + this.status.getMessage();
    }

    public List<Obstacle> getObstacles(){
        return this.world.getObstacles();
    }

    public void reset(){
        this.world.reset();
    }

}