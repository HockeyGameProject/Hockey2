import java.awt.*;

/**
 * creates and drwas a puck object on the rink
 * @author Evan Mesa
 * @version 1
 */
public class Puck extends MovingObject {


    double reflectAngle;

    public Puck(Point point, int speed, double angle, int radius, Color color) {
        super(point, speed, angle, radius, color);
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
        reflection();
        location.x = (int) (location.x + getSpeed() * Math.sin(getAngle()));
        location.y = (int) (location.y + getSpeed() * Math.cos(getAngle()));
    }
}
