import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

/**
 * Created by Mesa on 7/1/2016.
 */
public class Rink extends JPanel {

    Rink() {
        // set a preferred size for the custom panel.
        setPreferredSize(new Dimension(1000,550));
        setLayout(new BorderLayout());
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D rink = (Graphics2D) g;

        rink.setStroke(new BasicStroke(5));

        rink.setColor(Color.RED);
        rink.draw(new Line2D.Double(190, 100, 190, 450));// 5 vertical lines on rink
        rink.setColor(Color.BLUE);
        rink.draw(new Line2D.Double(340, 100, 340, 450));
        rink.setColor(Color.RED);
        rink.draw(new Line2D.Double(500, 100, 500, 450));
        rink.setColor(Color.BLUE);
        rink.draw(new Line2D.Double(660, 100, 660, 450));
        rink.setColor(Color.RED);
        rink.draw(new Line2D.Double(810, 100, 810, 450));
        rink.drawOval(445, 220, 110, 110);
        rink.setColor(Color.BLACK);
        rink.draw(new RoundRectangle2D.Double(100, 100, 800, 350, 200, 200));
        //rink.fillOval(-25, 25, 20, 20);//5 circles on hockey rink
        //rink.fillOval(-25, -25, 20, 20);
        //rink.fillOval(0, 0, 20, 20);
        //rink.fillOval(25, 25, 20, 20);
        //rink.fillOval(25, -25, 20, 20);
    }

}