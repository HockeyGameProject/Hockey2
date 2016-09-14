import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

/**
 * Main Controller class for the game. It calls update() and step()
 * @author Aditi Datta
 */


//test1
public class Controller {
    UI       ui;
    Rink     rink;
    Player   p1;
    Player   p2;
    Player   p3;
    Player   p4;
    Goalie1  g1;
    Goalie2  g2;
    Puck     puck;

    MouseEvent e;





    public Controller(){
        ui      = new UI("Hockey");


        // Moving objects
        puck = new Puck(0,new Point(500, 275), 0, 0, 8, Color.BLACK);


        // CREATING PLAYERS AND GOALIES mmm

        p1   = new Player(1,new Point(480, 275), 3, 3*Math.PI - 0.523599, 16, Color.RED, puck);
        p2   = new Player(2,new Point(690, 370), 0, 3*Math.PI - 0.523599, 16, Color.GREEN, puck);
        p3   = new Player(3,new Point(320, 170), 3, 4*Math.PI - 0.523599, 16, Color.MAGENTA, puck);
        p4   = new Player(4,new Point(530, 275), 3, 4*Math.PI - 0.523599, 16, Color.BLUE, puck);
        g1   = new Goalie1(5,new Point(190+20, 275), 3, 4*Math.PI - 0.523599, 12, Color.LIGHT_GRAY, puck);
        g2   = new Goalie2(6,new Point(810-20, 275), 3, Math.PI, 12, Color.LIGHT_GRAY, puck);

        Rink.selectedPlayer = p1;
        Rink.selectedPlayer2 = p2;



        rink    = new Rink();
        rink.addKeys();
        //s1.setPlayer(p1);
        // GIVING PUCK REFERENCE TO GOALIES
        //g1.setPuck(puck);
        //g2.setPuck(puck);

        // ADDING OBJECTS TO THE RINK
        rink.add(p1);
        rink.add(p2);
        rink.add(p3);
        rink.add(p4);

        rink.add(g1);
        rink.add(g2);
        rink.add(puck);
        // added moving objects

        ui.add(rink);
        ui.pack();
        ui.setVisible(true);


        rink.addMouseMotionListener(rink);

       // rink.addKeyListener(rink);
    }

    private class KBListener implements KeyListener {

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
                if( Rink.selectedPlayer == p1)
                    Rink.selectedPlayer = p2;
                else
                    Rink.selectedPlayer = p1;
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
                if( Rink.selectedPlayer == p1)
                    Rink.selectedPlayer = p2;
                else
                    Rink.selectedPlayer = p1;
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
                if( Rink.selectedPlayer == p1)
                    Rink.selectedPlayer = p2;
                else
                    Rink.selectedPlayer = p1;
            }
        }
    }


}
