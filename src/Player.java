import java.awt.*;

/**
 * creates a player object
 * @author Evan Mesa
 * @version 1
 */

public class Player extends MovingObject {

    Color teamColor;
    //Graphics2D stick = (Graphics2D) g;

    public Player(Point point, int speed, double angle, int radius, Color color) {
        super(point, speed, angle, radius, color);
        this.teamColor = color;
    }

    @Override
    public void setRadius(int radius) {
        super.setRadius(80);
    }


    public void pass(Puck puck, Player p){
        puck.setAngle(p.getAngle());
        puck.setSpeed(3);
    }

    public void wristShot(Puck puck, Player p){
        puck.setAngle(p.getAngle());
        puck.setSpeed(2);
    }
    public void slapShot(Puck puck, Player p){
        puck.setAngle(p.getAngle());
        puck.setSpeed(6);
    }

    public void bodyCheck(){

    }

    public void steal(){

    }

    public void paint(Graphics g){
        Graphics2D object = (Graphics2D) g;
        object.setColor(teamColor);
        object.fillOval(location.x, location.y, this.radius/2, this.radius/2);
    }

    @Override
    public void updateLocation() {
        location.x = location.x + 0;
        location.y = location.y + 0;
    }


}
