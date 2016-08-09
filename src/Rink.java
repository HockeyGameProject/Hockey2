import javax.swing.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.awt.geom.Arc2D;
import java.awt.geom.Line2D;
//import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import java.util.ArrayList;


/**
 * creates a Rink on the main panel.
 * @author Evan Mesa
 * @version 1
 */
public class Rink extends JPanel implements Runnable, MouseMotionListener, KeyListener {

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e
     */
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if(c == 's' ){
            System.out.println("pressed");
            /*if( Rink.selectedPlayer == p1)
                Rink.selectedPlayer = p2;
            else
                Rink.selectedPlayer = p1;
                */
        }
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        char c = e.getKeyChar();
        if(c == 's' ){
            System.out.println("pressed");
            /*if( Rink.selectedPlayer == p1)
                Rink.selectedPlayer = p2;
            else
                Rink.selectedPlayer = p1;
            */
        }
    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e
     */
    @Override
    public void keyReleased(KeyEvent e) {
        char c = e.getKeyChar();
        if(c == 's' ){
            System.out.println("pressed");
            /*if( Rink.selectedPlayer == p1)
                Rink.selectedPlayer = p2;
            else
                Rink.selectedPlayer = p1;
            */
        }
    }


    Thread t;
    ArrayList<MovingObject> objects = new ArrayList<>();
    static Player selectedPlayer;
    boolean dragged = false;
    boolean moved = false;
    MouseEvent e = null;

    Rink() {
        // set a preferred size for the custom panel.
        setPreferredSize(new Dimension(1000,550));
        setLayout(new BorderLayout());
    }

    public void add(MovingObject mo){
        objects.add(mo);
    }



    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D rink = (Graphics2D) g;

        rink.setStroke(new BasicStroke(5));

        rink.setColor(Color.RED);
        rink.draw(new Line2D.Double(190, 100, 190, 450)); // first vertical lines on rink
        rink.setColor(Color.BLUE);
        rink.draw(new Line2D.Double(340, 100, 340, 450));
        rink.setColor(Color.RED);
        rink.draw(new Line2D.Double(500, 100, 500, 450));
        rink.setColor(Color.BLUE);
        rink.draw(new Line2D.Double(660, 100, 660, 450));
        rink.setColor(Color.RED);
        rink.draw(new Line2D.Double(810, 100, 810, 450)); // last line

        rink.setColor(Color.GREEN);// center X line
        rink.draw(new Line2D.Double(190, 275, 810, 275));

        rink.setColor(Color.RED); //goals
        rink.draw(new Rectangle2D.Double(150, 235, 40, 80));
        rink.draw(new Rectangle2D.Double(810, 235, 40, 80));

        rink.drawOval(445, 220, 110, 110);
        rink.setColor(Color.BLACK);
        rink.draw(new RoundRectangle2D.Double(100, 100, 800, 350, 200, 200));

        rink.setColor(Color.BLUE);//crease
        rink.fillArc(190-40, 232, 86, 86, 90, -180);
        rink.fillArc(810-40-5, 232, 86, 86, 90, 180);

        rink.setColor(Color.BLACK);
        Arc2D arc1 = new Arc2D.Double(100, 100, 200, 200, 90, 90, Arc2D.OPEN);
        rink.draw(arc1);
        Arc2D arc2 = new Arc2D.Double(100, 250, 200, 200, 180, 90, Arc2D.OPEN);
        rink.draw(arc2);

        Arc2D arc3 = new Arc2D.Double(700, 100, 200, 200, 0, 90, Arc2D.OPEN);
        rink.draw(arc3);

        Arc2D arc4 = new Arc2D.Double(700, 250, 200, 200, 270, 90, Arc2D.OPEN);
        rink.draw(arc4);


        for(MovingObject mo : objects){
            mo.draw(rink);
        }
    }

    @Override
    public void addNotify() {
        super.addNotify();

        t = new Thread(this);
        t.start();
    }

    public void startGame(){
        if(t == null) {
            t = new Thread(this, "Rink");
            t.start();
        }
    }

    @Override
    public void run() {
        System.out.println("RUNNING");
        int i = 0;

        while(i++ < 1000) {
            moved = false;
            dragged = false;
            try {
                Thread.sleep(33);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateAll();
            repaint();

        }
    }



    ArrayList<ArrayList<MovingObject>> collisionList = new ArrayList<>();
    ArrayList<MovingObject> twoObjectsCollide = new ArrayList<>();
    public void updateAll(){
        // Collision detection

        if(dragged || moved){
            selectedPlayer.updateLocation(e.getX(),e.getY());
        }
        for(MovingObject mo : objects){
            //System.out.println("Current Location: "+mo.location);
            selectedPlayer.hitWalls();
            if(mo == selectedPlayer) {
                if (!dragged || !moved) {
                    //System.out.println("test");
                    mo.updateLocation();
                }

                if(mo.hitWall == 1 || mo.hitWall == 2 || mo.hitWall == 3 || mo.hitWall == 4){

                    selectedPlayer.rubWalls();
                    selectedPlayer.updateLocation(e.getX(), e.getY());
                    selectedPlayer.hitWall = 0;
                }
            }


            //if(mo instanceof Player && mo != selectedPlayer)
            mo.updateLocation();
            /*(for(MovingObject ob : objects){
                if(mo != ob) {
                    //Collision.objectsCollide(mo, ob);
                }
                //collisionList.add(twoObjectsCollide);
            }*/

            //System.out.println("Updated Location: "+mo.location);
        }

        for(ArrayList<MovingObject> col : collisionList){// for each list in the list of list
            //handle collisions set angle and speed with
            col.get(0);
            col.get(1);
        }
        // update objects
        //
    }//test

    @Override
    public void mouseDragged(MouseEvent e) {
        dragged = true;
        if(selectedPlayer != null) {
            //selectedPlayer.updateLocation(e.getX(),e.getY());
            //System.out.println(selectedPlayer.getPoint());
            this.e = e;
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        moved = true;
        if(selectedPlayer != null) {
            //selectedPlayer.updateLocation(e.getX(),e.getY());
            //System.out.println(selectedPlayer.getPoint());
            this.e = e;
        }
    }

}