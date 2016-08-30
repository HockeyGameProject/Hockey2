import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * creates a player object
 * @author Aditi Datta
 * @author Evan Mesa
 * @version 1
 */

public class Player extends MovingObject{

    Color teamColor;
    Puck puck;
    Stick stick;
    int hold = 0;
    int release = 0;


    public Player(int id, Point point, int speed, double angle, int radius, Color color, Puck puck) {
        super(id, point, speed, angle, radius, color);
        this.teamColor = color;
        this.puck = puck;
        this.stick = new Stick(40);

    }



    public void setPuck(Puck pk){
        puck = pk;
    }

    //Graphics2D stick = (Graphics2D) g;


   // public void followMouse(e)






    public void steal(){

    }

    public void draw(Graphics2D g2d){
        stick.draw(g2d);
        g2d.setColor(color);
        g2d.fillOval(location.x-radius, location.y-radius, radius*2, radius*2);

    }

    public void rubWalls(){
        if ( hitWall == 1){
            location.y = topBoundary + radius;
        }
        else if (hitWall == 2){
            location.y = bottomBoundary - radius;
        }
        if (hitWall == 3){
            location.x = leftBoundary + radius;
        }
        else if (hitWall == 4){
            location.x = rightBoundary - radius;
        }

    }

    double angleWithArcCenter(int cx, int cy){
        double theta = Math.atan2((location.y-cy), (location.x-cx));
        return theta;
    }






    public void hitWalls(){
        if(location.y <= topBoundary + radius){
            hitWall = 1;
        }
        else if(location.y >= bottomBoundary - radius){
            hitWall = 2;
        }
        if(location.x <= leftBoundary + radius){
            hitWall = 3;
        }
        else if(location.x >= rightBoundary - radius){
            hitWall = 4;

        }

        if(location.x >= rightBoundary - 100 &&
                location.y >= bottomBoundary - 100){
            Point center = new Point(rightBoundary - 100, bottomBoundary - 100);
            double distance = Math.hypot(location.x-center.x, location.y-center.y);
            if (distance >= 100-radius){
                double angle = angleWithArcCenter(center.x, center.y);
                location.x = center.x + (int) ((100-radius)*Math.cos(angle));
                location.y = center.y + (int) ((100-radius)*Math.sin(angle));
            }

        }
        else if(location.y <= topBoundary+100 &&
                location.x <= leftBoundary+100){
            Point center = new Point(leftBoundary+100,topBoundary+100);
            double distance = Math.hypot(location.x-center.x, location.y-center.y);
            if (distance >= 100-radius){
                double angle = angleWithArcCenter(center.x, center.y);
                location.x = center.x + (int) ((100-radius)*Math.cos(angle));
                location.y = center.y + (int) ((100-radius)*Math.sin(angle));
            }
        }
        else if(location.x <= leftBoundary+100 &&
                location.y >= bottomBoundary - 100){
            Point center = new Point(leftBoundary+100,bottomBoundary-100);
            double distance = Math.hypot(location.x-center.x, location.y-center.y);
            if (distance >= 100-radius){
                double angle = angleWithArcCenter(center.x, center.y);
                location.x = center.x + (int) ((100-radius)*Math.cos(angle));
                location.y = center.y + (int) ((100-radius)*Math.sin(angle));
            }
        }
        else if(location.y <= topBoundary+100 &&
                location.x >= rightBoundary - 100){
            Point center = new Point(rightBoundary-100,topBoundary+100);
            double distance = Math.hypot(location.x-center.x, location.y-center.y);
            if (distance >= 100-radius){
                double angle = angleWithArcCenter(center.x, center.y);
                location.x = center.x + (int) ((100-radius)*Math.cos(angle));
                location.y = center.y + (int) ((100-radius)*Math.sin(angle));
            }
        }
    }


    @Override
    public void updateLocation() {
        double Y = puck.location.y - location.y;
        double X = puck.location.x - location.x;

        setAngle(Math.atan2(Y, X));

        location.x = (int) (location.x + getSpeed() * Math.cos(angle));
        location.y = (int) (location.y + getSpeed() * Math.sin(angle));
        stick.updateLocation();
    }

    public void updateLocation(double x, double y){
        double distance = Math.sqrt(Math.pow((location.x - x), 2)
                + Math.pow((location.y - y), 2));
        double Y = y - location.y;
        double X = x - location.x;
        if( distance < 80){
            setAngle(Math.atan2(Y, X));
            stick.updateLocation();
        }
        else {
            //setSpeed(3);
            setAngle(Math.atan2(Y, X));
            stick.updateLocation();
            location.x = (int) (location.x + getSpeed() * Math.cos(angle));
            location.y = (int) (location.y + getSpeed() * Math.sin(angle));
        }
    }


    public void updateLocationKeys(int x, int y){
        location.x =  (location.x + x);
        location.y =  (location.y + y);
        stick.updateLocation();
    }

    public void moveX(int num){
        updateLocationKeys(num, 0);
    }
    public void moveY(int num){
        updateLocationKeys(0, (-1)*num);
    }


    public void stickHandling() {// of its close itll turn on the hold method

        //Puck puck = player.puck;
        int stickHoldingPointX = (int) (location.x + 30 * Math.cos(angle));
        int stickHoldingPointY = (int) (location.y + 30 * Math.sin(angle));
        double distance = Math.sqrt(Math.pow((puck.location.x - stickHoldingPointX), 2)
                + Math.pow((puck.location.y - stickHoldingPointY), 2));

        if (distance <= puck.radius && release != 1) {

            puck.location.x = stickHoldingPointX;
            puck.location.y = stickHoldingPointY;
            puck.speed = speed;
            puck.angle = angle;
            hold = 1;

        } else if (distance > puck.radius && release == 1) {
            //hold = 0;
            release = 0;
        }
    }

    public void holdPuck() {
        //possession = id;
        int stickHoldingPointX = (int) (location.x + 30 * Math.cos(angle));
        int stickHoldingPointY = (int) (location.y + 30 * Math.sin(angle));

        puck.location.x = stickHoldingPointX;
        puck.location.y = stickHoldingPointY;
        puck.speed = speed;
        puck.angle = angle;
    }
    public void wristShot(){
        //Rink.possession = 0;
        release = 1;
        hold = 0;
        puck.setAngle(angle);
        puck.setSpeed(8);
        puck.updateLocation();
    }

    public void slapShot(){
        //Rink.possession = 0;
        release = 1;
        hold = 0;
        puck.setAngle(angle);
        puck.setSpeed(16);
        puck.updateLocation();
    }

    public void pass(Puck puck){
        puck.setAngle(getAngle());
        puck.setSpeed(5);
    }

    public void bodyCheck(){
        speed = 4;
        location.x = (int) (location.x + getSpeed() * Math.cos(angle));
        location.y = (int) (location.y + getSpeed() * Math.sin(angle));

    }

    protected class Stick {

        //Player player;
        int x;
        int y;
        int a;
        int b;
        int length;




        public Stick(int length) {
            x = location.x;
            y = location.y;
            this.length = length;
            a = (int) (x + length * Math.cos(getAngle()));
            b = (int) (y + length * Math.sin(getAngle()));
        }



        public void updateLocation() {
            x = location.x;
            y = location.y;
            a = (int)(x + length * Math.cos(angle));
            b = (int)(y + length * Math.sin(angle));
        }






        public void draw(Graphics2D g2d) {
            g2d.setStroke(new BasicStroke(5));
            g2d.setColor(Color.black);
            g2d.drawLine(x, y, a, b);//from center of player cicle to edge of stick
        }
    }
}
