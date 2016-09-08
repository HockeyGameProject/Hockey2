import java.awt.*;


public class Goalie1 extends Player {

    //Stick stick; m
    int count = 0;
    double slope = (double) (horizontalMiddle - puck.location.y) / (leftGoalLine - puck.location.x);
    double saveSpot = (horizontalMiddle + (210- leftGoalLine)*slope);

    //private Puck puck;
    public Goalie1(int id, Point point, int speed, double angle, int radius, Color color, Puck puck) {
        super(id, point, speed, angle, radius, color, puck);
        //this.stick = new Stick(40);
    }


    @Override
    public void setRadius(int radius) {
        super.setRadius(45);
    }




    public void moveGoalieDown(){
        count++;
        if(count == 2) {
            location.y = location.y + 1; // moves down one pixel per frame
            count = 0;
        }
    }
    public void moveGoalieUp(){
        count++;
        if(count == 2) {
            location.y = location.y - 1;
            count = 0;
        }
    }

    public void moveGoalieBehindNet(){

    }

    @Override
    public void updateLocation() {
        stick.updateLocation();
        location.x = 210;
        double slope = (double) (horizontalMiddle - puck.location.y) / (leftGoalLine - puck.location.x);
        double saveSpot = (horizontalMiddle + (210- leftGoalLine)*slope);
        double Y = puck.location.y - location.y;//makes it face puck
        double X = puck.location.x - location.x;

        setAngle(Math.atan2(Y, X));



        //updateLocationGoalie(location.x, location.y, angle);

        // if puck is behind goal line
        if (puck.location.x <= leftGoalLine){
            if ( puck.location.y < topGoalPost){

                moveGoalieUp();
                if(location.y <= topGoalPost) {
                    location.y = topGoalPost;
                    setAngle(3 *Math.PI/4);
                }
            }
            else if ( puck.location.y > bottomGoalPost){
                moveGoalieDown();
                if(location.y >= bottomGoalPost) {
                    location.y = bottomGoalPost;
                    setAngle(Math.PI/2);
                }
            }
            else if ( puck.location.y >= topGoalPost && puck.location.y <= bottomGoalPost){
                if(location.y < puck.location.y){
                    moveGoalieDown();
                }
                else if (location.y > puck.location.y){
                    moveGoalieUp();
                }
            }
        }
        else {

            if (saveSpot >= bottomGoalPost) {
                saveSpot = bottomGoalPost;
            }
            else if (saveSpot <= topGoalPost) {
                saveSpot = topGoalPost;
            }

            if (location.y < saveSpot) {
                location.y = (location.y + 1);// moves up one pixel per frame at at 90 degree angle up
            } else if (location.y > saveSpot) {
                location.y = (location.y - 1); // moves down one pizel per frame
            }
        }

    }

}
