package za.co.wethinkcode.toyrobot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DirectionTest {
    @Test
    public void testNORTH(){
        assertEquals(0, Direction.NORTH.getX());
        assertEquals(1, Direction.NORTH.getY());
    }

    @Test
    public void testEAST(){
        assertEquals(1, Direction.EAST.getX());
        assertEquals(0, Direction.EAST.getY());
    }   

    @Test
    public void testSOUTH(){
        assertEquals(0, Direction.SOUTH.getX());
        assertEquals(-1, Direction.SOUTH.getY());
    }   
    
    @Test
    public void testWEST(){
        assertEquals(-1, Direction.WEST.getX());
        assertEquals(0, Direction.WEST.getY());
    }
}