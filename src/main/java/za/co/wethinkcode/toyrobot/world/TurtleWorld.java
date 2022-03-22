package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.Position;


import java.awt.Color;

import org.turtle.StdDraw;

public class TurtleWorld extends AbstractWorld{
    private int prev_x = 0;
    private int prev_y = 0;
    double scale = 1;



    public TurtleWorld(Maze maze) {
        super(maze);
        StdDraw.setCanvasSize(200, 400);
        StdDraw.setPenRadius(0.002);
        StdDraw.setXscale(-100,100);
        StdDraw.setYscale(-200,200);
        StdDraw.disableDoubleBuffering();
        DrawRobot(position);
    }

    private void drawObstacle(Obstacle obstacle){
        StdDraw.setPenRadius(0.002);

        StdDraw.setPenColor(Color.CYAN);
        StdDraw.filledSquare(
            obstacle.getBottomLeftX()*scale,
            obstacle.getBottomLeftY()*scale,
            obstacle.getSize()*scale/2
        );
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.square(
            obstacle.getBottomLeftX()*scale,
            obstacle.getBottomLeftY()*scale,
            obstacle.getSize()*scale/2
        );
    }

    public void DrawRobot(Position position){
        float robotSize = 2.5f;
        StdDraw.setPenRadius(0.002);
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.filledSquare(
            position.getX()*scale,
            position.getY()*scale,
            robotSize
        );
        // StdDraw.setPenColor(Color.BLUE);
        StdDraw.square(
            position.getX()*scale,
            position.getY()*scale,
            robotSize
        );

    }

    @Override
    public void showObstacles() {
        drawObstacle(bounds);
        for (Obstacle obstacle : obstacles){
            drawObstacle(obstacle);
        }
    }

    // @Override
    public void showStatus() {
        StdDraw.setPenRadius(0.005);
        StdDraw.setPenColor(Color.BLUE);
        StdDraw.line(
            prev_x*scale, 
            prev_y*scale,
            position.getX()*scale,
            position.getY()*scale
        );
        DrawRobot(position);
        prev_x = position.getX();
        prev_y = position.getY();
    }
    
    

}
