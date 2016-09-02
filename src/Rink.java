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
    //ArrayList<Player> players = new ArrayList<>();
    Player[] players = new Player[7];
    static Player selectedPlayer;
    static Player selectedPlayer2;
    boolean dragged = false;
    boolean moved = false;
    MouseEvent e = null;
    static int possession = 0;
    Puck puck;
    int frames = 0;
    boolean flag = false;





    Rink() {
        // set a preferred size for the custom panel.
        setPreferredSize(new Dimension(1000,550));
        setLayout(new BorderLayout());
    }


    public void add(Player mo){
        players[mo.id] = mo;
        super.add(mo);
    }

    public void add(Puck puck){
        this.puck = puck;
        super.add(puck);
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


        for(int i = 1; i < players.length; i++){
            players[i].draw(rink);
        }
        puck.draw(rink);
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
        //int frames = 0;


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




    public void updateAll(){
        // Collision detection
        possession = Player.hold;
       // System.out.println(possession);


        puck.hitWalls();
        puck.updateLocation();
        for(int i = 1; i < players.length; i++){
            //System.out.println("Current Location: "+mo.location);
            Player mo = players[i];
            if(flag == true){ // for body check
                frames++;
                selectedPlayer.bodyCheck();
            }

            if(frames > 2 && frames < 120){
                frames++;
                flag = false;
                selectedPlayer.speed = 0;
            }
            if(frames == 120){
                selectedPlayer.setSpeed(3);
            }

            if(mo == selectedPlayer || mo == selectedPlayer2){
                if(dragged || moved) {
                    selectedPlayer.updateLocation(e.getX(), e.getY());
                    //System.out.println(selectedPlayer.stick.b);
                    //System.out.println(MovingObject.topBoundary);

                }

            }
            else {

                mo.updateLocation();
            }



            mo.hitWalls();
            if((mo.hitWall == 1 || mo.hitWall == 2 || mo.hitWall == 3 || mo.hitWall == 4 || mo.hitWall == 5)){
                mo.rubWalls();
                mo.hitWall = 0;
            }

            if(i != possession)
                mo.stickHandling();
            //selectedPlayer.stickHandling();

            if(Player.hold != 0) {
                //System.out.println(Player.hold);
                players[Player.hold].holdPuck();
            }
        }

        Collision collision = new Collision(players.length+1);
        for(int i = 1; i < players.length; i++){
            for (int j = i+1; j < players.length; j++){
                collision.objectsCollide(players[i], players[j]);
            }
        }
        collision.handleCollisions();
        selectedPlayer.updateLocation();
        selectedPlayer2.updateLocationCol();
//hey

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


        //selectedPlayer2.setFocusable(true);
        //selectedPlayer2.requestFocusInWindow();
        //selectedPlayer.setFocusable(true);
        //selectedPlayer.requestFocusInWindow();


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
                System.out.println("testS");
                selectedPlayer2.moveY(-5);
                selectedPlayer2.setAngle(Math.PI/2);
            }
        });

        KeyStroke Q = KeyStroke.getKeyStroke("Q");
        selectedPlayer2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(Q, "diag");
        selectedPlayer2.getActionMap().put("diag", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("testq");
                selectedPlayer2.moveY(5);
                selectedPlayer2.moveX(-5);
                selectedPlayer2.setAngle(-3 * Math.PI/4);
            }
        });

        KeyStroke j = KeyStroke.getKeyStroke("J");
        selectedPlayer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(j, "button1");
        selectedPlayer.getActionMap().put("button1", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("testJ");
                if(selectedPlayer.hold == 1){
                    selectedPlayer.wristShot();
                }
                else{
                    selectedPlayer.steal = 1;
                }


            }
        });

        KeyStroke l = KeyStroke.getKeyStroke("L");
        selectedPlayer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(l, "button3");
        selectedPlayer.getActionMap().put("button3", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("testL");
                if(selectedPlayer.hold == 1){
                    selectedPlayer.slapShot();
                }
                else{
                    if(frames >= 120 || frames == 0) {

                        System.out.println("body check");
                        flag = true;
                        frames = 0;
                    }
                }

            }
        });









    }

}