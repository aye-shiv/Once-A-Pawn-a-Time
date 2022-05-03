package objects.weapon;

import main.util.CollisionDetector;
import objects.GameObject;

import objects.entity.Entity;
import main.GamePanel;
import objects.entity.Player;
import objects.entity.enemy.Enemy;
import objects.entity.enemy.Pawn;

import java.awt.*;
import java.util.ArrayList;

public abstract class Weapon extends GameObject {
    String name;

    public Weapon(GamePanel gp, Entity entity){
        super(gp);
        this.entity = entity;
        this.collision = weaponCollision;
    }


    Entity entity;
    protected int hitDamage = 0;

    protected int screenXOffset = 0;
    protected int screenYOffset = 0;

    /* =====Functions to Override==== */

    
    /* =====Custom==== */
    public abstract void attack();

    /* =====Getters==== */

    public int getHitDamage() { return hitDamage; }

    public int getScreenXOffset() { return screenXOffset; }
    public int getScreenYOffset() { return screenYOffset; }

    /* =====Setters==== */

    public void setHitDamage(int damage) { this.hitDamage = damage; }

    public void setScreenXOffset(int screenXOffset) {
        this.screenXOffset = screenXOffset;
    }
    public void setScreenYOffset(int screenYOffset) {
        this.screenYOffset = screenYOffset;
    }

    public String toString() { return name; }
    //DAMAGE
    //Staff: 2
    //Spear: 4
    //Sword: 8
    //Cannon: 12

    //SPEED
    //Staff: 4
    //Spear: 8
    //Sword: 12
    //Cannon: 17

    @Override
    protected void checkColliding(){
        if(entity instanceof Player){
            if(CollisionDetector.isColliding(this, gp.boss)){
                collidingObject = gp.boss;
            } else {
                for(Pawn pawn: new ArrayList<>(gp.getPawns())){
                    if(CollisionDetector.isColliding(this, pawn)){
                        collidingObject = pawn;
                        break;
                    }
                }
            }
        } else if(entity instanceof Enemy){
            if(CollisionDetector.isColliding(this, gp.player)){
                collidingObject = gp.player;
            }
        }
    }

    Runnable weaponCollision = () -> {
        if(entity instanceof Player){
            if(collidingObject instanceof Enemy){
                System.out.println("[" + name +"] Player hit a enemy");
                Enemy enemy = (Enemy) collidingObject;
                enemy.takeDamage(hitDamage);
                collidingObject = null;

                if(animation == null)
                    startCollisionDelay(500);
                else startCollisionDelay((int) animation.getTotalDuration());
            }
        } else if(entity instanceof Enemy){
            if(collidingObject instanceof Player){
                System.out.println("[" + name +"] Enemy hit a player");
                Player player = (Player) collidingObject;
                player.takeDamage(hitDamage);
                collidingObject = null;

                if(animation == null)
                    startCollisionDelay(500);
                else startCollisionDelay((int) animation.getTotalDuration());

            }
        }

    };

}