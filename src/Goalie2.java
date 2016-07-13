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
        Puck puck = pk;
    }

    @Override
    public void setRadius(int radius) {
        super.setRadius(45);
    }

    @Override


    public void updateLocation() {
        double slope = (275 - puck.location.y) / (810 - puck.location.x);
        double goalieX = (190 + (790-810)/slope);
        setLocation((int)goalieX, 790);
    }
}