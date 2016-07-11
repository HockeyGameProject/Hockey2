/**
 * creates and drwas a puck object on the rink
 * @author Evan Mesa
 * @author Aditi Datta
 * @version 2
 */

import java.awt.*;

public class MovingObject extends Component {

    Point   location;
    int     speed;
    double  angle;
    int     radius;


    public MovingObject(Point point, int speed, double angle, int radius) {
        this.location = point;
        this.speed = speed;
        this.angle = angle;
        this.radius = radius;
    }

    public Point getPoint() {
        return location;
    }

    public void setLocation(Point point) {
        this.location = point;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void setRadius(int radius){
        this.radius = radius;
    }

    public void paint(Graphics g){
        Graphics2D object = (Graphics2D) g;
        object.setColor(Color.BLACK);
        object.fillOval(location.x, location.y, this.radius/2, this.radius/2);
    }



}
