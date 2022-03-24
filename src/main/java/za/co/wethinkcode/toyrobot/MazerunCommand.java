package za.co.wethinkcode.toyrobot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * SprintCommand class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import za.co.wethinkcode.toyrobot.maze.MazeRunner;
import za.co.wethinkcode.toyrobot.world.IWorld.UpdateResponse;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.IWorld.Direction;

/**
 * A command that will make a robot sprint
 */
public class MazerunCommand extends Command implements MazeRunner {
    private HashMap<String,Integer> map;
    private List<Position> path = new ArrayList<Position>();
    private int count = 0;
    private int size = 1;


    public MazerunCommand(){super("mazerun");}

    /**
     * @param edge : The edge to go to.
     */
    public MazerunCommand(String edge){super("mazerun", edge);}


    private void explore(Position currentPosition, int n){
        map.put(currentPosition.toString(), n);

        // System.out.println(map.get(currentPosition.toString()));

        for (Direction direction : new  Direction[]{Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT}){
            Position next = new Position(
                currentPosition.getX()+direction.getX(),
                currentPosition.getY()+direction.getY()
            );

            if (map.get(next.toString()) == null){
                return;
            }

            if (map.get(next.toString()) == 0){
                explore(next, n+1);
            }
        }
    }


    private Position getSmallest(Position goal){
        Position smallest = goal;

        if (goal.getX() == IWorld.CENTRE.getX()){
            for (int x = IWorld.BOTTOM_LEFT.getX(); x <= IWorld.TOP_RIGHT.getX(); x++){
                Position temp = new Position(x, goal.getY());

                if (map.get(temp.toString())>0 
                        && (map.get(smallest.toString()) > map.get(temp.toString()) 
                            || map.get(smallest.toString()) == -1)){
                        smallest = temp;
                }
            }
        } else {
            for (int y = IWorld.TOP_RIGHT.getY(); y >= IWorld.BOTTOM_LEFT.getY(); y--){
                Position temp = new Position(goal.getX(), y);                

                if (map.get(temp.toString())>0 
                    && (map.get(smallest.toString()) > map.get(temp.toString()) 
                        || map.get(smallest.toString()) == -1)){
                smallest = temp;
                }
            }
        }

        return smallest;
    }


    private void backTrace(Position currentPosition, Direction lastDirection){
        Direction[] directions;
        
        switch (lastDirection){
            case DOWN:
                directions = new  Direction[]{Direction.DOWN, Direction.LEFT, Direction.RIGHT, Direction.UP};
                break;
            case LEFT:
                directions = new  Direction[]{Direction.LEFT, Direction.DOWN, Direction.UP, Direction.RIGHT};
                break;
            case RIGHT:
                directions = new  Direction[]{Direction.RIGHT, Direction.UP, Direction.DOWN, Direction.LEFT};
                break;
            default:
                directions = new  Direction[]{Direction.UP, Direction.RIGHT, Direction.LEFT,  Direction.DOWN};
                break;
        }


        for (Direction direction : directions){
            Position next = new Position(
                currentPosition.getX()+direction.getX(),
                currentPosition.getY()+direction.getY()
            );

            if (map.get(next.toString()) != null){
                if (map.get(next.toString()) == map.get(currentPosition.toString()) -1){
                    if (direction != lastDirection || map.get(next.toString()) == 1){
                        path.add(next);
                    }
                    backTrace(next, direction);
                    break;
                }
            }
        }

    }


    private void initializeMap(Robot target){
        List<Obstacle> obstacles = target.getObstacles();
        map = new HashMap<String, Integer>();
        size = obstacles.get(0).getSize();
        
        for (int y = IWorld.BOTTOM_LEFT.getY(); y <=  IWorld.TOP_RIGHT.getY(); y++){
            for (int x = IWorld.BOTTOM_LEFT.getX(); x <= IWorld.TOP_RIGHT.getX(); x++){
                Position temp = new Position(x, y);
                
                for (Obstacle obstacle : obstacles){
                    if (obstacle.blocksPosition(temp)){
                        map.put(temp.toString(), -1);
                        break;
                    } 
                }
                
                if (map.get(temp.toString()) == null){
                    map.put(temp.toString(), 0);
                }
            }
        }
    }


    @Override
    public boolean mazeRun(Robot target, Direction edgeDirection) {
        initializeMap(target);

        explore(target.getPosition(), 1);
        
        Position start = getSmallest(getEdgePosition(edgeDirection));
     
        path.add(start);

        backTrace(start, Direction.left(Direction.left(edgeDirection)));
        
        count = path.size();

        for (Position p : path){
            System.out.println(p);
        }

        for (int i = 0; i < 2; i++){
            target.handleCommand(new ForwardCommand("100"));
            System.out.println(target);
        }

        return true;
    }
    
    @Override
    public int getMazeRunCost() {
        return count;
    }
    

    /**
     * This makes a target move to an edge, default UP, while avoiding obstacles
     */
    @Override
    public boolean execute(Robot target){
        Direction edge;
        switch (this.getArgument().toLowerCase()){
            case "right" :
                edge = Direction.RIGHT;
                break;
            case "down" :
                edge = Direction.DOWN;
                break;
            case "left" :
                edge = Direction.LEFT;
                break;
            default:
                edge  = Direction.UP;
                break;
        }

        if (mazeRun(target, edge)){
            UpdateResponse status = UpdateResponse.SOLVED;
            status.setMessage("I am at the "+ edge +" edge. (Cost: " 
                + getMazeRunCost() + " steps)");
            target.setStatus(status);
        } else {
            target.setStatus(UpdateResponse.FAILED_NO_SOLUTION);
        }
        return true;
    }


    private Position getEdgePosition(Direction edge){
        switch (edge){
            case DOWN:
                return new Position(IWorld.CENTRE.getX(), IWorld.BOTTOM_LEFT.getY());
            case LEFT:
                return new Position(IWorld.BOTTOM_LEFT.getX(), IWorld.CENTRE.getY());
            case RIGHT:
                return new Position(IWorld.TOP_RIGHT.getX(), IWorld.CENTRE.getY());
            default:
                return new Position(IWorld.CENTRE.getX(), IWorld.TOP_RIGHT.getY());
        }
    }
}
