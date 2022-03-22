package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.maze.Maze;
import za.co.wethinkcode.toyrobot.maze.SimpleMaze;

public class MazeTest {
    @Test 
    void CreateEmptyMaze(){
        Maze maze = new EmptyMaze();
        assertEquals(0, maze.getObstacles().size());
    }
    @Test 
    void CreateSimpleMaze(){
        Maze maze = new SimpleMaze();
        assertEquals(1, maze.getObstacles().size());
        assertEquals(1, maze.getObstacles().get(0).getBottomLeftX());
        assertEquals(1, maze.getObstacles().get(0).getBottomLeftY());
        assertEquals(5, maze.getObstacles().get(0).getTopRightX());
        assertEquals(5, maze.getObstacles().get(0).getTopRightY());
        assertEquals(5, maze.getObstacles().get(0).getSize());
    }
    @Test
    void CreateRandomMaze(){
        for (int i = 0; i<100; i++){
            Maze maze = new SimpleMaze();
            assertTrue(maze.getObstacles().size()>=0 && maze.getObstacles().size()<=10);
            assertEquals(5, maze.getObstacles().get(0).getSize());
        }
    }
}
