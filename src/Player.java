import net.java.games.input.*;
import net.java.games.input.Component;
import net.java.games.input.Controller;

import java.awt.*;
import java.util.LinkedList;

/**
 * creates a player object
 * @author Aditi Datta
 * @author Evan Mesa
 * @version 1
 */

public class Player extends MovingObject{

    Color teamColor;
    Puck puck;
    Stick stick;
    int release = 0;
    int steal = 0;
    int puckGrabArea = 16;
    double slideAngle= 0;
    int start = 0;
    LinkedList <Double> slideList = new LinkedList<>();
    double oldDistance = 0;
    int oldX = 0;
    int oldY = 0;
    double distance = 0;
    int frames = 0;
    boolean bodyCheckFlag = false;

    public Player(int id, Point point, int speed, double angle, int radius, Color color, Puck puck) {
        super(id, point, speed, angle, radius, color);
        this.teamColor = color;
        this.puck = puck;
        this.stick = new Stick(20);
        dummy_radius = stick.length;
    }


    public void setPuck(Puck pk){
        puck = pk;
    }

    public void draw(Graphics2D g2d){
        stick.draw(g2d);
        g2d.setColor(new Color(color.getRed(), color.getGreen(), color.getBlue(), 60));
        g2d.fillOval(location.x-dummy_radius, location.y-dummy_radius, dummy_radius*2, dummy_radius*2);
        g2d.setColor(color);
        g2d.fillOval(location.x - radius, location.y - radius, radius*2, radius*2); // i think this is right
    }



    public void rubWalls(){


        switch (hitWall){
            case 1:
                location.y = topBoundary + dummy_radius;
                break;
            case 2:
                location.y = bottomBoundary - dummy_radius;
                break;
            case 3:
                location.x = leftBoundary + dummy_radius;
                break;
            case 4:
                location.x = rightBoundary - dummy_radius;
                break;
            case 5:
                location.y = topGoalPost - dummy_radius;//left and right goal top
                break;
            case 6:
                location.y = bottomGoalPost + dummy_radius;//left and right goal bottom
                break;
            case 7:
                location.x = leftGoalBack - dummy_radius;//left  goal back
                break;
            case 8:
                location.x = rightGoalBack + dummy_radius;//right goal back
                break;
            case 9:
                location.x = leftGoalLine + radius;//left goal front
                break;
            case 10:
                location.x = rightGoalLine - radius;//right goal front
                break;
            case 11://top left net corner
                location.x = leftGoalBack - dummy_radius;
                location.y = topGoalPost - dummy_radius;
                break;
            case 12://bottom left net corner
                location.x = leftGoalBack - dummy_radius;
                location.y = bottomGoalPost + dummy_radius;
                break;
            case 13://top right net corner
                location.x = rightGoalBack + dummy_radius;
                location.y = topGoalPost - dummy_radius;
                break;
            case 14://bottom right net corner
                location.x = rightGoalBack + dummy_radius;
                location.y = bottomGoalPost + dummy_radius;
                break;

        }


    }

    double angleWithArcCenter(int cx, int cy){
        double theta = Math.atan2((location.y-cy), (location.x-cx));
        return theta;
    }

    public void hitWalls(){

        dummy_radius = stick.length;
        if(location.y <= topBoundary + dummy_radius || stick.b <= topBoundary){
            hitWall = 1;
        }
        else if(location.y >= bottomBoundary - dummy_radius || stick.b >= bottomBoundary){
            hitWall = 2;
        }
        else if(location.x <= leftBoundary + dummy_radius || stick.a <= leftBoundary){
            hitWall = 3;
        }
        else if(location.x >= rightBoundary - dummy_radius || stick.a >= rightBoundary){
            hitWall = 4;
        }

        else if(location.x < leftGoalLine && location.y  <= topGoalPost){//left goal top
            if(location.y >= topGoalPost - dummy_radius && location.x >= leftGoalBack){
                //System.out.println(location.x + " " + location.y);
                hitWall = 5;
            }
        }
        else if(location.x  < leftGoalLine  && location.y >= bottomGoalPost){//left goal bottom
            if(location.y <= bottomGoalPost + dummy_radius && location.x >= leftGoalBack){
                hitWall = 6;
            }
        }
        else if(location.x < leftGoalBack && location.y > topGoalPost &&//left goal back
                location.y < bottomGoalPost){
            if(location.x >= leftGoalBack- dummy_radius)
                hitWall = 7;
        }
        // Right Goal post
        else if(location.x > rightGoalLine && location.y <= topGoalPost){//right goal top
            if(location.y >= topGoalPost- dummy_radius && location.x <= rightGoalBack){
                hitWall = 5;
            }
        }
        else if(location.x > rightGoalLine  && location.y >= bottomGoalPost){//right goal bottom
            if(location.y <= bottomGoalPost+ dummy_radius && location.x <= rightGoalBack){
                hitWall = 6;
            }
        }
        else if(location.x > rightGoalBack && location.y > topGoalPost &&//right goal back
                location.y < bottomGoalPost){
            if(location.x <= rightGoalBack+ dummy_radius)
                hitWall = 8;
        }

        else if(location.x > leftGoalLine && location.y > topGoalPost &&//left goal front
                location.y < bottomGoalPost){
            if(location.x <= leftGoalLine + radius)
                hitWall = 9;
            else if(location.x >= rightGoalLine - radius) //right goal front
                hitWall = 10;
        }

        else if (location.y < topGoalPost && location.x < leftGoalBack) {//left goal top right corner
            if (location.y + dummy_radius + getSpeed() >= topGoalPost && location.x + dummy_radius + getSpeed()>= leftGoalBack) {
                hitWall = 11;
            }
        }
        else if (location.y > bottomGoalPost && location.x < leftGoalBack) {//left goal bottom  corner
            if (location.y - dummy_radius - getSpeed() <= bottomGoalPost && location.x + dummy_radius + getSpeed()>= leftGoalBack) {
                hitWall = 12;
            }
        }
        else if (location.y < topGoalPost && location.x > rightGoalBack) {//right goal top  corner
            if (location.y + dummy_radius + getSpeed() >= topGoalPost && location.x - dummy_radius - getSpeed()<= rightGoalBack) {
                hitWall = 13;
            }
        }
        else if (location.y > bottomGoalPost && location.x > rightGoalBack) {//right goal bottom  corner
            if (location.y - dummy_radius - getSpeed() <= bottomGoalPost && location.x - dummy_radius - getSpeed()<= rightGoalBack) {
                hitWall = 14;
            }
        }


        if(location.x >= rightBoundary - 100 &&
                location.y >= bottomBoundary - 100){
            Point center = new Point(rightBoundary - 100, bottomBoundary - 100);
            double distance = Math.hypot(location.x-center.x, location.y-center.y);
            if (distance >= 100-dummy_radius){
                double angle = angleWithArcCenter(center.x, center.y);
                location.x = center.x + (int) ((100-dummy_radius)*Math.cos(angle));
                location.y = center.y + (int) ((100-dummy_radius)*Math.sin(angle));
            }

        }
        else if(location.y <= topBoundary+100 &&
                location.x <= leftBoundary+100){
            Point center = new Point(leftBoundary+100,topBoundary+100);
            double distance = Math.hypot(location.x-center.x, location.y-center.y);
            if (distance >= 100-dummy_radius){
                double angle = angleWithArcCenter(center.x, center.y);
                location.x = center.x + (int) ((100-dummy_radius)*Math.cos(angle));
                location.y = center.y + (int) ((100-dummy_radius)*Math.sin(angle));
            }
        }
        else if(location.x <= leftBoundary+100 &&
                location.y >= bottomBoundary - 100){
            Point center = new Point(leftBoundary+100,bottomBoundary-100);
            double distance = Math.hypot(location.x-center.x, location.y-center.y);
            if (distance >= 100-dummy_radius){
                double angle = angleWithArcCenter(center.x, center.y);
                location.x = center.x + (int) ((100-dummy_radius)*Math.cos(angle));
                location.y = center.y + (int) ((100-dummy_radius)*Math.sin(angle));
            }
        }
        else if(location.y <= topBoundary+100 &&
                location.x >= rightBoundary - 100){
            Point center = new Point(rightBoundary-100,topBoundary+100);
            double distance = Math.hypot(location.x-center.x, location.y-center.y);
            if (distance >= 100-dummy_radius){
                double angle = angleWithArcCenter(center.x, center.y);
                location.x = center.x + (int) ((100-dummy_radius)*Math.cos(angle));
                location.y = center.y + (int) ((100-dummy_radius)*Math.sin(angle));
            }
        }
    }


    public void updateLocationCol() {
        collisionFrames++;
        if(collisionFrames >= collisionDuration){
            collisionFrames = 0;
            colliding = false;
        }
        location.x = (int) (location.x + getSpeed() * Math.cos(angle));
        location.y = (int) (location.y + getSpeed() * Math.sin(angle));
        stick.updateLocation();
    }

    @Override
    public void updateLocation() {/*
        double Y = puck.location.y - location.y;
        double X = puck.location.x - location.x;


        setAngle(Math.atan2(Y, X));

        location.x = (int) (location.x + getSpeed() * Math.cos(angle));
        location.y = (int) (location.y + getSpeed() * Math.sin(angle));
        stick.updateLocation();*/
    }


    public void updateLocationController(double xAxisPercentage, double yAxisPercentage){
        int xNeutral = 49;
        int yNeutral = 49;
        double distance = Math.sqrt(Math.pow((xAxisPercentage - xNeutral), 2)
                + Math.pow((yAxisPercentage - yNeutral), 2));

        double controllerX = xAxisPercentage - xNeutral;
        double controllerY = yAxisPercentage - yNeutral;
        double newAngle = Math.atan2(controllerY, controllerX);
        setAngle(newAngle);
        stick.updateLocation();
        if(slideList.size() > 24){
            slideList.addLast(newAngle);
            slideAngle = slideList.pollFirst();
        }
        else{
            slideList.addLast(newAngle);
            slideAngle = newAngle;

        }
        if( distance < 38){// controller grace area. allows you to turn without moving
            start = 1;
            slideList.clear();
            if(Rink.i%10 == 0){//call friction method every 10 frames
                speed = setSpeedFriction();
            }
        }
        else {
            setSpeed(1.5);
            if(start == 1) {
                location.x = (int) (location.x + speed * Math.cos(newAngle));
                location.y = (int) (location.y + speed * Math.sin(newAngle));
                start = 0;
                slideAngle = newAngle;
            }
            else{
                //System.out.println("slide");
                location.x = (int) Math.round((location.x + speed * Math.cos(slideAngle)));
                location.y = (int) Math.round((location.y + speed * Math.sin(slideAngle)));
                //location.x = (int) (location.x + speed * Math.cos(slideAngle));
                //location.y = (int) (location.y + speed * Math.sin(slideAngle));
            }
        }

    }

    public void updateLocation(double x, double y){

        distance = Math.sqrt(Math.pow((location.x - x), 2)
                + Math.pow((location.y - y), 2));

        double Y = y - location.y;
        double X = x - location.x;
        double newAngle = Math.atan2(Y, X);
        setAngle(newAngle);
        stick.updateLocation();

        if(slideList.size() > 24){
            slideList.addLast(newAngle);
            slideAngle = slideList.pollFirst();
        }
        else{
            slideList.addLast(newAngle);
            slideAngle = newAngle;

        }
        if( distance < 80){// controller grace area. allows you to turn without moving
            start = 1;
            slideList.clear();
            if(Rink.i%10 == 0){//call friction method every 10 frames
                speed = setSpeedFriction();
            }
        }
        else {
            setSpeed(1.5);
            if(start == 1) {
                location.x = (int) (location.x + speed * Math.cos(newAngle));
                location.y = (int) (location.y + speed * Math.sin(newAngle));
                start = 0;
                slideAngle = newAngle;
            }
            else{
                //System.out.println("slide");
                location.x = (int) Math.round((location.x + speed * Math.cos(slideAngle)));
                location.y = (int) Math.round((location.y + speed * Math.sin(slideAngle)));
                //location.x = (int) (location.x + speed * Math.cos(slideAngle));
                //location.y = (int) (location.y + speed * Math.sin(slideAngle));
            }
        }
    }


    public void updateLocationKeys(int x, int y){
        location.x =  (location.x + x);
        location.y =  (location.y + y);
        stick.updateLocation();
    }

    public void moveX(int num){
        updateLocationKeys(num, 0);
    }
    public void moveY(int num){
        updateLocationKeys(0, (-1)*num);
    }


    public void stickHandling() {// of its close itll turn on the hold method

        //Puck puck = player.puck;
        int stickHoldingPointX = (int) (location.x + (radius - adjustment) * Math.cos(angle));
        int stickHoldingPointY = (int) (location.y + (radius - adjustment) * Math.sin(angle));
        double distance = Math.sqrt(Math.pow((puck.location.x - stickHoldingPointX), 2)
                + Math.pow((puck.location.y - stickHoldingPointY), 2));

        if (distance <= puckGrabArea && release != 1) {

            if(puck.hold == 0){

                puck.hold = id;
            }
            else if (steal == 1){
                puck.hold = id;
                steal = 0;
            }

            //Rink.possession = id;
        }
        else if (distance > puckGrabArea && release == 1) {
            //System.out.println("distance");
            release = 0;
            //Rink.possession = 0;
        }

    }

    public void holdPuck() {
        //possession = id;
        int stickHoldingPointX = (int) (location.x + 12 * Math.cos(angle));
        int stickHoldingPointY = (int) (location.y + 12 * Math.sin(angle));

        puck.location.x = stickHoldingPointX;
        puck.location.y = stickHoldingPointY;
        puck.speed = speed;
        puck.angle = angle;
    }

    public void wristShot(){
        //Rink.possession = 0;
        release = 1;
        puck.hold = 0;
        //Rink.possession = 0;
        puck.setAngle(angle);
        puck.setSpeed(10);
        puck.updateLocation();
    }

    public void slapShot(){
        //Rink.possession = 0;
        release = 1;
        puck.hold = 0;
        puck.setAngle(angle);
        puck.setSpeed(14);
        puck.updateLocation();
    }

    public void pass(Puck puck){
        puck.setAngle(getAngle());
        puck.setSpeed(5);
    }


    public void bodyCheck(){

            frames++;
            //speed = 3;
            location.x = (int) (location.x + getSpeed() * Math.cos(angle));
            location.y = (int) (location.y + getSpeed() * Math.sin(angle));

        if (frames > 10 && frames < 80) {
            //frames++;
            //bodyCheckFlag = false;
            if(frames < 20){
                speed = 6;
            }
            else if (frames >= 20) {
                setSpeed(0);
               // System.out.println(frames);
            }

        }

        else if(frames >= 80 ){
            bodyCheckFlag = false;
            frames = 0;
            //System.out.println(frames);
        }
    }

    public void bodyCheckStart(){
        System.out.println("start");
        if(frames >= 80 || frames == 0) {

            System.out.println("body check");
            bodyCheckFlag = true;
            frames = 0;
        }
    }

    public void afterGoal() {


        stick.updateLocation();


        double Y;
        double X;
        System.out.println(puck.hold);

        if(release == 0 && puck.hold == 0) {
            stickHandling();
            holdPuck();

            Y = puck.location.y - location.y;
            X = puck.location.x - location.x;
            setAngle(Math.atan2(Y, X));
            //goalie frozen on its track
            location.x = (int) (location.x + 5 * Math.cos(angle));
            location.y = (int) (location.y + 5 * Math.sin(angle));
        }
        else if(puck.hold == 5 || puck.hold == 6){

            Y = horizontalMiddle - location.y;
            X = verticalCenter - location.x;
            setAngle(Math.atan2(Y, X));
            stick.updateLocation();

            double puckY = horizontalMiddle - puck.location.y;
            double puckX = verticalCenter - puck.location.x;


            puck.setAngle(Math.atan2(puckY, puckX));
            puck.setSpeed(4);
            //slapShot();
            puck.location.x = (int) (puck.location.x + puck.speed * Math.cos(puck.angle));
            puck.location.y = (int) (puck.location.y + puck.speed * Math.sin(puck.angle));

        }


    }

    protected class Stick {

        //Player player;
        int x;
        int y;
        int a;
        int b;
        int length;




        public Stick(int length) {
            x = location.x;
            y = location.y;
            this.length = length;
            a = (int) (x + length * Math.cos(getAngle()));
            b = (int) (y + length * Math.sin(getAngle()));
        }



        public void updateLocation() {
            x = location.x;
            y = location.y;
            a = (int)(x + length * Math.cos(angle));
            b = (int)(y + length * Math.sin(angle));
        }






        public void draw(Graphics2D g2d) {
            g2d.setStroke(new BasicStroke(5));
            g2d.setColor(Color.black);
            g2d.drawLine(x, y, a, b);//from center of player cicle to edge of stick
        }
    }
}
