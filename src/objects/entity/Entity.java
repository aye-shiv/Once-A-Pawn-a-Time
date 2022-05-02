package objects.entity;

import main.GamePanel;
import objects.GameObject;
import objects.weapon.Weapon;

public abstract class Entity extends GameObject {

    public static int FACING_LEFT = 1;
    public static int FACING_RIGHT = 2;

    public Entity(GamePanel gp) {
		super(gp);
	}


    protected Weapon weapon;
    protected int maxHP = 0, hp = 0;
    protected int facing = FACING_RIGHT;


    /* =====Custom==== */
    public void takeHealth(int health){
        hp += health;
    }
    public void takeDamage(int damage) {
        hp -= damage;
    }


    /* =====Getters==== */

    public int getMaxHP() { return maxHP; }
    public int getHP() { return hp; }

    public Weapon getWeapon() { return weapon; }
    public int getFacing() { return facing; }
    /* =====Setters==== */

    public void setWeapon(Weapon weapon) { this.weapon = weapon; }


    public void moveLeft(){
        super.moveLeft();
        facing = Entity.FACING_LEFT;
    }

    public void moveRight(){
        super.moveRight();
        facing = Entity.FACING_RIGHT;
    }
}