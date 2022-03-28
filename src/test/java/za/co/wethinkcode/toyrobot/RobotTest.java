package za.co.wethinkcode.toyrobot;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.TextWorld;
import za.co.wethinkcode.toyrobot.world.IWorld.Direction;

class RobotTest {
    
    @Test
    void initialPosition() {
        Robot robot = new Robot("CrashTestDummy", new TextWorld(new EmptyMaze()));
        assertEquals(IWorld.CENTRE, robot.getPosition());
        assertEquals(Direction.UP, robot.getCurrentDirection());
    }

    @Test
    void dump() {
        Robot robot = new Robot("CrashTestDummy", new TextWorld(new EmptyMaze()));
        assertEquals("[0,0] CrashTestDummy> Ready", robot.toString());
    }

    @Test
    void shutdown() {
        Robot robot = new Robot("CrashTestDummy", new TextWorld(new EmptyMaze()));
        ShutdownCommand command = new ShutdownCommand();
        assertTrue(robot.handleCommand(command));
        assertEquals(IWorld.UpdateResponse.SHUTDOWN, robot.getStatus());
    }

    @Test
    void forward() {
        Robot robot = new Robot("CrashTestDummy", new TextWorld(new EmptyMaze()));
        ForwardCommand command = new ForwardCommand("10");
        assertTrue(robot.handleCommand(command));
        Position expectedPosition = new Position(IWorld.CENTRE.getX(), IWorld.CENTRE.getY() + 10);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals(IWorld.UpdateResponse.SUCCESS, robot.getStatus());
        assertEquals("Moved forward by 10 steps.", robot.getStatus().getMessage());
    }

    @Test
    void forwardforward() {
        Robot robot = new Robot("CrashTestDummy", new TextWorld(new EmptyMaze()));
        assertTrue(robot.handleCommand(new ForwardCommand("10")));
        assertTrue(robot.handleCommand(new ForwardCommand("5")));
        assertEquals(IWorld.UpdateResponse.SUCCESS, robot.getStatus());
        assertEquals("Moved forward by 5 steps.", robot.getStatus().getMessage());
    }

    @Test
    void tooFarForward() {
        Robot robot = new Robot("CrashTestDummy", new TextWorld(new EmptyMaze()));
        assertTrue(robot.handleCommand(new ForwardCommand("1000")));
        assertEquals(IWorld.CENTRE, robot.getPosition());
        assertEquals(IWorld.UpdateResponse.FAILED_OUTSIDE_WORLD, robot.getStatus());
        assertEquals("Sorry, I cannot go outside my safe zone.", robot.getStatus().getMessage());
    }

    @Test
    void help() {
        Robot robot = new Robot("CrashTestDummy", new TextWorld(new EmptyMaze()));
        Command command = new HelpCommand();
        assertTrue(robot.handleCommand(command));
        assertEquals(IWorld.UpdateResponse.HELP, robot.getStatus());
        assertEquals(IWorld.UpdateResponse.HELP.getMessage(), robot.getStatus().getMessage());
    }
}