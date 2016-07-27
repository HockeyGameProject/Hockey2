/**
 * Created by Mesa on 7/15/2016.
 */
public class Collision {

    public static boolean objectsCollide(MovingObject object1, MovingObject object2){
        int distance = object1.radius + object2.radius;
        if(Math.sqrt(Math.pow((object2.location.x - object1.location.x), 2)
                + Math.pow((object2.location.y - object1.location.y), 2))
                == distance){
            return true;
        }
        return false;
    }

    //public boolean edgeOfRinkCollision(MovingObject object1){// need a different one for players and puck

    //}
}
