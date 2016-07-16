import java.awt.*;

/**
 * creates and drwas a puck object on the rink
 * @author Evan Mesa
 * @version 1
 */
public class Puck extends MovingObject {

    int topBoundary = 100;
    int bottomBoundary = 450;
    int leftBoundary = 100;
    int rightBoundary = 900;
    double reflectAngle;

    public Puck(Point point, int speed, double angle, int radius, Color color) {
        super(point, speed, angle, radius, color);
    }

    @Override
    public void setRadius(int radius) {
        super.setRadius(15);
    }

    public void reflection(){
        if(location.x == topBoundary || location.x == bottomBoundary || location.y == leftBoundary || location.y == rightBoundary ){
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
