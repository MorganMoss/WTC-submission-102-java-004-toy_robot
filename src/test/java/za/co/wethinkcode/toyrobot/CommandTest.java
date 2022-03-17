package za.co.wethinkcode.toyrobot;


import org.junit.jupiter.api.Test;

import za.co.wethinkcode.toyrobot.command.BackCommand;
import za.co.wethinkcode.toyrobot.command.Command;
import za.co.wethinkcode.toyrobot.command.ForwardCommand;
import za.co.wethinkcode.toyrobot.command.HelpCommand;
import za.co.wethinkcode.toyrobot.command.LeftCommand;
import za.co.wethinkcode.toyrobot.command.ReplayCommand;
import za.co.wethinkcode.toyrobot.command.RightCommand;
import za.co.wethinkcode.toyrobot.command.ShutdownCommand;
import za.co.wethinkcode.toyrobot.command.SprintCommand;
import za.co.wethinkcode.toyrobot.obstacle.Robot;
import za.co.wethinkcode.toyrobot.position.Coordinate;
import za.co.wethinkcode.toyrobot.position.Direction;

import static org.junit.jupiter.api.Assertions.*;

class CommandTest {

    // @Test
    // void getShutdownName() {
    //     Command test = new ShutdownCommand();
    //     assertEquals("off", test.getName());
    // }

    // @Test
    // void executeShutdown() {
    //     Robot robot = new Robot("CrashTestDummy");
    //     Command shutdown = Command.create("shutdown");
    //     assertFalse(shutdown.execute(robot));
    //     assertEquals("Shutting down...", robot.getStatus());
    // }

    // @Test
    // void getForwardName() {
    //     ForwardCommand test = new ForwardCommand("100");
    //     assertEquals("forward", test.getName());
    //     assertEquals("100", test.getArgument());
    // }

    // @Test
    // void executeForward() {
    //     Robot robot = new Robot("CrashTestDummy");
    //     Command forward100 = Command.create("forward 10");
    //     assertTrue(forward100.execute(robot));
    //     Coordinate expectedPosition = new Coordinate(Robot.CENTRE.getX(), Robot.CENTRE.getY() + 10);
    //     assertEquals(expectedPosition, robot.getPosition());
    //     assertEquals("Moved forward by 10 steps.", robot.getStatus());
    // }

    // @Test
    // void getHelpName() {
    //     HelpCommand test = new HelpCommand();                                                               
    //     assertEquals("help", test.getName());
    // }
    // @Test
    // void executeHelp() {
    //     Robot robot = new Robot("CrashTestDummy");
    //     Command help = Command.create("help");
    //     assertTrue(help.execute(robot));
    //     assertEquals("I can understand these commands:\n" +
    //             "OFF  - Shut down robot\n" +
    //             "HELP - provide information about commands\n" +
    //             "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'", robot.getStatus());
    // }


    // @Test
    // void getLeftName() {
    //     Command test = new LeftCommand();
    //     assertEquals("left", test.getName());
    // }
    // @Test
    // void executeLeft() {
    //     Command test = Command.create("left");
    //     Robot robot = new Robot("CrashTestDummy");
    //     assertTrue(test.execute(robot));
    //     assertEquals("Turned left.", robot.getStatus());
    //     assertEquals(Direction.WEST, robot.getCurrentDirection());
    // }
    

    // @Test
    // void getRightName() {
    //     Command test = new RightCommand();
    //     assertEquals("right", test.getName());
    // }
    // @Test
    // void executeRight() {
    //     Command test = Command.create("right");
    //     Robot robot = new Robot("CrashTestDummy");
    //     assertTrue(test.execute(robot));
    //     assertEquals("Turned right.", robot.getStatus());
    //     assertEquals(Direction.EAST, robot.getCurrentDirection());
    // }





    // @Test
    // void getbackName() {
    //     Command test = new BackCommand("100");
    //     assertEquals("back", test.getName());
    //     assertEquals("100", test.getArgument());
    // }
    // @Test
    // void executeback() {
    //     Robot robot = new Robot("CrashTestDummy");
    //     Command Back10 = Command.create("back 10");
    //     assertTrue(Back10.execute(robot));
    //     Coordinate expectedPosition = new Coordinate(
    //         Robot.CENTRE.getX(),
    //         Robot.CENTRE.getY() - 10
    //     );
    //     assertEquals(expectedPosition, robot.getPosition());
    //     assertEquals("Moved back by 10 steps.", robot.getStatus());
    // }


    // @Test
    // void getSprintName() {
    //     Command test = new SprintCommand("100");
    //     assertEquals("sprint", test.getName());
    //     assertEquals("100", test.getArgument());
    // }
    // @Test
    // void executeSprint() {
    //     Robot robot = new Robot("CrashTestDummy");
    //     Command sprint5 = Command.create("sprint 5");
    //     assertTrue(sprint5.execute(robot));
    //     Coordinate expectedPosition = new Coordinate(
    //         Robot.CENTRE.getX(),
    //         Robot.CENTRE.getY() + 15
    //     );
    //     assertEquals(expectedPosition, robot.getPosition());
    //     assertEquals("Moved forward by 1 steps.", robot.getStatus());
    // }

    // @Test
    // void getReplayName() {
    //     Command test = new ReplayCommand("");
    //     assertEquals("replay", test.getName());
    //     assertEquals("", test.getArgument());
    // }
    // @Test
    // void executeReplay() {
    //     Robot robot = new Robot("CrashTestDummy");
    //     Command replay = Command.create("replay");
    //     Command.create("forward 10").execute(robot);
    //     Command.create("left").execute(robot);
    //     Command.create("back 2").execute(robot);
    //     Command.create("right").execute(robot);
    //     Command.create("sprint 2").execute(robot);

    //     assertTrue(replay.execute(robot));

    //     Coordinate expectedPosition = new Coordinate(
    //         Robot.CENTRE.getX() + 4,
    //         Robot.CENTRE.getY() + 26
    //     );
    //     assertEquals(expectedPosition, robot.getPosition());
    //     assertEquals("replayed 5 commands.", robot.getStatus());
    // }

    // @Test
    // void createCommand() {
    //     Command forward = Command.create("forward 10");                                                 //<1>
    //     assertEquals("forward", forward.getName());
    //     assertEquals("10", forward.getArgument());

    //     Command shutdown = Command.create("shutdown");                                                  //<2>
    //     assertEquals("off", shutdown.getName());

    //     Command help = Command.create("help");                                                          //<3>
    //     assertEquals("help", help.getName());
    // }

    // @Test
    // void createInvalidCommand() {
    //     try {
    //         Command forward = Command.create("say hello");                                              //<4>
    //         fail("Should have thrown an exception");                                                    //<5>
    //     } catch (IllegalArgumentException e) {
    //         assertEquals("Unsupported command: say hello", e.getMessage());                             //<6>
    //     }
    // }
}
