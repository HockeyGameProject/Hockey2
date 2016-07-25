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


    public void pass(Puck puck){
        puck.setAngle(getAngle());
        puck.setSpeed(5);
    }

    public void wristShot(Puck puck){
        puck.setAngle(getAngle());
        puck.setSpeed(7);
    }
    public void slapShot(Puck puck){
        puck.setAngle(getAngle());
        puck.setSpeed(13);
    }

    public void bodyCheck(){

    }

    public void steal(){

    }

    /*public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D object = (Graphics2D) g;
        object.setColor(teamColor);
        object.fillOval(location.x, location.y, this.radius/2, this.radius/2);
    }*/

    /*public void draw(Graphics2D g2d){
        g2d.setColor(color);
        g2d.fillOval(location.x-radius, location.y-radius, radius*2, radius*2);
    }*/


    public void hitWall(){
        if(location.x == topBoundary + 20 || location.x == bottomBoundary -20 || location.y == leftBoundary +20 || location.y == rightBoundary -20 ){

            setSpeed(0);
        }
    }
    @Override
    public void updateLocation() {
        //location.x = (int) (location.x + getSpeed() * Math.sin(getAngle()));
        //location.y = (int) (location.y + getSpeed() * Math.cos(getAngle()));
        hitWall();
        location.x = (int) (location.x + getSpeed() * Math.sin(angle));
        location.y = (int) (location.y + getSpeed() * Math.cos(angle));
    }


}
