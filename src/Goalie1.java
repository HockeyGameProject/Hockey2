import java.awt.*;


public class Goalie1 extends Player {

    private Puck puck;
    public Goalie1(Point point, int speed, double angle, int radius, Color color) {
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
        location.y = 210;
        int goalLine = 210;
        int topGoalPost = 235;
        int bottomGoalPost = 315;
        double slope = (275 - puck.location.y) / (190 - puck.location.x);
        int saveSpot = (int)(190 + (210-190)/slope);

        if (puck.location.y <= goalLine){
            if ( puck.location.x < topGoalPost){
                location.x = topGoalPost;
            }
            else if ( puck.location.x > bottomGoalPost){
                location.x = bottomGoalPost;
            }
            else{
                if(location.x > puck.location.x){
                    location.x = (int) (location.x + 1 * Math.sin(-1.570795));// moves up one pixel per frame at at 90 degree angle up
                }
                else if (location.x < puck.location.x){
                    location.x = (int) (location.x + 1 * Math.sin(1.570795)); // moves down one pizel per frame
                }
            }
        }
        else if(location.x > saveSpot){
            location.x = (int) (location.x + 1 * Math.sin(-1.570795));// moves up one pixel per frame at at 90 degree angle up
        }
        else if (location.x < saveSpot){
            location.x = (int) (location.x + 1 * Math.sin(1.570795)); // moves down one pizel per frame
        }
    }

}
