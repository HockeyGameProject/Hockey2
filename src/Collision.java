import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mesa on 7/15/2016.
 */
public class Collision {

    public static ArrayList<MovingObject> objectsCollide(MovingObject object1, MovingObject object2){
        int distance = object1.radius + object2.radius;

        ArrayList<MovingObject> twoObjectsCollide = new ArrayList<>();
        //ArrayList<ArrayList<MovingObject>> collisionList = new ArrayList<>();
        if(Math.sqrt(Math.pow((object2.location.x - object1.location.x), 2)
                + Math.pow((object2.location.y - object1.location.y), 2))
                == distance){


            //out object in collision array
            twoObjectsCollide.add(object1);
            twoObjectsCollide.add(object2);
            //collisionList.add(twoObjectsCollide);


        }
        return twoObjectsCollide;
    }

    //public boolean edgeOfRinkCollision(MovingObject object1){// need a different one for players and puck

    //}
}
