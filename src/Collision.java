import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Collision {

    //ArrayList<ArrayList<MovingObject>> adjList = new ArrayList<>(7);

    private static int WHITE = 0;
    private static int GRAY  = 1;
    private static int BLACK = 2;

    ArrayList<MovingObject>[] adjList = new ArrayList[7];
    int[] color = new int[7];

    public Collision(){
        for (int i = 0; i < 7; i++){
            adjList[i] = new ArrayList<MovingObject>();
            color[i]   = WHITE;
        }//jjjj
    }

    public boolean objectsCollide(MovingObject object1, MovingObject object2){
        int distance = object1.radius + object2.radius;

        if(Math.sqrt(Math.pow((object2.location.x - object1.location.x), 2)
                + Math.pow((object2.location.y - object1.location.y), 2))
                == distance) {
            adjList[object1.id].add(object2);
            adjList[object2.id].add(object1);
            return true;
        }
        return false;
    }

    public void handleCollisions(){
        // BFS
        LinkedList<MovingObject> queue = new LinkedList<>();

    }

    public void calculateCollisions(MovingObject ob1, MovingObject ob2){
        double Y = ob1.location.y - ob1.location.y;
        double X = ob1.location.x - ob2.location.x;
        double collisionAngle = Math.atan2(Y, X);

        double ob1VelocityX = ob1.speed * Math.cos(ob1.angle - collisionAngle);
        double ob1VelocityY = ob1.speed * Math.sin(ob1.angle - collisionAngle);

        double ob2VelocityX = ob2.speed * Math.cos(ob2.angle - collisionAngle);
        double ob2VelocityY = ob2.speed * Math.sin(ob2.angle - collisionAngle);

        double ob1FinalXVelocity = (ob1VelocityX * (ob1.mass - ob2.mass) + 2 * ob2.mass * ob2VelocityX) / (ob1.mass + ob2.mass);
        double ob2FinalXVelocity = (ob2VelocityX * (ob1.mass - ob2.mass) + 2 * ob2.mass * ob1VelocityX) / (ob1.mass + ob2.mass);

        double ob1TotalVelocity = Math.sqrt( Math.pow(ob1FinalXVelocity, 2) * Math.pow(ob1FinalXVelocity, 2) + ob1VelocityY * Math.pow(ob1VelocityY, 2));
        double ob2TotalVelocity = Math.sqrt( Math.pow(ob2FinalXVelocity, 2) * Math.pow(ob2FinalXVelocity, 2) + ob2VelocityY * Math.pow(ob2VelocityY, 2));

        double ob1Angle = Math.atan2(ob1VelocityY, ob1FinalXVelocity) + ob1.angle;
        double ob2Angle = Math.atan2(ob2VelocityY, ob2FinalXVelocity) + ob2.angle;

        ob1.setSpeed((int) ob1TotalVelocity);
        ob2.setSpeed((int) ob2TotalVelocity);
        ob1.setAngle(ob1Angle);
        ob2.setAngle(ob2Angle);//TE
    }
}
