package za.co.wethinkcode.toyrobot.maze;

/**
 * SimpleMazerunner class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import za.co.wethinkcode.toyrobot.Command;
import za.co.wethinkcode.toyrobot.ForwardCommand;
import za.co.wethinkcode.toyrobot.LeftCommand;
import za.co.wethinkcode.toyrobot.Play;
import za.co.wethinkcode.toyrobot.Position;
import za.co.wethinkcode.toyrobot.Robot;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.IWorld.Direction;

/**
 * An implementation of the Mazerunner interface that will function to find 
 * the shortest path to an edge and make the target traverse to there
 */
public class SimpleMazeRunner implements MazeRunner {
    private HashMap<String,Integer> map;
    private List<Position> path = new ArrayList<Position>();
    private int count = 0;
    private int size = 5;


    @Override
    public boolean mazeRun(Robot target, Direction edgeDirection) {
        target.setStatus("Starting maze run..");
        Play.print(target.toString());
        
        initializeMap(target);

        explore(target.getPosition(), 1);
        
        Position start = getSmallest(getEdgePosition(edgeDirection));
        path.add(start);
        backTrace(start, Direction.left(Direction.left(edgeDirection)));
        
        
        Collections.reverse(path);     
        
        if (!path.get(0).equals(target.getPosition())){
            return false;
        }
        
        path.remove(0);
        
        count = path.size();

        for (Position position : path){
            int xC = target.getPosition().getX();
            int yC = target.getPosition().getY();

            int x = position.getX();
            int y = position.getY();

            int steps = 0;

            Command command;
            
            Direction direction = Direction.UP;

            if (xC == x){
                if (yC > y){
                    direction = Direction.DOWN;
                    steps = yC - y;
                } else {
                    direction = Direction.UP;
                    steps = y - yC;
                }
            } else {
                if (xC > x){
                    direction = Direction.LEFT;
                    steps = xC - x;
                } else {
                    direction = Direction.RIGHT;
                    steps = x - xC;
                }
            }
            
            while (target.getCurrentDirection() != direction){
                command = new LeftCommand();
                target.handleCommand(command);
                Play.print(target.toString());
            }

            while (steps > 100){
                command = new ForwardCommand(Integer.toString(100));
                target.handleCommand(command);
                Play.print(target.toString());
                steps -= 100;
                count++;
            }

            command = new ForwardCommand(Integer.toString(steps));
            target.handleCommand(command);
            Play.print(target.toString());
        }

        map.clear();

        return true;
    }
    
    @Override
    public int getMazeRunCost() {
        return count;
    }

 
    /**
     * This gets the position of the center of the edge to be traversed to.
     * @param edge : the direction of the edge to go to
     * @return the position of the center of the edge to be traversed to
     */
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

    /**
     * Initializes the map to contain information for each postion in the world.
     * @param target : The robot that this gets information from
     */
    private void initializeMap(Robot target){
        List<Obstacle> obstacles = target.getObstacles();
        map = new HashMap<String, Integer>();
        if (obstacles.size() > 0){
            size = obstacles.get(0).getSize();
        }
        
        for (int y = IWorld.BOTTOM_LEFT.getY(); y <=  IWorld.TOP_RIGHT.getY(); y+=size){
            for (int x = IWorld.BOTTOM_LEFT.getX(); x <= IWorld.TOP_RIGHT.getX(); x+=size){
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

                if (map.get(temp.toString()) > 0){
                    map.put(temp.toString(), 0);
                }
            }
        }
    }

    /**
     * Goes through map from the center and increments the map value of each 
     * next position by an amount equal to n * the recursion depth
     * @param currentPosition : The position to start this from
     * @param n : the number to change the current position's value on the map to
     */
    private void explore(Position currentPosition, int n){
        map.put(currentPosition.toString(), n);

        for (Direction direction : new  Direction[]{Direction.UP, Direction.DOWN, Direction.LEFT, Direction.RIGHT}){
            Position next = new Position(
                currentPosition.getX()+direction.getX()*size,
                currentPosition.getY()+direction.getY()*size
            );

            if (map.get(next.toString()) == null){
                return;
            }

            if (map.get(next.toString()) == 0 || map.get(next.toString()) > n+1){
                explore(next, n+1);
            }
        }
    }

    /**
     * Goes through all the open spaces on the goal edge and returns the shortest path
     * @param goal : the position of the edge's centre
     * @return the position that will require the lowest number of steps to get to
     */
    private Position getSmallest(Position goal){
        Position smallest = goal;

        if (goal.getX() == IWorld.CENTRE.getX()){
            for (int x = IWorld.BOTTOM_LEFT.getX(); x <= IWorld.TOP_RIGHT.getX(); x+=size){
                Position temp = new Position(x, goal.getY());

                int sizeCurrent = map.get(smallest.toString());
                int sizeNext = map.get(temp.toString());

                if (sizeNext>0){
                    if (sizeCurrent >= sizeNext || sizeCurrent == -1){
                        smallest = temp;
                    }
                }
            }
        } else {
            for (int y = IWorld.BOTTOM_LEFT.getY(); y <=  IWorld.TOP_RIGHT.getY(); y+=size){
                Position temp = new Position(goal.getX(), y);                

                int sizeCurrent = map.get(smallest.toString());
                int sizeNext = map.get(temp.toString());

                if (sizeNext>0){
                    if (sizeCurrent >= sizeNext || sizeCurrent == -1){
                        smallest = temp;
                    }
                }
            }
        }

        return smallest;
    }

    /**
     * Goes from and edge and will traverse to the center, using the map as a guide
     * @param currentPosition The position this is currently at, should initially be the edge
     * @param lastDirection The direction this was last going in. should initally be opposite the edges direction
     */
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
                currentPosition.getX()+direction.getX()*size,
                currentPosition.getY()+direction.getY()*size
            );

            if (map.get(next.toString()) != null){
                if (map.get(next.toString()) == map.get(currentPosition.toString()) -1){
                    if (direction != lastDirection){
                        path.add(currentPosition);
                    }

                    if(map.get(next.toString()) == 1){
                        path.add(next);
                    }

                    backTrace(next, direction);

                    break;
                }
            }
        }
    }
}
