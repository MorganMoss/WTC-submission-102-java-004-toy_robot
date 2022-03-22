package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;

public class WorldTest {
    @Test
    void createWorld(){
        IWorld world = new TextWorld(new EmptyMaze());
        assertTrue(world.getObstacles().size() == 0);
        assertEquals(IWorld.Direction.UP, world.getCurrentDirection());
        assertEquals(new Position(0, 0), world.getPosition());

    }
    
    @Test
    void resetWorld(){
        IWorld world = new TextWorld(new EmptyMaze());
        world.reset();
        assertTrue(world.getObstacles().size() == 0);
        assertEquals(IWorld.Direction.UP, world.getCurrentDirection());
        assertEquals(new Position(0, 0), world.getPosition());
    }
}
