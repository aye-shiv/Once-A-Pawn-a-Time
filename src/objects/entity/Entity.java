package objects.entity;

import main.GamePanel;
import objects.GameObject;
import objects.weapon.Weapon;

import java.awt.*;

public abstract class Entity extends GameObject {

    public static int FACING_LEFT = 1;
    public static int FACING_RIGHT = 2;

    public Entity(GamePanel gp) {
		super(gp);
	}


    protected Weapon weapon;
    protected int maxHP = 0, hp = 0;
    protected int facing = FACING_RIGHT;
    protected int idleCounter = 0;
    protected int actionBufCounter = 0;


    /* =====Custom==== */
    public void takeHealth(int health){
        hp += health;
        if(hp > maxHP){
            hp = maxHP;
        }
    }

    public void takeDamage(int damage) {
        hp -= damage;
        if(hp <= 0){
            hp = 0;
            destroy();
        }

    }


    /* =====Getters==== */

    public int getMaxHP() { return maxHP; }
    public int getHP() { return hp; }

    public Weapon getWeapon() { return weapon; }
    public int getFacing() { return facing; }
    public int getABC(){ return actionBufCounter;}
    /* =====Setters==== */

    public void setWeapon(Weapon weapon) { this.weapon = weapon; }

    @Override
    public void moveLeft(){
        super.moveLeft();
        facing = Entity.FACING_LEFT;
    }

    @Override
    public void moveRight(){
        super.moveRight();
        facing = Entity.FACING_RIGHT;
    }

    @Override
    public void update(){
        super.update();
        if(isJumping && worldY >= getJumpHeight()){
            moveUp();
        } else { //Gravity
            isJumping = false;
            moveDown();
        }
    }

    public void drawHP(Graphics2D g2){

        double hpBar = ((double)gp.tileSize/maxHP) * hp;

        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(screenX-1, screenY-16, gp.tileSize+2, 12);

        g2.setColor(new Color(255,0,30));
        g2.fillRect(screenX, screenY -15, (int)hpBar, 10);

    }

    /* =====Jump Mechanics==== */
    protected boolean isJumping = false;
    public boolean isJumping(){ return isJumping; }
    public boolean canJump() { return worldY == gp.worldFloorY - height;}
    public void setJumping(boolean jumping){ this.isJumping = jumping; }
    public int getJumpHeight(){
        return gp.worldFloorY - height - gp.tileSize*2;
    }

}