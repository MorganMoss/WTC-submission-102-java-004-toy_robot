package za.co.wethinkcode.toyrobot.world;

import za.co.wethinkcode.toyrobot.Position;

public class RectangleObstacle implements Obstacle{
    protected Position bottomLeftPosition;
    protected Position topRightPosition;

    public RectangleObstacle(Position bottomLeftPosition, Position topRightPosition){
        this.bottomLeftPosition = bottomLeftPosition;
        this.topRightPosition = topRightPosition;
    }

    
    @Override
    public int getSize() {
        return 0;
    }


    @Override
    public boolean blocksPosition(Position Position) {
        return Position.isIn(this.bottomLeftPosition, this.topRightPosition);
    }

    @Override
    public boolean blocksPath(Position start, Position end) {
        int step = 1;
        if ((start.getY() > end.getY()) || (start.getX() > end.getX()) ){
            step = -1;
        }

        if (start.getX() == end.getX()){
            final int x = start.getX();
            for (int y = start.getY(); y != end.getY() ; y += step)
                if (this.blocksPosition(new Position(x, y)))
                    return true;
        } else {
            final int y = start.getY();
            for (int x = start.getX(); x != end.getX() ; x += step)
                if (this.blocksPosition(new Position(x, y)))
                    return true;
        }

        return false;
    }

    @Override
    public String toString(){
        return "- At position " + bottomLeftPosition.toString() + " (to " + topRightPosition.toString() + ")";
    }


    @Override
    public int getBottomLeftX() {
        return bottomLeftPosition.getX();
    }


    @Override
    public int getBottomLeftY() {
        return bottomLeftPosition.getY();
    }


    @Override
    public int getTopRightX() {
        return topRightPosition.getX();
    }


    @Override
    public int getTopRightY() {
        return topRightPosition.getY();
    }


}
