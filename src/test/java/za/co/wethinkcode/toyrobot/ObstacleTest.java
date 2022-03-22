package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import za.co.wethinkcode.toyrobot.world.Obstacle;
import za.co.wethinkcode.toyrobot.world.RectangleObstacle;
import za.co.wethinkcode.toyrobot.world.SquareObstacle;

public class ObstacleTest {
    @Test
    void rectObsCreate(){
        Obstacle ob =  new RectangleObstacle(new Position(-100, -200), new Position(100, 200));
        assertEquals(-100, ob.getBottomLeftX());
        assertEquals(-200, ob.getBottomLeftY());
        assertEquals(100, ob.getTopRightX());
        assertEquals(200, ob.getTopRightY());
        assertEquals(0, ob.getSize());
        assertEquals( "- At position -100,-200 (to 100,200)", ob.toString());
    }

    @Test
    void sqrObsCreate(){
        Obstacle ob =  new SquareObstacle(new Position(1, 1));
        assertEquals(1, ob.getBottomLeftX());
        assertEquals(1, ob.getBottomLeftY());
        assertEquals(5, ob.getTopRightX());
        assertEquals(5, ob.getTopRightY());
        assertEquals(5, ob.getSize());
        assertEquals( "- At position 1,1 (to 5,5)", ob.toString());

        ob =  new SquareObstacle(new Position(1, 1), 10);
        assertEquals(1, ob.getBottomLeftX());
        assertEquals(1, ob.getBottomLeftY());
        assertEquals(10, ob.getTopRightX());
        assertEquals(10, ob.getTopRightY());
        assertEquals(10, ob.getSize());
        assertEquals( "- At position 1,1 (to 10,10)", ob.toString());

    }

    @Test
    void blocksPositionTest(){
        Obstacle ob =  new SquareObstacle(new Position(1, 1));

        for (int x = 0; x < 7; x++)
        for (int y = 0; y < 7; y++){
            if (x == 0 || x == 6 || y == 0 || y == 6)
                assertFalse(ob.blocksPosition(new Position(x, y)));
            else 
                assertTrue(ob.blocksPosition(new Position(x, y)));
        }

    }

    @Test
    void blocksPathTest(){
        Obstacle ob =  new SquareObstacle(new Position(-2, 1));
        assertTrue(ob.blocksPath(new Position(0, 0), new Position(0, 30)));
        assertFalse(ob.blocksPath(new Position(0, 0), new Position(30, 0)));
        assertFalse(ob.blocksPath(new Position(0, 0), new Position(0, -30)));
        assertFalse(ob.blocksPath(new Position(0, 0), new Position(-30, 0)));
        
        ob =  new SquareObstacle(new Position(1, -2));
        assertFalse(ob.blocksPath(new Position(0, 0), new Position(0, 30)));
        assertTrue(ob.blocksPath(new Position(0, 0), new Position(30, 0)));
        assertFalse(ob.blocksPath(new Position(0, 0), new Position(0, -30)));
        assertFalse(ob.blocksPath(new Position(0, 0), new Position(-30, 0)));

        ob =  new SquareObstacle(new Position(-2, -6));
        assertFalse(ob.blocksPath(new Position(0, 0), new Position(0, 30)));
        assertFalse(ob.blocksPath(new Position(0, 0), new Position(30, 0)));
        assertTrue(ob.blocksPath(new Position(0, 0), new Position(0, -30)));
        assertFalse(ob.blocksPath(new Position(0, 0), new Position(-30, 0)));
        
        ob =  new SquareObstacle(new Position(-6, -2));
        assertFalse(ob.blocksPath(new Position(0, 0), new Position(0, 30)));
        assertFalse(ob.blocksPath(new Position(0, 0), new Position(30, 0)));
        assertFalse(ob.blocksPath(new Position(0, 0), new Position(0, -30)));
        assertTrue(ob.blocksPath(new Position(0, 0), new Position(-30, 0)));

    }
}
