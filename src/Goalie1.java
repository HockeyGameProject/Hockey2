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
        Puck puck = pk;
    }


    @Override
    public void setRadius(int radius) {
        super.setRadius(45);
    }

    @Override
    public void updateLocation() {
        double slope = (275 - puck.location.y) / (190 - puck.location.x);
        double goalieX = (190 + (210-190)/slope);
        setLocation((int)goalieX, 210);
    }

}
