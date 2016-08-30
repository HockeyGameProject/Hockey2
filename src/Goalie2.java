import java.awt.*;


public class Goalie2 extends Player {

    //private Puck puck;
    public Goalie2(int id, Point point, int speed, double angle, int radius, Color color, Puck puck) {
        super(id, point, speed, angle, radius, color, puck);
    }




    @Override
    public void setRadius(int radius) {
        super.setRadius(45);
    }


    @Override
    public void updateLocation() {
        stick.updateLocation();
        location.x = 790;
        double Y = puck.location.y - location.y;
        double X = puck.location.x - location.x;
        setAngle(Math.atan2(Y, X));

        double slope = (double) (horizontalMiddle - puck.location.y) / (rightGoalLine - puck.location.x);
        double saveSpot = (horizontalMiddle + (790-rightGoalLine)*slope);

        //test
        if (puck.location.x >= rightGoalLine){
            if ( puck.location.y < topGoalPost){
                location.y = topGoalPost;
            }
            else if ( puck.location.y > bottomGoalPost){
                location.y = bottomGoalPost;
            }
            else{
                if(location.y > puck.location.y){
                    location.y = location.y + 1;// moves up one pixel per frame at at 90 degree angle up
                }
                else if (location.y < puck.location.y){
                    location.y = location.y + 1; // moves down one pixel per frame
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