import java.awt.*;

/**
 * creates and drwas a puck object on the rink
 * @author Evan Mesa
 * @version 1
 */
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

    /*public void draw(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fillOval(location.x-radius, location.y-radius, radius*2, radius*2);
    }*/

    @Override
    public void updateLocation() {
        location.y = 210;
        double slope = (275 - puck.location.y) / (190 - puck.location.x);
        int saveSpot = (int)(190 + (210-190)/slope);
        if(location.x < saveSpot){
            location.x = (int) (location.x + 1 * Math.sin(-1.570795));// moves up one pixel per frame at at 90 degree angle up
        }
        else if (location.x > saveSpot){
            location.x = (int) (location.x + 1 * Math.sin(1.570795)); // moves down one pizel per frame
        }

    }

}
