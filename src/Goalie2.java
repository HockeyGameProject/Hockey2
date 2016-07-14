import java.awt.*;

/**
 * creates and drwas a puck object on the rink
 * @author Evan Mesa
 * @version 1
 */
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
        double slope = (275 - puck.location.y) / (810 - puck.location.x);
        double saveSpot = (190 + (790-810)/slope);
        if(location.x < saveSpot){
            location.x = (int) (location.x + 1 * Math.sin(-1.570795));// moves up one pixel per frame at at 90 degree angle up
        }
        else if (location.x > saveSpot){
            location.x = (int) (location.x + 1 * Math.sin(1.570795)); // moves down one pizel per frame
        }

    }
}