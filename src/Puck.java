import java.awt.*;

/**
 * creates and drwas a puck object on the rink
 * @author Evan Mesa
 * @version 1
 */
public class Puck extends MovingObject {




    public Puck(int id, Point point, int speed, double angle, int radius, Color color) {
        super(id, point, speed, angle, radius, color);
    }

    @Override
    public void setRadius(int radius) {
        super.setRadius(15);
    }



    @Override
    public void updateLocation() {
        location.x = (int) (location.x + getSpeed() * Math.sin(getAngle()));
        location.y = (int) (location.y + getSpeed() * Math.cos(getAngle()));
    }

    public void reflection(double angle, int n){
        double reflectAngle;
        if(n == 1){
            reflectAngle = (-1)*angle+Math.PI;
            setAngle(reflectAngle);
        }
        else if(n == 2){
            reflectAngle = (-1)*angle;
            setAngle(reflectAngle);
        }

    }

    double angleWithArcCenter(int cx, int cy){
        double theta = Math.atan2((location.y-cy), (location.x-cx));
        return theta;
    }

    @Override
    public void hitWalls(){

        if(location.y <= topBoundary + radius ||
                location.y >= bottomBoundary - radius){
            reflection(angle, 1);
        }
        else if(location.x <= leftBoundary + radius ||
                location.x >= rightBoundary - radius ){
            reflection(angle, 2);
        }

        // Arcs and tangents
        if(location.x >= rightBoundary - 100 &&
                location.y >= bottomBoundary - 100){    // 4th corner
            Point center = new Point(rightBoundary - 100, bottomBoundary - 100);
            double distance = Math.hypot(location.x-center.x, location.y-center.y);
            if (distance >= 100-radius){
                double angle = angleWithArcCenter(center.x, center.y);
                reflection(angle, 1);
            }

        }
        else if(location.y <= topBoundary+100 &&
                location.x <= leftBoundary+100){    // 1st corner
            Point center = new Point(leftBoundary+100,topBoundary+100);
            double distance = Math.hypot(location.x-center.x, location.y-center.y);
            if (distance >= 100-radius){
                double angle = angleWithArcCenter(center.x, center.y);
                reflection(angle, 1);
            }
        }
        else if(location.x <= leftBoundary+100 &&
                location.y >= bottomBoundary - 100){    // 3rd corner
            Point center = new Point(leftBoundary+100,bottomBoundary-100);
            double distance = Math.hypot(location.x-center.x, location.y-center.y);
            if (distance >= 100-radius){
                double angle = angleWithArcCenter(center.x, center.y);
                reflection(angle, 1);
            }
        }
        else if(location.y <= topBoundary+100 &&
                location.x >= rightBoundary - 100){     // 2nd Corner
            Point center = new Point(rightBoundary-100,topBoundary+100);
            double distance = Math.hypot(location.x-center.x, location.y-center.y);
            if (distance >= 100-radius){
                double angle = angleWithArcCenter(center.x, center.y);
                reflection(angle, 1);
            }
        }



        // Left goal post
        if(location.x < leftGoalLine && location.y < topGoalPost){
            if(location.y >= topGoalPost-radius && location.x > leftGoalBack){
                reflection(angle, 1);
            }
        }
        else if(location.x < leftGoalLine  && location.y > bottomGoalPost){
            if(location.y <= bottomGoalPost+radius && location.x > leftGoalBack){
                reflection(angle, 1);
            }
        }
        else if(location.x < leftGoalBack && location.y > topGoalPost &&
                location.y < bottomGoalPost){
            if(location.x >= leftGoalBack-radius)
                reflection(angle, 2);
        }

        // Right Goal post
        if(location.x > rightGoalLine && location.y < topGoalPost){
            if(location.y >= topGoalPost-radius && location.x < rightGoalBack){
                reflection(angle, 1);
            }
        }
        else if(location.x > rightGoalLine  && location.y > bottomGoalPost){
            if(location.y <= bottomGoalPost+radius && location.x < rightGoalBack){
                reflection(angle, 1);
            }
        }
        else if(location.x > rightGoalBack && location.y > topGoalPost &&
                location.y < bottomGoalPost){
            if(location.x <= rightGoalBack+radius)
                reflection(angle, 2);
        }

    }
}
