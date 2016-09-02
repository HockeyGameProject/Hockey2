import java.awt.*;

/**
 * creates and draws a puck object on the rink
 * @author Aditi Datta
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
        location.x = (int) (location.x + speed * Math.cos(angle));
        location.y = (int) (location.y + speed * Math.sin(angle));
    }

    public void reflection(double angle, int n){
        double reflectAngle =0;

        if(n == 1){
            /*if(initAngle == Math.PI){
                reflectAngle = 0;
            }
            else if( initAngle == 0){
                reflectAngle = Math.PI;
            }
            else {
                reflectAngle = (-1) * angle + Math.PI;
            }*/

            reflectAngle = (-1) * angle + Math.PI;
        }
        else if(n == 2){
            /*
            if(initAngle == Math.PI/2){
                reflectAngle = 3* Math.PI/2;
            }
            else if( initAngle == 3/2 * Math.PI){
                reflectAngle = Math.PI/2;

            }
            else {
                reflectAngle = (-1)*angle;
            }*/
            reflectAngle = (-1)*angle;
        }
        setAngle(reflectAngle);
        /*
        double adjust;
        if(initAngle >= 2 * Math.PI){
            angle = angle % 2*Math.PI;
        }

        if(n == 1){
            if ( initAngle > Math.PI/2 && initAngle < 3/2 * Math.PI){
                adjust = initAngle - Math.PI;
                angle = initAngle + adjust * 2;
            }
            else if( (initAngle >= -Math.PI/2 && initAngle < Math.PI) || (initAngle > 3/2 * Math.PI) ){
                adjust = Math.PI - initAngle;
                angle = initAngle+ adjust * 2;
            }
        }
        else if( n == 2){
            if( initAngle < Math.PI){
                adjust = Math.PI - initAngle;
                angle = initAngle+ adjust * 2;
            }
            else if ( initAngle > Math.PI){
                adjust = initAngle - Math.PI;
                angle = initAngle + adjust * 2;
            }
        }*/
    }

    double reflectionAngleWithTangent(Point center){
        double angle = angleWithArcCenter(center.x, center.y);
        Point end = new Point();
        end.x = center.x + (int) (100*Math.cos(angle));
        end.y = center.y + (int) (100*Math.sin(angle));
        Line incident = new Line(center, end);
        Point tangentStart = new Point(end);
        Point tangentEnd = new Point();
        double angle1 = angle + ((Math.PI/180)*1);
        tangentEnd.x = center.x + (int) (100*Math.cos(angle1));
        tangentEnd.y = center.y + (int) (100*Math.sin(angle1));
        Line tangent = new Line(tangentStart, tangentEnd);
        double tangentTheta = Math.atan2(tangent.slopeY, tangent.slopeX);
        double lineTheta = Math.atan2(incident.slopeY, incident.slopeX);
        double incidenceAngle = (Math.PI/180)*90 - (lineTheta - tangentTheta);
        return ((Math.PI/180)*90) + (incidenceAngle*2);
    }

    double angleWithArcCenter(int cx, int cy){
        double theta = Math.atan2((location.y-cy), (location.x-cx));
        return theta;
    }

    @Override
    public void hitWalls(){


        /*
        double slope = Math.tan(angle);
        double topCollisionPointX    =  ( location.x - (location.y - topBoundary + radius)/slope );
        double bottomCollisionPointX =  ( location.x - (location.y - bottomBoundary - radius)/slope );
        double leftCollisionPointY   =  ( location.y - (location.x - leftBoundary + radius)*slope);
        double rightCollisonPointY   =  ( location.y - (location.x - rightBoundary - radius)*slope);

        double topDistance = Math.sqrt(Math.pow((location.x - topCollisionPointX), 2)
                + Math.pow((location.y - topBoundary), 2));
        double bottomDistance = Math.sqrt(Math.pow((location.x - bottomCollisionPointX), 2)
                + Math.pow((location.y - bottomBoundary), 2));

        double leftDistance = Math.sqrt(Math.pow((location.x - leftBoundary), 2)
                + Math.pow((location.y - leftCollisionPointY), 2));

        double rightDistance = Math.sqrt(Math.pow((location.x - rightBoundary), 2)
                + Math.pow((location.y - rightCollisonPointY), 2));


        int initSpeed = speed;
        System.out.println(initSpeed);
        if(topDistance < speed ){
            speed = (int)topDistance;
        }
        else if ( bottomDistance < speed) {
            speed = (int )bottomDistance;
        }
        else if(leftDistance < speed ){
            speed = (int) leftDistance;
        }
        else if(rightDistance< speed ){
            speed = (int) rightDistance;
        }

        */
        if(location.y <= topBoundary + radius || location.y >= bottomBoundary - radius){
            reflection(angle, 2);
          //  speed = initSpeed;
        }
        else if(location.x <= leftBoundary + radius || location.x >= rightBoundary - radius ){
            reflection(angle, 1);
            //speed = initSpeed;
        }
        //System.out.println(speed);
        // Arcs and tangents
        if(location.x >= rightBoundary - 100 &&
                location.y >= bottomBoundary - 100){    // 4th corner
            double distance = Math.hypot(location.x-arcCenter4.x,
                    location.y-arcCenter4.y);
            if (distance >= 100-radius){
                double refAngle = reflectionAngleWithTangent(arcCenter4);
                reflection(refAngle,2);
                //speed = initSpeed;
            }

        }
        else if(location.y <= topBoundary+100 &&
                location.x <= leftBoundary+100){    // 1st corner
            double distance = Math.hypot(location.x-arcCenter1.x,
                    location.y-arcCenter1.y);
            if (distance >= 100-radius){
                double refAngle = reflectionAngleWithTangent(arcCenter1);
                reflection(refAngle,1);
            }
        }
        else if(location.x <= leftBoundary+100 &&
                location.y >= bottomBoundary - 100){    // 3rd corner
            double distance = Math.hypot(location.x-arcCenter3.x,
                    location.y-arcCenter3.y);
            if (distance >= 100-radius){
                double refAngle = reflectionAngleWithTangent(arcCenter3);
                reflection(refAngle,2);
            }
        }
        else if(location.y <= topBoundary+100 &&
                location.x >= rightBoundary - 100){     // 2nd Corner
            Point center = new Point(rightBoundary-100,topBoundary+100);
            double distance = Math.hypot(location.x-center.x, location.y-center.y);
            if (distance >= 100-radius){
                double refAngle = reflectionAngleWithTangent(arcCenter2);
                //setAngle(refAngle);
                reflection(refAngle,1);
            }
        }



        // Left goal post
        if(location.x < leftGoalLine && location.y < topGoalPost){
            if(location.y >= topGoalPost-radius && location.x > leftGoalBack){
                reflection(angle, 2);
            }
        }
        else if(location.x < leftGoalLine  && location.y > bottomGoalPost){
            if(location.y <= bottomGoalPost+radius && location.x > leftGoalBack){
                reflection(angle, 2);
            }
        }
        else if(location.x < leftGoalBack && location.y > topGoalPost &&
                location.y < bottomGoalPost){
            if(location.x >= leftGoalBack-radius)
                reflection(angle, 1);
        }

        // Right Goal post
        if(location.x > rightGoalLine && location.y < topGoalPost){
            if(location.y >= topGoalPost-radius && location.x < rightGoalBack){
                reflection(angle, 2);
            }
        }
        else if(location.x > rightGoalLine  && location.y > bottomGoalPost){
            if(location.y <= bottomGoalPost+radius && location.x < rightGoalBack){
                reflection(angle, 2);
            }
        }
        else if(location.x > rightGoalBack && location.y > topGoalPost &&
                location.y < bottomGoalPost){
            if(location.x <= rightGoalBack+radius)
                reflection(angle, 1);
        }

    }
}
