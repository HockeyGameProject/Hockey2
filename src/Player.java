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

    public Player(int id, Point point, int speed, double angle, int radius, Color color, Puck puck) {
        super(id, point, speed, angle, radius, color);
        this.teamColor = color;
        this.puck = puck;

    }



    public void setPuck(Puck pk){
        puck = pk;
    }

    //Graphics2D stick = (Graphics2D) g;


   // public void followMouse(e)


    public void pass(Puck puck){
        puck.setAngle(getAngle());
        puck.setSpeed(5);
    }

    public void wristShot(Puck puck){
        puck.setAngle(getAngle());
        puck.setSpeed(7);
    }
    public void slapShot(Puck puck){
        puck.setAngle(getAngle());
        puck.setSpeed(13);
    }

    public void bodyCheck(){

    }

    public void steal(){

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
    }

    public void updateLocation(double x, double y){
        setSpeed(5);
        double Y = y - location.y;
        double X = x - location.x;
        //double slope = Y / X;

        setAngle(Math.atan2(Y, X));

        location.x = (int) (location.x + getSpeed() * Math.cos(angle));
        location.y = (int) (location.y + getSpeed() * Math.sin(angle));

    }

}
