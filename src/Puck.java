import java.awt.*;

/**
 * creates and drwas a puck object on the rink
 * @author Evan Mesa
 * @version 1
 */
public class Puck extends MovingObject {

    public Puck(Point point, int speed, double angle, int radius) {
        super(point, speed, angle, radius);
    }

    @Override
    public void setRadius(int radius) {
        super.setRadius(15);
    }
}
