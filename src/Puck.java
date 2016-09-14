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
        adjustment = 4;
        dummy_radius = radius + adjustment;
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

    public void goalScoredLeft(){
        if(location.x - radius - adjustment > leftGoalBack
                && location.x + radius + adjustment < leftGoalLine
                && location.y - radius  > topGoalPost
                && location.y + radius  < bottomGoalPost ){
            setSpeed(0);
            Rink.score = 1;
        }
    }

    public void goalScoredRight(){
        if(location.x + radius + adjustment < rightGoalBack
                && location.x - radius - adjustment > rightGoalLine
                && location.y - radius - adjustment/2 > topGoalPost
                && location.y + radius + adjustment/2 < bottomGoalPost ){

            setSpeed(0);
            Rink.score = 2;
        }
    }

    public void hitThePost(){
        double distanceFromLeftTopPost = Math.sqrt(Math.pow((leftGoalLine - location.x), 2)
                + Math.pow((topGoalPost - location.y), 2));
        double distanceFromLeftBottomPost = Math.sqrt(Math.pow((leftGoalLine - location.x), 2)
                + Math.pow((bottomGoalPost - location.y), 2));
        double distanceFromRightTopPost = Math.sqrt(Math.pow((rightGoalLine - location.x), 2)
                + Math.pow((topGoalPost - location.y), 2));
        double distanceFromRightBottomPost = Math.sqrt(Math.pow((rightGoalLine - location.x), 2)
                + Math.pow((bottomGoalPost - location.y), 2));

        if((distanceFromLeftBottomPost < radius
                || distanceFromLeftTopPost < radius
                || distanceFromRightBottomPost < radius
                || distanceFromRightTopPost < radius)
                && (location.x - radius > leftGoalLine
                || location.x + radius < rightGoalLine)) {
            System.out.println("hit post");
            setAngle((-1) * angle + Math.PI);
        }


    }

    public void reflection(double angle, int n){
        double reflectAngle =0;

        if(n == 1){
            reflectAngle = (-1) * angle + Math.PI;
        }
        else if(n == 2){
            reflectAngle = (-1)*angle;
        }
        setAngle(reflectAngle);
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

        double distanceFromLeftTopPost = Math.sqrt(Math.pow((leftGoalLine - location.x), 2)
                + Math.pow((topGoalPost + 3 - location.y), 2));
        double distanceFromLeftBottomPost = Math.sqrt(Math.pow((leftGoalLine - location.x), 2)
                + Math.pow((bottomGoalPost - 3 - location.y), 2));
        double distanceFromRightTopPost = Math.sqrt(Math.pow((rightGoalLine - location.x), 2)
                + Math.pow((topGoalPost + 3 - location.y), 2));
        double distanceFromRightBottomPost = Math.sqrt(Math.pow((rightGoalLine - location.x), 2)
                + Math.pow((bottomGoalPost - 3 - location.y), 2));

        if(location.y <= topBoundary + dummy_radius || location.y >= bottomBoundary -  dummy_radius){
            reflection(angle, 2);
        }
        else if(location.x <= leftBoundary + dummy_radius || location.x >= rightBoundary -  dummy_radius ){
            reflection(angle, 1);
        }


        // Arcs and tangents
        if(location.x >= rightBoundary - 100 &&
                location.y >= bottomBoundary - 100){    // 4th corner
            double distance = Math.hypot(location.x-arcCenter4.x,
                    location.y-arcCenter4.y);
            if (distance >= 100- dummy_radius){
                double refAngle = reflectionAngleWithTangent(arcCenter4);
                reflection(refAngle,2);
            }
        }
        else if(location.y <= topBoundary+100 &&
                location.x <= leftBoundary+100){    // 1st corner
            double distance = Math.hypot(location.x-arcCenter1.x,
                    location.y-arcCenter1.y);
            if (distance >= 100- dummy_radius){
                double refAngle = reflectionAngleWithTangent(arcCenter1);
                reflection(refAngle,1);
            }
        }
        else if(location.x <= leftBoundary+100 &&
                location.y >= bottomBoundary - 100){    // 3rd corner
            double distance = Math.hypot(location.x-arcCenter3.x,
                    location.y-arcCenter3.y);
            if (distance >= 100- dummy_radius){
                double refAngle = reflectionAngleWithTangent(arcCenter3);
                reflection(refAngle,2);
            }
        }
        else if(location.y <= topBoundary+100 &&
                location.x >= rightBoundary - 100){     // 2nd Corner
            Point center = new Point(rightBoundary-100,topBoundary+100);
            double distance = Math.hypot(location.x-center.x, location.y-center.y);
            if (distance >= 100- dummy_radius){
                double refAngle = reflectionAngleWithTangent(arcCenter2);
                //setAngle(refAngle);
                reflection(refAngle,1);
            }
        }



        // Left goal post
        if(location.x < leftGoalLine && location.y < topGoalPost){
            if(location.y >= topGoalPost- dummy_radius && location.x > leftGoalBack){
                reflection(angle, 2);
            }
        }
        else if(location.x < leftGoalLine  && location.y > bottomGoalPost){
            if(location.y <= bottomGoalPost+ dummy_radius && location.x > leftGoalBack){
                reflection(angle, 2);
            }
        }
        else if(location.x < leftGoalBack && location.y > topGoalPost &&
                location.y < bottomGoalPost){
            if(location.x >= leftGoalBack- dummy_radius)
                reflection(angle, 1);
        }


        // Right Goal post
        if(location.x > rightGoalLine && location.y < topGoalPost){
            if(location.y >= topGoalPost- dummy_radius && location.x < rightGoalBack){
                reflection(angle, 2);
            }
        }
        else if(location.x > rightGoalLine  && location.y > bottomGoalPost){
            if(location.y <= bottomGoalPost+ dummy_radius && location.x < rightGoalBack){
                reflection(angle, 2);
            }
        }
        else if(location.x > rightGoalBack && location.y > topGoalPost &&
                location.y < bottomGoalPost){
            if(location.x <= rightGoalBack+ dummy_radius)
                reflection(angle, 1);
        }

        else if((distanceFromLeftBottomPost < radius + adjustment
                || distanceFromLeftTopPost < radius + adjustment
                || distanceFromRightBottomPost < radius + adjustment
                || distanceFromRightTopPost < radius + adjustment)
                && (location.x - radius > leftGoalLine
                || location.x + radius < rightGoalLine)){

            System.out.println("hit post");
            setAngle((-1) * angle + Math.PI);


        }

    }
}
