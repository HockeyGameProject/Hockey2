/**
 * creates a Stick and adds it to a player.
 * @author Evan Mesa
 * @version 1
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
    //int x;
    //int y;

    public Stick(int id, Player p, int speed, double angle, int length, Color color) {
        super(id, p.location, speed, angle, length, color);
        player = p;
        a = (int)( location.x + length * Math.cos(getAngle()));
        b = (int)( location.y + length * Math.sin(getAngle()));
    }

    /*public void setStroke(Graphics g){
        Graphics2D stick = (Graphics2D) g;

        stick.setStroke(new BasicStroke(5));
    }*/

    public void setPlayer(Player player){
        this.player = player;
    }

    public void setAngle(Player p){
        angle = p.angle;
    }


    @Override
    public void updateLocation() {
        //a = player.getLocation().x;
        //b = player.getLocation().y;
        location = player.location;
        setAngle(player);
        a = (int)( location.x + radius * Math.cos(getAngle()));
        b = (int)( location.y + radius * Math.sin(getAngle()));
    }

    public void hitWalls(){

    }


    @Override
    public void draw(Graphics2D g2d) {

        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(color);
        g2d.drawLine(location.x, location.y, a, b);
    }
}
