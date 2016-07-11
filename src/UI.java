import javax.swing.*;
import java.awt.*;

/**
 * creates a game UI, the main frame containing menu bar and game board
 * @author Aditi Datta
 * @version 1
 */
public class UI extends JFrame implements Runnable{
    public UI(String title){
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
        System.out.println("RUNNING");
    }
}

