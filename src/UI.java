import javax.swing.*;
import java.awt.*;

/**
 * Created by alien on 7/5/16.
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

