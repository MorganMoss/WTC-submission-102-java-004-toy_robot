package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.Position;


import java.awt.Color;
import org.turtle.StdDraw;

public class TurtleWorld extends AbstractWorld{
    private int prev_x = 0;
    private int prev_y = 0;
    double scale = 1;
    private static double WINDOW_SCALE = 1.1;
    private static int WINDOW_SIZE= 3;

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
        drawBounds();
        showObstacles();
        drawRobot(position);
    }

    @Override
    public UpdateResponse updatePosition(int nrSteps) {
        UpdateResponse status = super.updatePosition(nrSteps);
        if (status == UpdateResponse.SUCCESS) showPositionUpdate();
        return status;
    }
    
    @Override
    public void showObstacles() {
        for (Obstacle obstacle : obstacles){
            drawObstacle(obstacle);
        }
    }


    private void drawObstacle(Obstacle obstacle){
        StdDraw.setPenRadius(0.002);
        
        StdDraw.setPenColor(Color.RED);
        StdDraw.filledSquare(
            obstacle.getBottomLeftX()*scale,
            obstacle.getBottomLeftY()*scale,
            obstacle.getSize()*scale/2
            );
        // StdDraw.setPenColor(Color.ORANGE);
        StdDraw.square(
            obstacle.getBottomLeftX()*scale,
            obstacle.getBottomLeftY()*scale,
            obstacle.getSize()*scale/2
        );
    }
    

    private void drawBounds() {
        // StdDraw.setXscale(-200,200);
        // StdDraw.setYscale(-200,200);
        // StdDraw.disableDoubleBuffering();
        
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(Color.BLACK);

        StdDraw.filledRectangle(
            0,0,
            bounds.getTopRightX()*scale,
            bounds.getTopRightY()*scale
            );
        StdDraw.setPenColor(Color.ORANGE);
        StdDraw.rectangle(
            0,0,
            bounds.getTopRightX()*scale,
            bounds.getTopRightY()*scale
        );
    }
    
    private void drawRobot(Position position){
        float robotSize = 2.5f;
        StdDraw.setPenRadius(0.002);
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.filledSquare(
            position.getX()*scale,
            position.getY()*scale,
            robotSize*scale
            );
        StdDraw.square(
            position.getX()*scale,
            position.getY()*scale,
            robotSize*scale
            );
    }

    private void showPositionUpdate() {
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.line(
            prev_x*scale, 
            prev_y*scale,
            position.getX()*scale,
            position.getY()*scale
        );
        drawRobot(position);
        prev_x = position.getX();
        prev_y = position.getY();
    }


}
