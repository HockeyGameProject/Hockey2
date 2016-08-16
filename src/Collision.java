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
        }
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


}
