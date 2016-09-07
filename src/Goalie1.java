import java.awt.*;


public class Goalie1 extends Player {

    //Stick stick;

    //private Puck puck;
    public Goalie1(int id, Point point, int speed, double angle, int radius, Color color, Puck puck) {
        super(id, point, speed, angle, radius, color, puck);
        //this.stick = new Stick(40);
    }


    @Override
    public void setRadius(int radius) {
        super.setRadius(45);
    }




    @Override
    public void updateLocation() {
        stick.updateLocation();
        location.x = 210;
        double Y = puck.location.y - location.y;//makes it face puck
        double X = puck.location.x - location.x;

        setAngle(Math.atan2(Y, X));

        double slope = (double) (horizontalMiddle - puck.location.y) / (leftGoalLine - puck.location.x);
        double saveSpot = (horizontalMiddle + (210- leftGoalLine)*slope);

        //updateLocationGoalie(location.x, location.y, angle);


        if (puck.location.x <= leftGoalLine){
            if ( puck.location.y < topGoalPost){
                location.y = topGoalPost;
            }
            else if ( puck.location.y > bottomGoalPost){
                location.y = bottomGoalPost;
            }
            else{
                if(location.y > puck.location.y){
                    location.y = location.y - 1;// moves up one pixel per frame at at 90 degree angle up
                }
                else if (location.y < puck.location.y){
                    location.y = location.y + 1; // moves down one pizel per frame
                }
            }
        }


        if(saveSpot >= bottomGoalPost){
            saveSpot = bottomGoalPost;
        }
        else if ( saveSpot <= topGoalPost){
            saveSpot = topGoalPost;
        }
        if(location.y < saveSpot){
            location.y = (location.y + 1);// moves up one pixel per frame at at 90 degree angle up
        }
        else if (location.y > saveSpot){
            location.y = (location.y -1 ); // moves down one pizel per frame
        }
    }

}
