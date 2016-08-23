import javax.swing.*;
import javax.swing.text.Keymap;
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
public class Rink extends JPanel implements Runnable, MouseMotionListener{



    Thread t;
    ArrayList<MovingObject> objects = new ArrayList<>();
    static Player selectedPlayer;
    static Player selectedPlayer2;
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
        super.add(mo);

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
            //moved = false;
            //dragged = false;
            try {
                Thread.sleep(20);
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

        for(MovingObject mo : objects){
            //System.out.println("Current Location: "+mo.location);
            mo.hitWalls();
            if((mo instanceof Player) && (mo.hitWall == 1 ||
                    mo.hitWall == 2 || mo.hitWall == 3 || mo.hitWall == 4)){
                Player p = (Player) mo;
                p.rubWalls();
                //mo.updateLocation();
                p.hitWall = 0;
            }
            if(mo == selectedPlayer || mo == selectedPlayer2) {

                if(dragged || moved) {
                    selectedPlayer.updateLocation(e.getX(), e.getY());
                }

            }else {
                mo.updateLocation();
            }

        }

        // update objects
        //
    }//test

    @Override
    public void mouseDragged(MouseEvent e) {
        dragged = true;

            //selectedPlayer.updateLocation(e.getX(),e.getY());
            //System.out.println(selectedPlayer.getPoint());
        this.e = e;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        moved = true;

            //selectedPlayer.updateLocation(e.getX(),e.getY());
            //System.out.println(selectedPlayer.getPoint());
        this.e = e;
    }
    /*
    public class MoveClass extends AbstractAction {
        Player p1;
        MoveClass(Player p){
            p1 = selectedPlayer;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            System.out.println("test2");
            p1.angle = 3*(Math.PI)/2;

        }
    }*/
    private class MotionAction extends AbstractAction implements ActionListener
    {


        public MotionAction(Player p, double angle)
        {

            p = selectedPlayer2;
            angle = selectedPlayer2.angle;
        }

        public void actionPerformed(ActionEvent e)
        {

        }
    }

    public void useKeys(Player p, double angle){

    }


    public void addKeys(){


        selectedPlayer2.setFocusable(true);
        selectedPlayer2.requestFocusInWindow();


        KeyStroke w = KeyStroke.getKeyStroke("W");
        selectedPlayer2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(w, "up");
        selectedPlayer2.getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPlayer2.moveY(5);
                selectedPlayer2.setAngle(3 *Math.PI/2);
            }
        });


        KeyStroke a = KeyStroke.getKeyStroke("A");
        selectedPlayer2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(a, "left");
        selectedPlayer2.getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPlayer2.moveX(-5);
                selectedPlayer2.setAngle(Math.PI);
            }
        });

        KeyStroke d = KeyStroke.getKeyStroke("D");
        selectedPlayer2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(d, "right");
        selectedPlayer2.getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPlayer2.moveX(5);
                selectedPlayer2.setAngle(0);
            }
        });


        KeyStroke s = KeyStroke.getKeyStroke("S");
        selectedPlayer2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(s, "down");
        selectedPlayer2.getActionMap().put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPlayer2.moveY(-5);
                selectedPlayer2.setAngle(Math.PI/2);
            }
        });

        KeyStroke j = KeyStroke.getKeyStroke("J");
        selectedPlayer2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(j, "wrist shot");
        selectedPlayer2.getActionMap().put("wrist shot", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if puck is possessed, wrist shot
                selectedPlayer2.wristShot(Controller.puck);
            }
        });

        KeyStroke k = KeyStroke.getKeyStroke("K");
        selectedPlayer2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(j, "pass or steal");
        selectedPlayer2.getActionMap().put("pass or steal", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //if puck is possesed, pass, if not, steal
                selectedPlayer2.pass(Controller.puck);
            }
        });



        selectedPlayer.getInputMap().put(KeyStroke.getKeyStroke("J"), "button 1");
        selectedPlayer.getActionMap().put("J", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //wrist shot
            }
        });
        selectedPlayer.getInputMap().put(KeyStroke.getKeyStroke("K"), "button 2");
        selectedPlayer.getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //pass
            }
        });
        selectedPlayer.getInputMap().put(KeyStroke.getKeyStroke("L"), "button 3");
        selectedPlayer.getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //slap shot
            }
        });

    }

}