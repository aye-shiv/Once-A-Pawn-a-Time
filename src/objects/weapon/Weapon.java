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

    public Weapon(GamePanel gp, Entity entity){
        super(gp);
        this.entity = entity;
        this.collision = defaultWeaponCollision;
    }


    protected Entity entity;
    protected int hitDamage = 0;

    protected int screenXOffset = 0;
    protected int screenYOffset = 0;
    
    /* =====Custom==== */


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

    /* =====Functions to Override==== */

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

        if(isAnimating())
            animation.draw(g2);
    }

    @Override
    public void update() {
        super.update();
        if(entity.getFacing() == Entity.FACING_LEFT){
            width = -Math.abs(width);
        } else if(entity.getFacing() == Entity.FACING_RIGHT){
            width = Math.abs(width);
        }

        screenX = entity.getScreenX() + screenXOffset;
        screenY = entity.getScreenY() + screenYOffset;

        if(isAnimating())
            animation.update();
    }

    public void attack(){

    }

    /* =========================== */

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


    protected Runnable defaultWeaponCollision = () -> {
        if(entity instanceof Player){
            if(collidingObject instanceof Enemy){
                if(animation == null)
                    startCollisionDelay(500);
                else startCollisionDelay((int) animation.getTotalDuration());

                //System.out.println("[" + name +"] Player hit a enemy");
                Enemy enemy = (Enemy) collidingObject;
                enemy.takeDamage(hitDamage);
                collidingObject = null;
            }
        } else if(entity instanceof Enemy){
            if(collidingObject instanceof Player){
                if(animation == null)
                    startCollisionDelay(1000);
                else startCollisionDelay((int) animation.getTotalDuration() + 1000);

                //System.out.println("[" + name +"] Enemy hit a player");
                Player player = (Player) collidingObject;
                player.takeDamage(hitDamage);
                collidingObject = null;
            }
        }

    };

}