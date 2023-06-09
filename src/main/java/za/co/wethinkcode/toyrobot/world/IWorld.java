package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

import java.util.List;

/**
 * Your Text and Turtle worlds must implement this interface.
 */
public interface IWorld {
    /**
     * Enum used to track direction. 
     * Has built-in methods to turn left and right
     * Has a co-ordinate that can be used as a multiplier 
     * to help determine what direction the movement will occur
     */
    enum Direction {
        UP (0,1), 
        RIGHT (1,0), 
        DOWN (0,-1), 
        LEFT (-1,0);
    
        private int x;
        private int y;
    
        Direction(int x, int y){
            this.x = x;
            this.y = y;
        }
    
        public int getX() {
            return x;
        }
    
        public int getY() {
            return y;
        }
        
        /**
         * Takes robot to next direction to the left of the direction given.
         * @param direction : the current direction
         * @return the new direction
         */
        public static Direction left(Direction direction){
            switch(direction){
                case UP : return LEFT;
                case RIGHT: return  UP;
                case DOWN: return RIGHT;
                case LEFT: return DOWN;
                default: return null;
            }
        }

        /**
         * Takes robot to next direction to the right of the direction given.
         * @param direction : the current direction
         * @return the new direction
         */
        public static Direction right(Direction direction){
            switch(direction){
                case UP : return RIGHT;
                case RIGHT: return DOWN;
                case DOWN: return  LEFT;
                case LEFT: return  UP;
                default: return null;
            }
        }

        @Override
        public String toString() {
            return super.toString().toLowerCase();
        }
    }

    /**
     * Enum that indicates response for all commands
     */
    enum UpdateResponse {
        READY ("Ready"),
        SHUTDOWN ("Shutting down..."),
        SUCCESS ("Success"), //command succeeded
        SOLVED ("I am at the "), //maze solved
        FAILED ("Sorry, I did not understand "), //incorrect input
        FAILED_NO_SOLUTION ("Sorry, I cannot solve to that edge."),
        FAILED_OUTSIDE_WORLD ("Sorry, I cannot go outside my safe zone."),
        FAILED_OBSTRUCTED ("Sorry, there is an obstacle in the way."),
        TURNED_LEFT ("Turned left."),
        TURNED_RIGHT ("Turned right."),
        HELP (
            "I can understand these commands:\n" +
            "OFF  - Shut down robot\n" +
            "HELP - provide information about commands\n" +
            "FORWARD - move forward by specified number of steps, e.g. 'FORWARD 10'\n" +
            "BACK    - move backwards by specified number of steps, e.g. 'BACK 10'\n" +
            "LEFT    - turn left\n" +
            "RIGHT   - turn right\n" +
            "SPRINT  - move forward by specified number of steps, then again by one less and so on, e.g. 'SPRINT 5'\n" +
            "MAZERUN - solve the current maze to an edge,  e.g. 'MAZERUN BOTTOM'\n" +
            "REPLAY             - replay commands from history\n" +
            "REPLAY REVERSED    - replay commands in the reverse order" +
            "REPLAY (place)     - replay commands starting from the place specified to the end,  e.g. 'REPLAY 2'\n" +
            "REPLAY (place-place)     - replay commands starting from the place specified to the the specified end,  e.g. 'REPLAY 4-2'\n"
        );
        

        private String message;


        UpdateResponse(String message){
            this.message = message;
        }


        public String getMessage() {
            return message;
        }

        public void setMessage(String message){
            this.message = message;
        }
    }

    Position BOTTOM_LEFT = new Position(-100, -200);
    Position TOP_RIGHT = new Position(100, 200);
    Position CENTRE = new Position(0,0);

    /**
     * Updates the position of your robot in the world by moving the nrSteps in the robots current direction.
     * @param nrSteps steps to move in current direction
     * @return true if this does not take the robot over the world's limits, or into an obstacle.
     */
    UpdateResponse updatePosition(int nrSteps);

    /**
     * Updates the current direction your robot is facing in the world by cycling through the directions UP, RIGHT, BOTTOM, LEFT.
     * @param turnRight if true, then turn 90 degrees to the right, else turn left.
     */
    void updateDirection(boolean turnRight);

    /**
     * Retrieves the current position of the robot
     */
    Position getPosition();

    /**
     * Gets the current direction the robot is facing in relation to a world edge.
     * @return Direction.UP, RIGHT, DOWN, or LEFT
     */
    Direction getCurrentDirection();

    /**
     * Checks if the new position will be allowed, i.e. falls within the constraints of the world, and does not overlap an obstacle.
     * @param position the position to check
     * @return true if it is allowed, else false
     */
    boolean isNewPositionAllowed(Position position);

    /**
     * Checks if the robot is at one of the edges of the world
     * @return true if the robot's current is on one of the 4 edges of the world
     */
    boolean isAtEdge();

    /**
     * Reset the world by:
     * - moving current robot position to center 0,0 coordinate
     * - removing all obstacles
     * - setting current direction to UP
     */
    void reset();

    /**
     * @return the list of obstacles, or an empty list if no obstacles exist.
     */
    List<Obstacle> getObstacles();

    /**
     * Gives opportunity to world to draw or list obstacles.
     */
    void showObstacles();

    UpdateResponse getStatus();
}
