import java.awt.*;

/**
 * Created by alien on 8/16/16.
 */
public class Line {
    int x1;
    int x2;
    int y1;
    int y2;
    int slopeX;
    int slopeY;
    double slope;
    double slopeAngle;
    double A;
    double B;
    double C;


    public Line(int x1, int x2, int y1, int y2){
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
        slopeX = x2 - x1;
        slopeY = y2 - y1;
        slope = ((double)slopeY)/slopeX;
        //C = y2 - mx2
        C = (-1)*(y2-(slope*x2));
        A = (-1)*slope;
        B = 1;
        slopeAngle = Math.atan2(slopeY, slopeX);
    }

    public Line(Point x1y1, Point x2y2){
        x1 = x1y1.x;
        x2 = x2y2.x;
        y1 = x1y1.y;
        y2 = x2y2.y;
        slopeX = x2 - x1;
        slopeY = y2 - y1;
        slope = ((double)slopeY)/slopeX;
        //C = y2 - mx2
        C = (-1)*(y2-(slope*x2));
        A = (-1)*slope;
        B = 1;
        slopeAngle = Math.atan2(slopeY, slopeX);
    }
}
