package objects.weapon;

import objects.GameObject;

import objects.entity.Player;
import main.GamePanel;

public abstract class Weapon extends GameObject {

    public Weapon(GamePanel gp, Player player){
        super(gp);
        this.player = player;
    }


    protected Player player;

    protected int speed = 0;
    protected int hitDamage = 0;

    
    /* =====Custom==== */

    

    /* =====Getters==== */

    public int getSpeed() { return speed; }
    public int getHitDamage() { return hitDamage; }

    /* =====Setters==== */


    public void setSpeed(int speed) { this.speed = speed; }
    public void setHitDamage(int damage) { this.hitDamage = damage; }


    
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