package objects.entity;

import main.GamePanel;
import objects.GameObject;

public abstract class Entity extends GameObject {

	public Entity(GamePanel gp) {
		super(gp);
	}


    protected int speed = 0;
    protected int hitDamage = 0;
    protected int maxHP = 0, hp = 0;


    /* =====Custom==== */
    public void takeHealth(int health){
        hp += health;
    }

    public void takeDamage(int damage) {
        hp -= damage;
    }


    /* =====Getters==== */

    public int getSpeed() { return speed; }
    public int getHitDamage() { return hitDamage; }

    public int getmaxHP() { return maxHP; }
    public int getHP() { return hp; }

    /* =====Setters==== */


    public void setSpeed(int speed) { this.speed = speed; }
    public void setHitDamage(int damage) { this.hitDamage = damage; }
}