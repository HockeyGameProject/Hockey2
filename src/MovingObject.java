/**
 * creates and drwas a puck object on the rink
 * @author Evan Mesa
 * @author Aditi Datta
 * @version 2
 */

import java.awt.*;

public abstract class MovingObject extends Component {

    Point   location;
    int     speed;
    double  angle;
    int     radius;
    Color   color;
    int     id;
    int     mass = 10;

    static int topBoundary = 100;
    static int bottomBoundary = 450;
    static int leftBoundary = 100;
    static int rightBoundary = 900;

    static int leftGoalLine = 190;
    static int rightGoalLine = 810;
    static int topGoalPost = 235;
    static int bottomGoalPost = 315;
    static int horizontalMiddle = 275;
    static int leftGoalBack = 150;
    static int rightGoalBack = 850;

    Point arcCenter1 = new Point(leftBoundary+100,topBoundary+100);
    Point arcCenter2 = new Point(rightBoundary-100,topBoundary+100);
    Point arcCenter3 = new Point(leftBoundary+100,bottomBoundary-100);
    Point arcCenter4 = new Point(rightBoundary - 100, bottomBoundary - 100);

    int hitWall = 0;

    public MovingObject(int id, Point point, int speed, double angle, int radius, Color color) {
        this.id       = id;
        this.location = point;
        this.speed    = speed;
        this.angle    = angle;
        this.radius   = radius;
        this.color    = color;
        //this.mass     = mass;
    }
    //test
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




    //test
    public abstract void hitWalls();
    public abstract void updateLocation();

    public void draw(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fillOval(location.x-radius, location.y-radius, radius*2, radius*2);
    }

}
