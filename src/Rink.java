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


//lll
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
    int goalieTimer = 0;
    int resetTimer = 0;
    int afterGoalTimer = 0;

    boolean flag = false;
    static int reset = 0;
    static int score = 0;

    static int p1startx = 480;
    static int p1starty = 275;

    static int p2startx = 690;
    static int p2starty = 370;

    static int p3startx = 320;
    static int p3starty = 170;

    static int p4startx = 530;
    static int p4starty = 275;

    ScorePanel scorePanel = new ScorePanel();
    boolean setScore1 = false;
    boolean setScore2 = false;



    Rink() {
        // set a preferred size for the custom panel.
        setPreferredSize(new Dimension(1000,550));
        //setLayout(new BorderLayout());
        add(scorePanel);
        setVisible(true);
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

        rink.setStroke(new BasicStroke(3));

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
            if(players[i] == null){
                continue;
            }
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
        //int i = 0;
        //int frames = 0;


        while(true) {
            //i++;
            //moved = false;
            //dragged = false;
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateAll();
            repaint();

        }
    }

    public void reset(){
        /*
        p1   = new Player(1,new Point(480, 275), 3, 3*Math.PI - 0.523599, 16, Color.RED, puck);
        p2   = new Player(2,new Point(690, 370), 0, 3*Math.PI - 0.523599, 16, Color.GREEN, puck);
        p3   = new Player(3,new Point(320, 170), 3, 4*Math.PI - 0.523599, 16, Color.MAGENTA, puck);
        p4   = new Player(4,new Point(530, 275), 3, 4*Math.PI - 0.523599, 16, Color.BLUE, puck);
        g1   = new Goalie1(5,new Point(190+20, 275), 3, 4*Math.PI - 0.523599, 12, Color.LIGHT_GRAY, puck);
        g2   = new Goalie2(6,new Point(810-20, 275), 3, Math.PI, 12, Color.LIGHT_GRAY, puck);
         */

        double Y1 = p1starty - players[1].location.y;
        double X1 = p1startx - players[1].location.x;
        players[1].setAngle(Math.atan2(Y1, X1));
        players[1].location.x = (int) (players[1].location.x + players[1].speed * Math.cos(players[1].angle));
        players[1].location.y = (int) (players[1].location.y + players[1].speed * Math.sin(players[1].angle));
        players[1].stick.updateLocation();

        double Y2 = p2starty - players[2].location.y;
        double X2 = p2startx - players[2].location.x;
        players[2].setAngle(Math.atan2(Y2, X2));
        players[2].location.x = (int) (players[2].location.x + players[2].speed * Math.cos(players[2].angle));
        players[2].location.y = (int) (players[2].location.y + players[2].speed * Math.sin(players[2].angle));
        players[2].stick.updateLocation();

        double Y3 = p3starty - players[3].location.y;
        double X3 = p3startx - players[3].location.x;
        players[3].setAngle(Math.atan2(Y3, X3));
        players[3].location.x = (int) (players[3].location.x + players[3].speed * Math.cos(players[3].angle));
        players[3].location.y = (int) (players[3].location.y + players[3].speed * Math.sin(players[3].angle));
        players[3].stick.updateLocation();

        double Y4 = p4starty - players[4].location.y;
        double X4 = p4startx - players[4].location.x;
        players[4].setAngle(Math.atan2(Y4, X4));
        players[4].location.x = (int) (players[4].location.x + players[4].speed * Math.cos(players[4].angle));
        players[4].location.y = (int) (players[4].location.y + players[4].speed * Math.sin(players[4].angle));
        players[4].stick.updateLocation();

        if(puck.location.y > puck.horizontalMiddle - 50
                && puck.location.y < puck.horizontalMiddle + 50
                && puck.location.x > puck.verticalCenter - 50
                && puck.location.x < puck.verticalCenter + 50){

            puck.speed = 0;
            resetTimer++;
            if( resetTimer == 600) {
                Player.hold = 0;
                reset = 0;
                score = 0;
                setScore1 = false;
                setScore2 = false;
                afterGoalTimer = 0;
                resetTimer = 0;
            }
        }
    }




    public void updateAll(){

        possession = Player.hold;

        puck.hitWalls();
        puck.updateLocation();
        puck.goalScoredLeft();
        puck.goalScoredRight();


        for(int i = 1; i < players.length; i++){

            if(players[i] == null){
                continue;
            }

            Player mo = players[i];

            if( score == 1 ){
                if(!setScore1) {
                    setScore1 = true;
                    scorePanel.addScore1(1);
                }
                reset = 1;
                afterGoalTimer++;
                reset();
                if(afterGoalTimer >= 180) {
                    players[5].afterGoal();
                }

            }
            else if(score == 2){
                if(!setScore2) {
                    setScore2 = true;
                    scorePanel.addScore2(1);
                }
                reset = 2;
                afterGoalTimer++;
                reset();
                if(afterGoalTimer >= 180) {
                    players[6].afterGoal();
                }
            }

            if(reset == 0) {// if its in reset mode it will skip everything

                if (flag == true) { // for body check
                    frames++;
                    selectedPlayer.bodyCheck();
                }

                if (frames > 4 && frames < 240) {
                    frames++;
                    flag = false;
                    selectedPlayer.speed = 0;
                }
                if (frames == 20) {
                    selectedPlayer.setSpeed(3);
                }

                if (mo == selectedPlayer || mo == selectedPlayer2) {
                    if (mo.colliding) {
                        mo.updateLocationCol();
                    }
                    else if (dragged || moved) {
                        selectedPlayer.updateLocation(e.getX(), e.getY());
                    }

                } else {

                    if (mo.colliding) {
                        mo.updateLocationCol();
                    } else {
                        mo.updateLocation();
                    }
                }


                mo.hitWalls();
                if ((mo.hitWall == 1 || mo.hitWall == 2 || mo.hitWall == 3 ||// if its along any wall, rub
                        mo.hitWall == 4 || mo.hitWall == 5)) {
                    mo.rubWalls();
                    mo.hitWall = 0;
                }

                if (i != possession) {
                    mo.stickHandling();
                }
                //selectedPlayer.stickHandling();


                if (Player.hold != 0) {
                    //System.out.println(Player.hold);
                    players[Player.hold].holdPuck();

                    //when goalie gets the puck
                    if (Player.hold == 5 || Player.hold == 6) {
                        goalieTimer++;
                        //System.out.println("goalie catch");
                        if (goalieTimer == 200) {
                            if (Player.hold == 5) {
                                if (players[1].location.x > players[1].leftGoalLine ||
                                        players[2].location.x > players[2].leftGoalLine) {
                                    goaliePassToTeammates1();
                                } else {
                                    players[Player.hold].wristShot();
                                }
                                goalieTimer = 0;
                            } else if (Player.hold == 6) {

                                if (players[3].location.x < players[3].rightGoalLine ||
                                        players[4].location.x < players[4].rightGoalLine) {
                                    goaliePassToTeammates2();
                                } else {
                                    players[Player.hold].wristShot();
                                }

                                goalieTimer = 0;
                            }

                        }
                    }
                }
            }

        }

        /*
        Collision collision = new Collision(players.length+1);
        for(int i = 1; i < players.length; i++){
            for (int j = i+1; j < players.length; j++){

                if(collision.objectsCollide(players[i], players[j]))
                    collision.calculateCollisions(players[i], players[j]);
            }
        }*/



    }

    public void goaliePassToTeammates1(){
        double Y1 = players[1].location.y - players[5].location.y;
        double X1 = players[1].location.x - players[5].location.x;
        double Y2 = players[2].location.y - players[5].location.y;
        double X2 = players[2].location.x - players[5].location.x;

        double toPlayer1 = Math.sqrt(Math.pow((X1), 2)
                + Math.pow((Y1), 2));

        double toPlayer2 = Math.sqrt(Math.pow((X2), 2)
                + Math.pow((Y2), 2));

        double toPlayer3 = Math.sqrt(Math.pow((players[3].location.x - players[5].location.x), 2)
                + Math.pow((players[3].location.y - players[5].location.y), 2));

        double toPlayer4 = Math.sqrt(Math.pow((players[4].location.x - players[5].location.x), 2)
                + Math.pow((players[4].location.y - players[5].location.y), 2));


        if (toPlayer1 < toPlayer3 && toPlayer1 < toPlayer4 && toPlayer1 < toPlayer2) {// if player one is closest
            System.out.println("pass back to player 1");
            players[5].setAngle(Math.atan2(Y1, X1));
        }
        else if (toPlayer2 < toPlayer3 && toPlayer2 < toPlayer4 && toPlayer2 < toPlayer1) {// if player 2 is closest
            System.out.println("pass back to player 2");
            players[5].setAngle(Math.atan2(Y2, X2));
        }
        else if( (toPlayer3 < toPlayer1 || toPlayer3 < toPlayer2 || toPlayer4 < toPlayer1 || toPlayer4 < toPlayer2)){

            Line line1 = new Line(players[5].location.x, players[1].location.x,
                    players[5].location.y, players[1].location.y );//goalie to player 1
            Line line2 = new Line(players[5].location.x, players[2].location.x,
                    players[5].location.y, players[2].location.y );//goalie to player 2

            double[] distance = new double[4];
            distance[0] = line1.distanceFrom(players[3].location.x, players[3].location.y);
            distance[1]= line1.distanceFrom(players[4].location.x, players[4].location.y);

            distance[2] = line2.distanceFrom(players[3].location.x, players[3].location.y);
            distance[3] = line2.distanceFrom(players[4].location.x, players[4].location.y);
            double max = distance[0];
            for(int i = 1; i < 4; i++){
                if(distance[i] > max){
                    max = distance[i];
                }
            }

            if(max == distance[0] || max == distance[1]){// if the defenders are farthest from the player 1 passing lane, pass to p1
                players[5].setAngle(Math.atan2(Y1, X1));
            }
            if(max == distance[2] || max == distance[3]){// if the defenders are farthest from the player 2 passing lane, pass to p2
                players[5].setAngle(Math.atan2(Y2, X2));
            }
        }

        players[Player.hold].wristShot();
        goalieTimer = 0;
    }

    public void goaliePassToTeammates2(){
        double Y3 = players[3].location.y - players[6].location.y;
        double X3 = players[3].location.x - players[6].location.x;
        double Y4 = players[4].location.y - players[6].location.y;
        double X4 = players[4].location.x - players[6].location.x;

        double toPlayer1 = Math.sqrt(Math.pow((players[1].location.x - players[6].location.x), 2)
                + Math.pow((players[1].location.y - players[6].location.y), 2));

        double toPlayer2 = Math.sqrt(Math.pow((players[2].location.x - players[6].location.x), 2)
                + Math.pow((players[2].location.y - players[6].location.y), 2));

        double toPlayer3 = Math.sqrt(Math.pow((X3), 2)
                + Math.pow((Y3), 2));

        double toPlayer4 = Math.sqrt(Math.pow((X4), 2)
                + Math.pow((Y4), 2));


        if (toPlayer3 < toPlayer1 && toPlayer3 < toPlayer2 && toPlayer3 < toPlayer4) {// if player one is closest
            System.out.println("pass back to player 3");

            players[5].setAngle(Math.atan2(Y3, X3));
        }
        else if (toPlayer4 < toPlayer1 && toPlayer4 < toPlayer2 && toPlayer4 < toPlayer3) {// if player 2 is closest
            System.out.println("pass back to player 4");
            players[5].setAngle(Math.atan2(Y4, X4));
        }
        else if( (toPlayer3 < toPlayer1 || toPlayer3 < toPlayer2 || toPlayer4 < toPlayer1 || toPlayer4 < toPlayer2)){

            Line line1 = new Line(players[6].location.x, players[3].location.x,
                    players[6].location.y, players[3].location.y );//goalie to player 1
            Line line2 = new Line(players[6].location.x, players[4].location.x,
                    players[6].location.y, players[4].location.y );//goalie to player 2

            double[] distance = new double[4];
            distance[0] = line1.distanceFrom(players[1].location.x, players[1].location.y);
            distance[1]= line1.distanceFrom(players[2].location.x, players[2].location.y);

            distance[2] = line2.distanceFrom(players[1].location.x, players[1].location.y);
            distance[3] = line2.distanceFrom(players[2].location.x, players[2].location.y);
            double max = distance[0];
            for(int i = 1; i < 4; i++){
                if(distance[i] > max){
                    max = distance[i];
                }
            }

            if(max == distance[0] || max == distance[1]){// if the defenders are farthest from the player 1 passing lane, pass to p1
                players[6].setAngle(Math.atan2(Y3, X3));
            }
            if(max == distance[2] || max == distance[3]){// if the defenders are farthest from the player 2 passing lane, pass to p2
                players[6].setAngle(Math.atan2(Y4, X4));
            }
        }

        players[Player.hold].wristShot();
        goalieTimer = 0;
    }

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
        selectedPlayer2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(Q, "diagUpLeft");
        selectedPlayer2.getActionMap().put("diagUpLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("testq");
                selectedPlayer2.moveY(5);
                selectedPlayer2.moveX(-5);
                selectedPlayer2.setAngle(-3 * Math.PI/4);
            }
        });

        KeyStroke Z = KeyStroke.getKeyStroke("Z");
        selectedPlayer2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(Z, "diagDownLeft");
        selectedPlayer2.getActionMap().put("diagDownLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPlayer2.moveY(-5);
                selectedPlayer2.moveX(-5);
                selectedPlayer2.setAngle(3 * Math.PI/4);
            }
        });

        KeyStroke X = KeyStroke.getKeyStroke("X");
        selectedPlayer2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(X, "diagDownRight");
        selectedPlayer2.getActionMap().put("diagDownRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPlayer2.moveY(-5);
                selectedPlayer2.moveX(5);
                selectedPlayer2.setAngle(Math.PI/4);
            }
        });

        KeyStroke E = KeyStroke.getKeyStroke("E");
        selectedPlayer2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(E, "diagUpRight");
        selectedPlayer2.getActionMap().put("diagUpRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedPlayer2.moveY(5);
                selectedPlayer2.moveX(5);
                selectedPlayer2.setAngle(-Math.PI/4);
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