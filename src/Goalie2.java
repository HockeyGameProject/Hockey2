import java.awt.*;


public class Goalie2 extends Player {

    int count = 0;

    public Goalie2(int id, Point point, int speed, double angle, int radius, Color color, Puck puck) {
        super(id, point, speed, angle, radius, color, puck);
    }

    public void moveGoalieDown(){
        count++;
        if(count == 2) {
            location.y = location.y + 1; // moves down one pixel per frame
            count = 0;
        }
        if(location.y >= bottomGoalPost) {
            location.y = bottomGoalPost;
            //setAngle(Math.PI/2);
        }

    }
    public void moveGoalieUp(){
        count++;
        if(count == 2) {
            location.y = location.y - 1;
            count = 0;
        }
        if(location.y <= topGoalPost) {
            location.y = topGoalPost;

        }
    }

    @Override
    public void updateLocation() {
        stick.updateLocation();
        location.x = rightGoalLine - 15;

        double Y = puck.location.y - location.y;//makes it face puck
        double X = puck.location.x - location.x;



        // if puck is behind goal line
        if (puck.location.x >= rightGoalLine){

            if(puck.location.y < horizontalMiddle){
                moveGoalieUp();
                setAngle(angle + 4*Math.PI/180);//rotates clockwise
                System.out.println(angle);
                if(angle >= -Math.PI/2){
                    setAngle( 3*Math.PI/2);
                }
            }
            else if(puck.location.y > horizontalMiddle){
                moveGoalieDown();
                setAngle(angle - 4*Math.PI/180);//roates goalie coutner clockwise
                if(angle <= Math.PI/2){
                    setAngle( -3*Math.PI/2);// i dont know how this works
                }
            }
        }
        else {
            setAngle(Math.atan2(Y, X));
            if(location.y < puck.location.y){
                moveGoalieDown();
            }
            else if (location.y > puck.location.y){
                moveGoalieUp();
            }

        }
    }



}