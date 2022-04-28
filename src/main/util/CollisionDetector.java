package main.util;

import main.GamePanel;
import objects.GameObject;

public class CollisionDetector {

    GamePanel gp;

    public CollisionDetector(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(GameObject entity){
        int eLWX = entity.getWorldX() + entity.getHitBox().x;
        int eRWX = entity.getWorldX() + entity.getHitBox().x + entity.getHitBox().width;
        int eTWY = entity.getWorldY() + entity.getHitBox().y;
        int eBWY = entity.getWorldY() + entity.getHitBox().y + entity.getHitBox().height;

        int eLC = eLWX/gp.tileSize;
        int eRC = eRWX/gp.tileSize;
        int eTR = eTWY/gp.tileSize;
        int eBR = eBWY/gp.tileSize;

        int index1, index2;
        //switch(gp.)

    }
}
