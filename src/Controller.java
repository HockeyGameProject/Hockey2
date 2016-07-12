import java.awt.*;

/**
 * Main Controller class for the game. It calls update() and step()
 * @author Aditi Datta
 */
public class Controller {
    UI      ui;
    Rink    rink;
    Player  p1;
    Player  p2;
    Player  p3;
    Player  p4;
    Goalie  g1;
    Goalie  g2;
    Puck    puck;


    public Controller(){
        ui      = new UI("Hockey");
        rink    = new Rink();

        p1      = new Player(new Point(100,200), 5, 0.3, 40, Color.MAGENTA);
        p2      = new Player(new Point(500,200), 5, 0.3, 40, Color.GREEN);
        p3      = new Player(new Point(300,100), 5, 0.3, 40, Color.MAGENTA);
        p4      = new Player(new Point(500,300), 5, 0.3, 40, Color.GREEN);

        rink.add(p1);
        rink.add(p2);
        rink.add(p3);
        rink.add(p4);


        ui.add(rink);
        ui.pack();
        ui.setVisible(true);
    }
}
