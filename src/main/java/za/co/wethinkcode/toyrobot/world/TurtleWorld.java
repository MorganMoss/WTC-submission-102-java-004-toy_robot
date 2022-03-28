package za.co.wethinkcode.toyrobot.world;

/**
 * TurtleWorld class source code
 * 
 * @author Morgan Moss
 * @version 1.0
 * 
 */

import java.awt.Color;
import org.turtle.StdDraw;

import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.Position;

/**
 * An implementation of the AbstractWorld class. 
 * Creates a world using StdDraw as the interface.
 */
public class TurtleWorld extends AbstractWorld{
    private static double WINDOW_SCALE = 1.1;
    private static double OBSTACLE_SCALE = 1;
    private static int WINDOW_SIZE = 3;

    
    private int prev_x = 0;
    private int prev_y = 0;


    public TurtleWorld(Maze maze) {
        super(maze);

        StdDraw.setCanvasSize(
            bounds.getTopRightX()*WINDOW_SIZE,
            bounds.getTopRightY()*WINDOW_SIZE
        );

        StdDraw.setXscale(
            bounds.getBottomLeftX()*WINDOW_SCALE,
            bounds.getTopRightX()*WINDOW_SCALE
        );

        StdDraw.setYscale(
            bounds.getBottomLeftY()*WINDOW_SCALE,
            bounds.getTopRightY()*WINDOW_SCALE
        );

        StdDraw.disableDoubleBuffering();
        
        showObstacles();
    }

    
    @Override
    public void showObstacles() {
        drawBounds();
        for (Obstacle obstacle : obstacles){
            drawObstacle(obstacle);
        }
        drawRobot(position);
    }

    @Override
    public UpdateResponse updatePosition(int nrSteps) {
        UpdateResponse status = super.updatePosition(nrSteps);
        if (status == UpdateResponse.SUCCESS) showPositionUpdate();
        return status;
    }

    @Override
    public void reset() {
        super.reset();
        prev_x = CENTRE.getX();
        prev_y = CENTRE.getY();
    }

    
    /**
     * Draws the given obstacle on the StdDraw window
     * @param obstacle : The obstacle to be drawn
     */
    private void drawObstacle(Obstacle obstacle){
        StdDraw.setPenRadius(0.002);
        
        StdDraw.setPenColor(Color.DARK_GRAY);
        StdDraw.filledSquare(
            obstacle.getBottomLeftX()*OBSTACLE_SCALE,
            obstacle.getBottomLeftY()*OBSTACLE_SCALE,
            obstacle.getSize()*OBSTACLE_SCALE/2
            );
        // StdDraw.setPenColor(Color.ORANGE);
        StdDraw.square(
            obstacle.getBottomLeftX()*OBSTACLE_SCALE,
            obstacle.getBottomLeftY()*OBSTACLE_SCALE,
            obstacle.getSize()*OBSTACLE_SCALE/2
        );
    }
    
    /**
     * Draws the bounds of the world in the StdDraw window
     */
    private void drawBounds() {
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(Color.BLACK);

        StdDraw.filledRectangle(
            0,0,
            bounds.getTopRightX()*OBSTACLE_SCALE,
            bounds.getTopRightY()*OBSTACLE_SCALE
            );
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.rectangle(
            0,0,
            bounds.getTopRightX()*OBSTACLE_SCALE,
            bounds.getTopRightY()*OBSTACLE_SCALE
        );
    }
    
    /**
     * Draws the position representing the robot of the world in the StdDraw window
     */
    private void drawRobot(Position position){
        float robotSize = 2.5f;
        StdDraw.setPenRadius(0.002);
        StdDraw.setPenColor(Color.orange);
        StdDraw.filledSquare(
            position.getX()*OBSTACLE_SCALE,
            position.getY()*OBSTACLE_SCALE,
            robotSize*OBSTACLE_SCALE
            );
        StdDraw.square(
            position.getX()*OBSTACLE_SCALE,
            position.getY()*OBSTACLE_SCALE,
            robotSize*OBSTACLE_SCALE
            );
    }

    /**
     * Creates a line between the robot's old and new position in the StdDraw window
     */
    private void showPositionUpdate() {
        StdDraw.setPenRadius(0.006);
        StdDraw.setPenColor(Color.orange);

        StdDraw.line(
            prev_x*OBSTACLE_SCALE, 
            prev_y*OBSTACLE_SCALE,
            position.getX()*OBSTACLE_SCALE,
            position.getY()*OBSTACLE_SCALE
        );

        drawRobot(new Position(prev_x, prev_y));
        drawRobot(position);

        prev_x = position.getX();
        prev_y = position.getY();
    }
}
