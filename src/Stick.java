/**
 * Created by Mesa on 7/13/2016.
 */
import java.awt.*;
import java.awt.geom.Line2D;

/**
 * Created by Mesa on 7/5/2016.
 */
//test21321
public class Stick extends MovingObject{

    Player player;
    int a;
    int b;
    int x;
    int y;

    public Stick(Point point, int speed, double angle, int length, Color color) {
        super(point, speed, angle, length, color);
    }

    public void setStroke(Graphics g){
        Graphics2D stick = (Graphics2D) g;

        stick.setStroke(new BasicStroke(5));
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public void drawStick(Graphics g){

        g.drawLine(a, b, x, y);
    }

    @Override
    public void updateLocation() {
        a = player.getLocation().x;
        b = player.getLocation().y;
        x = (int)( a + 30 * Math.cos(getAngle()));
        y = (int)( b + 30 * Math.sin(getAngle()));
    }
}
