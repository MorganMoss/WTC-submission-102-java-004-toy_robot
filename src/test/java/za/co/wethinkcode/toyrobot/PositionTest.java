package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;

import za.co.wethinkcode.toyrobot.position.Coordinate;

import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    public void shouldKnowXandY() {
        Coordinate p = new Coordinate(10, 20);
        assertEquals(10, p.getX());
        assertEquals(20, p.getY());

    }
    @Test
    public void equality() {
        assertEquals(new Coordinate(-44, 63), new Coordinate(-44, 63));
        assertNotEquals(new Coordinate(-44, 63), new Coordinate(0, 63));
        assertNotEquals(new Coordinate(-44, 63), new Coordinate(-44, 0));
        assertNotEquals(new Coordinate(-44, 63), new Coordinate(0, 0));
    }
    @Test
    public void insideRectangularRegion() {
        Coordinate topRight = new Coordinate(20, 20);
        Coordinate bottomLeft = new Coordinate(-20,-20);
        assertTrue((new Coordinate(10,10)).isIn(bottomLeft, topRight), "should be inside");
        assertFalse((new Coordinate(10,30)).isIn(bottomLeft, topRight), "should be beyond top boundary");
        assertFalse((new Coordinate(10,-30)).isIn(bottomLeft, topRight), "should be beyond bottom boundary");
        assertFalse((new Coordinate(30,10)).isIn(bottomLeft, topRight), "should be beyond right boundary");
        assertFalse((new Coordinate(-30,10)).isIn(bottomLeft, topRight), "should be beyond left boundary");
    }
}
