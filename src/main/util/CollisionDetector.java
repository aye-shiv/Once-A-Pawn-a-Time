package main.util;

import main.GamePanel;
import objects.GameObject;

public class CollisionDetector {

    GamePanel gp;

    public CollisionDetector(GamePanel gp){
        this.gp = gp;
    }

    public static boolean isColliding(GameObject object1, GameObject object2){
        return object1.getHitBox().intersects(object2.getHitBox());
    }

}
