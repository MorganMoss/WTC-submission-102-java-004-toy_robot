package za.co.wethinkcode.toyrobot;


import org.junit.jupiter.api.Test;

import za.co.wethinkcode.toyrobot.maze.EmptyMaze;
import za.co.wethinkcode.toyrobot.world.IWorld;
import za.co.wethinkcode.toyrobot.world.IWorld.Direction;
import za.co.wethinkcode.toyrobot.world.TextWorld;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {
    
    @Test
    void createCommand() {
        Command forward = Command.create("forward 10");                                                 
        assertEquals("forward", forward.getName());
        assertEquals("10", forward.getArgument());
    
        Command shutdown = Command.create("shutdown");                                                  
        assertEquals("off", shutdown.getName());
    
        Command help = Command.create("help");                                                          
        assertEquals("help", help.getName());
    }
    @Test
    void createInvalidCommand() {
        try {
            Command.create("say hello");                                              
            fail("Should have thrown an exception");                                                    
        } catch (IllegalArgumentException e) {
            assertEquals("Unsupported command: say hello", e.getMessage());                             
        }
    }


    @Test
    void getShutdownName() {
        Command test = new ShutdownCommand();
        assertEquals("off", test.getName());
    }
    @Test
    void executeShutdown() {
        Robot robot = new Robot("CrashTestDummy", new TextWorld(new EmptyMaze()));
        Command shutdown = Command.create("shutdown");
        assertFalse(shutdown.execute(robot));
        assertEquals("Shutting down...", robot.getStatus().getMessage());
    }


    @Test
    void getForwardName() {
        ForwardCommand test = new ForwardCommand("100");
        assertEquals("forward", test.getName());
        assertEquals("100", test.getArgument());
    }
    @Test
    void executeForward() {
        Robot robot = new Robot("CrashTestDummy", new TextWorld(new EmptyMaze()));
        Command forward100 = Command.create("forward 10");
        assertTrue(forward100.execute(robot));
        Position expectedPosition = new Position(IWorld.CENTRE.getX(), IWorld.CENTRE.getY() + 10);
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Moved forward by 10 steps.", robot.getStatus().getMessage());
    }


    @Test
    void getHelpName() {
        Command test = new HelpCommand();                                                               
        assertEquals("help", test.getName());
    }
    @Test
    void executeHelp() {
        Robot robot = new Robot("CrashTestDummy", new TextWorld(new EmptyMaze()));
        Command help = Command.create("help");
        assertTrue(help.execute(robot));
        assertEquals(IWorld.UpdateResponse.HELP.getMessage(), robot.getStatus().getMessage());
    }

    
    @Test
    void getLeftName() {
        Command test = new LeftCommand();
        assertEquals("left", test.getName());
    }
    @Test
    void executeLeft() {
        Command test = Command.create("left");
        Robot robot = new Robot("CrashTestDummy", new TextWorld(new EmptyMaze()));
        assertTrue(test.execute(robot));
        assertEquals("Turned left.", robot.getStatus().getMessage());
        assertEquals(Direction.LEFT, robot.getCurrentDirection());
    }
    

    @Test
    void getRightName() {
        Command test = new RightCommand();
        assertEquals("right", test.getName());
    }
    @Test
    void executeRight() {
        Command test = Command.create("right");
        Robot robot = new Robot("CrashTestDummy", new TextWorld(new EmptyMaze()));
        assertTrue(test.execute(robot));
        assertEquals("Turned right.", robot.getStatus().getMessage());
        assertEquals(Direction.RIGHT, robot.getCurrentDirection());
    }


    @Test
    void getbackName() {
        Command test = new BackCommand("100");
        assertEquals("back", test.getName());
        assertEquals("100", test.getArgument());
    }
    @Test
    void executeback() {
        Robot robot = new Robot("CrashTestDummy", new TextWorld(new EmptyMaze()));
        Command Back10 = Command.create("back 10");
        assertTrue(Back10.execute(robot));
        Position expectedPosition = new Position(
            IWorld.CENTRE.getX(),
            IWorld.CENTRE.getY() - 10
        );
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Moved back by 10 steps.", robot.getStatus().getMessage());
    }


    @Test
    void getSprintName() {
        Command test = new SprintCommand("100");
        assertEquals("sprint", test.getName());
        assertEquals("100", test.getArgument());
    }
    @Test
    void executeSprint() {
        Robot robot = new Robot("CrashTestDummy", new TextWorld(new EmptyMaze()));
        Command sprint5 = Command.create("sprint 5");
        assertTrue(sprint5.execute(robot));
        Position expectedPosition = new Position(
            IWorld.CENTRE.getX(),
            IWorld.CENTRE.getY() + 15
        );
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("Moved forward by 1 steps.", robot.getStatus().getMessage());
    }

    @Test
    void getReplayName() {
        Command test = new ReplayCommand("");
        assertEquals("replay", test.getName());
        assertEquals("", test.getArgument());
    }
    @Test
    void executeReplay() {
        Robot robot = new Robot("CrashTestDummy", new TextWorld(new EmptyMaze()));
        Command replay = Command.create("replay");
        Command.create("forward 10").execute(robot);
        Command.create("left").execute(robot);
        Command.create("back 2").execute(robot);
        Command.create("right").execute(robot);
        Command.create("sprint 2").execute(robot); //sprint adds 2 forward commands to history

        assertTrue(replay.execute(robot));

        Position expectedPosition = new Position(
            IWorld.CENTRE.getX() + 4,
            IWorld.CENTRE.getY() + 26
        );
        assertEquals(expectedPosition, robot.getPosition());
        assertEquals("replayed 6 commands.", robot.getStatus().getMessage());
    }
}
