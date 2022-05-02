package main.util;

import com.sun.istack.internal.NotNull;
import main.GamePanel;
import objects.entity.Entity;
import objects.entity.Player;
import objects.weapon.Weapon;

public class CollisionDetector {

    GamePanel gp;

    public CollisionDetector(GamePanel gp){
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int eLWX = entity.getWorldX() + entity.getHitBox().x;
        int eRWX = entity.getWorldX() + entity.getHitBox().x + entity.getHitBox().width;
        int eTWY = entity.getWorldY() + entity.getHitBox().y;
        int eBWY = entity.getWorldY() + entity.getHitBox().y + entity.getHitBox().height;

        int eLC = eLWX/gp.tileSize;
        int eRC = eRWX/gp.tileSize;
        int eTR = eTWY/gp.tileSize;
        int eBR = eBWY/gp.tileSize;

        int index1, index2;
        switch(entity.getFacing()){
            case 1:
                break;

            case 2:
                eTR = (eTWY - gp.getPlayer().getSpeedX())/gp.tileSize;
                index1 = gp.getTileManager().tileNum[eLC][eTR];
                index2 = gp.getTileManager().tileNum[eRC][eTR];
                if(gp.getTileManager().tiles[index1].collision || gp.getTileManager().tiles[index2].collision){
                    entity.collision = true;
                }
                break;
        }

    }

    public boolean checkEntity(Entity entity1, Entity entity2){
        int eLWX1 = entity1.getWorldX() + entity1.getHitBox().x;
        int eRWX1 = entity1.getWorldX() + entity1.getHitBox().x + entity1.getHitBox().width;
        int eTWY1 = entity1.getWorldY() + entity1.getHitBox().y;
        int eBWY1 = entity1.getWorldY() + entity1.getHitBox().y + entity1.getHitBox().height;

        int eLWX2 = entity1.getWorldX() + entity1.getHitBox().x;
        int eRWX2 = entity1.getWorldX() + entity1.getHitBox().x + entity1.getHitBox().width;
        int eTWY2 = entity1.getWorldY() + entity1.getHitBox().y;
        int eBWY2 = entity1.getWorldY() + entity1.getHitBox().y + entity1.getHitBox().height;

        return false;
    }

    public boolean checkHit(Weapon weapon, Entity entity){
        int pLWX = weapon.getWorldX() + weapon.getHitBox().x;
        int pRWX = weapon.getWorldX() + weapon.getHitBox().x + weapon.getHitBox().width;
        int pTWY = weapon.getWorldY() + weapon.getHitBox().y;
        int pBWY = weapon.getWorldY() + weapon.getHitBox().y + weapon.getHitBox().height;

        int eLWX = entity.getWorldX() + entity.getHitBox().x;
        int eRWX = entity.getWorldX() + entity.getHitBox().x + entity.getHitBox().width;
        int eTWY = entity.getWorldY() + entity.getHitBox().y;
        int eBWY = entity.getWorldY() + entity.getHitBox().y + entity.getHitBox().height;

        return false;
    }
}
