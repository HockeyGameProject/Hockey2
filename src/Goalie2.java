import java.awt.*;


public class Goalie2 extends Player {

    private Puck puck;
    public Goalie2(Point point, int speed, double angle, int radius, Color color) {
        super(point, speed, angle, radius, color);
    }

    public void setPuck(Puck pk){
        puck = pk;
    }


    @Override
    public void setRadius(int radius) {
        super.setRadius(45);
    }


    @Override
    public void updateLocation() {
        location.y = 790;
        int goalLine = 810;
        int topGoalPost = 235;
        int bottomGoalPost = 315;
        int horizontalMiddle = 275;

        double slope = (double) (horizontalMiddle - puck.location.y) / (goalLine - puck.location.x);

        double saveSpot = (horizontalMiddle + (210-goalLine)*slope);//wrong



        if (puck.location.x <= goalLine){
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