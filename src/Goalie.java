import java.awt.*;

/**
 * creates and drwas a puck object on the rink
 * @author Evan Mesa
 * @version 1
 */
public class Goalie extends Player {

    public Goalie(Point point, int speed, double angle, int radius, Color color) {
        super(point, speed, angle, radius, color);
    }

    @Override
    public void setRadius(int radius) {
        super.setRadius(45);
    }

    @Override
    public void updateLocation1G1(Puck puck) {
        double slope = (275 - puck.location.y) / (190 - puck.location.x);
        double goalieX = (190 + (210-190)/slope);
        setLocation((int)goalieX, 210);
    }

    public void updateLocation1G2(Puck puck) {
        double slope = (275 - puck.location.y) / (810 - puck.location.x);
        double goalieX = (190 + (790-810)/slope);
        setLocation((int)goalieX, 790);
    }
}
