import java.awt.*;

/**
 * creates and drwas a puck object on the rink
 * @author Evan Mesa
 * @version 1
 */
public class Puck extends MovingObject {


    double reflectAngle;

    public Puck(int id, Point point, int speed, double angle, int radius, Color color) {
        super(id, point, speed, angle, radius, color);
    }

    @Override
    public void setRadius(int radius) {
        super.setRadius(15);
    }

    public void reflection(){
        if(location.y <= topBoundary + 10 || location.y >= bottomBoundary -10){
            reflectAngle = (-1)*getAngle()+Math.PI;
            setAngle(reflectAngle);
        }
        else if(location.x <= leftBoundary +10 || location.x >= rightBoundary -10 ){
            reflectAngle = (-1)*getAngle();
            setAngle(reflectAngle);
        }



    }

    @Override
    public void updateLocation() {
        hitWalls();
        location.x = (int) (location.x + getSpeed() * Math.sin(getAngle()));
        location.y = (int) (location.y + getSpeed() * Math.cos(getAngle()));
    }

    public void hitWalls(){

        reflection();
        if(location.x >= rightGoalLine - radius && location.y <= bottomGoalPost + radius
                && location.y >= topGoalPost - radius){
            reflection();
        }

        else if(location.x <= leftGoalLine - radius && location.y <= bottomGoalPost + radius
                && location.y >= topGoalPost - radius){
            reflection();
        }

        if(location.x >= rightGoalLine && ((location.y <= bottomGoalPost + radius
                && location.y > horizontalMiddle
                && location.x < rightGoalBack)
                || (location.y >= topGoalPost - radius
                && location.y < horizontalMiddle
                && location.x < rightGoalBack)
                || (location.x <= rightGoalBack + radius
                && location.x > rightGoalBack
                && location.y < bottomGoalPost
                && location.y > topGoalPost))){
            reflection();
        }
        else if( location.x <= leftGoalLine && ((location.y <= bottomGoalPost + radius
                && location.y > horizontalMiddle
                && location.x > leftGoalBack)
                || (location.y >= topGoalPost - radius
                && location.y < horizontalMiddle
                && location.x > leftGoalBack)
                || (location.x >= leftGoalBack - radius
                && location.x < leftGoalBack
                && location.y < bottomGoalPost
                && location.y > topGoalPost))){
            reflection();
        }

    }
}
