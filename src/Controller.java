import java.awt.*;
import java.util.Random;

/**
 * Main Controller class for the game. It calls update() and step()
 * @author Aditi Datta
 */

//test
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
    Stick    s1;
    Stick    s2;
    Stick    s3;
    Stick    s4;
    Stick    s5;
    Stick    s6;

    public Controller(){
        ui      = new UI("Hockey");
        rink    = new Rink();

        // CREATING PLAYERS AND GOALIES
        p1   = new Player(new Point(480, 275), 5, 3*Math.PI - 0.523599, 20, Color.RED);
        p2   = new Player(new Point(690, 370), 5, 3*Math.PI - 0.523599, 20, Color.GREEN);
        p3   = new Player(new Point(320, 170), 5, 4*Math.PI - 0.523599, 20, Color.MAGENTA);
        p4   = new Player(new Point(530, 275), 5, 4*Math.PI - 0.523599, 20, Color.BLUE);
        g1   = new Goalie1(new Point(190+20, 275), 5, 4*Math.PI - 0.523599, 20, Color.LIGHT_GRAY);
        g2   = new Goalie2(new Point(810-20, 275), 5, 4*Math.PI - 0.523599, 20, Color.DARK_GRAY);
        puck = new Puck(new Point(500, 275), 0, 0.523599, 10, Color.BLACK);
        s1   = new Stick(p1, 5, 3*Math.PI - 0.523599, 40, Color.BLACK);
        s2   = new Stick(p2, 5, 3*Math.PI - 0.523599, 40, Color.BLACK);
        s3   = new Stick(p3, 5, 3*Math.PI - 0.523599, 40, Color.BLACK);
        s4   = new Stick(p4, 5, 3*Math.PI - 0.523599, 40, Color.BLACK);
        s5   = new Stick(g1, 5, 3*Math.PI - 0.523599, 40, Color.BLACK);
        s6   = new Stick(g2, 5, 3*Math.PI - 0.523599, 40, Color.BLACK);

        //s1.setPlayer(p1);
        // GIVING PUCK REFERENCE TO GOALIES
        g1.setPuck(puck);
        g2.setPuck(puck);

        // ADDING OBJECTS TO THE RINK
        rink.add(s1);
        rink.add(p1);
        rink.add(s2);
        rink.add(p2);
        rink.add(s3);
        rink.add(p3);
        rink.add(s4);
        rink.add(p4);
        rink.add(s5);
        rink.add(g1);
        rink.add(s6);
        rink.add(g2);
        rink.add(puck);


        ui.add(rink);
        ui.pack();
        ui.setVisible(true);

    }


}
