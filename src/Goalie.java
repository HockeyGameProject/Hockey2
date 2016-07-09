import java.awt.*;

/**
 * Created by Mesa on 7/1/2016.
 */
public class Goalie extends Player {

    public Goalie(Point point, int speed, double angle, int radius) {
        super(point, speed, angle, radius);
    }

    @Override
    public void setRadius(int radius) {
        super.setRadius(45);
    }
}
