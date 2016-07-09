/**
 * Created by alien on 7/8/16.
 */
public class Controller {
    UI ui;
    Rink rink;
    Player p1;
    Player p2;
    Player p3;
    Player p4;

    public Controller(){
        ui      = new UI("Hockey");
        rink    = new Rink();

        ui.pack();
        ui.setVisible(true);
    }
}
