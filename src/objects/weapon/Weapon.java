package objects.weapon;

import objects.GameObject;

import objects.entity.Entity;
import main.GamePanel;

import java.awt.*;

public abstract class Weapon extends GameObject {
    String name;

    public Weapon(GamePanel gp, Entity entity){
        super(gp);
        this.entity = entity;
    }


    Entity entity;
    protected int speed = 0;
    protected int hitDamage = 0;

    protected int screenXOffset = 0;
    protected int screenYOffset = 0;

    /* =====Functions to Override==== */

    public abstract void attack();

    
    /* =====Custom==== */


    /* =====Getters==== */

    public int getSpeed() { return speed; }
    public int getHitDamage() { return hitDamage; }

    public int getScreenXOffset() { return screenXOffset; }
    public int getScreenYOffset() { return screenYOffset; }

    /* =====Setters==== */

    public void setSpeed(int speed) { this.speed = speed; }
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

}