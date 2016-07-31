import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * creates a player object
 * @author Evan Mesa
 * @version 1
 */

public class Player extends MovingObject implements MouseMotionListener{

    Color teamColor;

    public Player(int id, Point point, int speed, double angle, int radius, Color color) {
        super(id, point, speed, angle, radius, color);
        this.teamColor = color;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        eventOutput("Mouse dragged", e);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
       e.getX();
    }

    public Player(int id, Point point, int speed, double angle, int radius, Color color, Color teamColor) {
        super(id, point, speed, angle, radius, color);
        this.teamColor = teamColor;
        this.addMouseMotionListener(this);
    }

    //Graphics2D stick = (Graphics2D) g;


   // public void followMouse(e)


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
    }
goal post locations
goal length = 80
goal width = 40

left goal top post 190, 235 (190 left goal Line)
left goal bottom post 190, 315
left goal back top corner 150, 235
left goal back bottom corner 150, 315


right goal top post 810, 235 (810 right goal line)
right goal bottom post 810, 315
right goal back top corner 850, 235
right goal back bottom corner 850, 315

top boundary of the rink 100
bottom boundary 450
left boundary 100
right boundary 900

pucks radius 10
player radius 20
stick length from center of player 40

center green line 275



    */

    public void hitWalls(){
        /*if(location.y <= topBoundary + radius
                || location.y >= bottomBoundary - radius
                || location.x <= leftBoundary + radius
                || location.x >= rightBoundary - radius ){

            setSpeed(0);
        }
        */
        reflection();
        /*
        if((location.y <= bottomGoalPost + radius
                    && location.x >= leftGoalBack - radius
                    && location.x <= leftGoalLine + radius)//bottom of left goal
                || (location.y >= topGoalPost    - radius
                    && location.x >= leftGoalBack - radius
                    && location.x <= leftGoalLine + radius)//top of left goal post
                || (location.y >= topGoalPost - radius
                    &&  location.y <= bottomGoalPost + radius
                    && location.x >= leftGoalBack -radius)// back of left goal
                ){
            setSpeed(0);
        }
        else if ((location.y <= bottomGoalPost + radius
                    && location.x <= rightGoalBack + radius
                    && location.x >= rightGoalLine - radius)//bottom of right goal
                || (location.y >= topGoalPost    - radius
                    && location.x <= rightGoalBack + radius
                    && location.x >= rightGoalLine - radius)//top of right goal
                || (location.y >= topGoalPost - radius
                    &&  location.y <= bottomGoalPost + radius
                    && location.x <= rightGoalBack + radius)//bottom of right goal
        ){

            setSpeed(0);
        }
        */
        if(location.x >= rightGoalLine - radius && location.y <= bottomGoalPost + radius
                && location.y >= topGoalPost - radius){
            setSpeed(0);
        }

        else if(location.x <= leftGoalLine - radius && location.y <= bottomGoalPost + radius
                && location.y >= topGoalPost - radius){
            setSpeed(0);
        }

        if(location.x >= rightGoalLine && ((location.y <= bottomGoalPost + radius
                                                && location.y > horizontalMiddle
                                                && location.x < rightGoalBack)
                                            || (location.y >= topGoalPost - radius
                                                && location.y < horizontalMiddle
                                                && location.x < rightGoalBack)
                                            || (location.x <= rightGoalBack + radius
                                                && location.x > rightGoalBack
                                                && location.y < bottomGoalPost
                                                && location.y > topGoalPost))){
            setSpeed(0);
        }
        else if( location.x <= leftGoalLine && ((location.y <= bottomGoalPost + radius
                                                    && location.y > horizontalMiddle
                                                    && location.x > leftGoalBack)
                                                || (location.y >= topGoalPost - radius
                                                    && location.y < horizontalMiddle
                                                    && location.x > leftGoalBack)
                                                || (location.x >= leftGoalBack - radius
                                                    && location.x < leftGoalBack
                                                    && location.y < bottomGoalPost
                                                    && location.y > topGoalPost))){
            setSpeed(0);
        }

    }

    public void reflection(){
        double reflectAngle;
        if(location.y <= topBoundary + 10 || location.y >= bottomBoundary -10){
            reflectAngle = (-1)*getAngle()+Math.PI;
            setAngle(reflectAngle);
        }
        else if(location.x <= leftBoundary +10 || location.x >= rightBoundary -10 ){
            reflectAngle = (-1)*getAngle();
            setAngle(reflectAngle);
        }

    }

    @Override
    public void updateLocation() {
        //location.x = (int) (location.x + getSpeed() * Math.sin(getAngle()));
        //location.y = (int) (location.y + getSpeed() * Math.cos(getAngle()));
        hitWalls();

        location.x = (int) (location.x + getSpeed() * Math.sin(angle));
        location.y = (int) (location.y + getSpeed() * Math.cos(angle));
    }


}
