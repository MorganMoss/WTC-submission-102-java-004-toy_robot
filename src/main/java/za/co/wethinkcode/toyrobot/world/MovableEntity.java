package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Direction;
import za.co.wethinkcode.toyrobot.Position;

public class MovableEntity {
    private Position position;
    private Direction direction;
    
    public MovableEntity(Position origin, Direction direction){
        this.position = origin;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
    
    public Position getPosition() {
        return position;
    }

    public void moveTo(Position newPosition){
        this.position = newPosition;
    }

    public void turnTo(Direction newDirection){
        this.direction = newDirection;
    }
}
