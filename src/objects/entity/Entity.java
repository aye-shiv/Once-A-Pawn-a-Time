package objects.entity;

import main.GamePanel;
import objects.GameObject;
import objects.weapon.Weapon;
import objects.weapon.ranged.RangedWeapon;

import java.awt.*;

public abstract class Entity extends GameObject {

    public static int FACING_LEFT = 1;
    public static int FACING_RIGHT = 2;
    protected int facing = FACING_RIGHT;

    public Entity(GamePanel gp) {
		super(gp);
	}

    protected Weapon weapon;
    protected int maxHP = 0, hp = 0;
    protected boolean invincible = false;


    /* =====Functions to Override==== */

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
        if(KBDistance <= 0)
            isTakingKB = false;
        if(isTakingKB){
            if(KBDirection == FACING_LEFT){
                moveRightKB();
            } else if(KBDirection == FACING_RIGHT){
                moveLeftKB();
            }
        }
        if(isJumping && worldY >= getJumpHeight()){
            moveUp();
        } else { //Gravity
            isJumping = false;
            moveDown();
        }
        setAction();

        //Entities set their own screenX/Y
        //screenX = worldX;
        //screenY = worldY;


        this.weapon.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

        g2.drawImage(image, screenX, screenY, width, height, null);
        this.weapon.draw(g2);
        if(drawHP)
            drawHP(g2);
    }

    public void destroy(){
        super.destroy();
        playDeathSound();
    }

    public void setAction(){}

    /* =====Custom==== */
    public void playTakeHealthSound() { soundManager.playClip("take_health"); }
    public void playTakeDamageSound() {}
    public void playDeathSound() { soundManager.playClip("take_damage"); }

    public void takeHealth(int health){
        playTakeHealthSound();
        hp += health;
        if(hp > maxHP){
            hp = maxHP;
        }
    }

    public void takeDamage(int damage) {
        if(invincible)
            return;
        playTakeDamageSound();
        takeKB();
        hp -= damage;
        if(hp <= 0){
            hp = 0;
            destroy();
        }
    }

    protected boolean drawHP = true;
    public boolean isDrawHP() { return drawHP; }
    public void setDrawHP(boolean drawHP) { this.drawHP = drawHP; }
    public void drawHP(Graphics2D g2){

        int barWidth = ((int)(getWidth() * 0.85));
        int barHeight = ((int)(getHeight() * 0.10));

        g2.setColor(new Color(35, 35, 35));
        g2.fillRect(screenX-1, screenY-16, barWidth+2, barHeight);

        double hpBar = ((double)barWidth/maxHP) * hp;
        g2.setColor(new Color(255,0,30));
        g2.fillRect(screenX, screenY-15, (int)hpBar, barHeight-2);

    }

    /* =====Jump Mechanics==== */

    protected boolean isJumping = false;
    public boolean isJumping(){ return isJumping; }
    private boolean canJump() { return worldY == gp.worldFloorY - height;}
    public void jump(){
        if(canJump())
            isJumping = true;
    }
    public int getJumpHeight(){ return gp.worldFloorY - height - gp.tileSize*2; }

    /* =====Knockback Mechanics==== */

    protected int KnockBackDistance = 70;
    private int KBDistance = 0;

    protected boolean isTakingKB = false;
    protected int KBDirection = FACING_LEFT;
    public boolean isTakingKB(){ return isTakingKB; }
    public void takeKB(){
        if(!isTakingKB) {
            if(facing == FACING_LEFT) {
                KBDirection = FACING_LEFT;
            } else if(facing == FACING_RIGHT) {
                KBDirection = FACING_RIGHT;
            }
            KBDistance = KnockBackDistance;
            isTakingKB = true;
        }
    }

    protected void moveLeftKB() {
        if (worldX - speedX >= width){
            worldX -= speedX;
            KBDistance -= speedX;
        }
        else
            KBDistance = 0;
    }
    protected void moveRightKB(){
        if(worldX+speedX <= (gp.worldWidth - gp.tileSize*4) + width) {
            worldX += speedX;
            KBDistance -= speedX;
        } else
            KBDistance = 0;
    }


    /* =====Getters==== */

    public int getMaxHP() { return maxHP; }
    public int getHP() { return hp; }
    public boolean isInvincible() { return invincible; }

    public Weapon getWeapon() { return weapon; }
    public int getFacing() { return facing; }

    /* =====Setters==== */

    public void setInvincible(boolean invincible) { this.invincible = invincible; }
    public void setWeapon(Weapon weapon) { this.weapon = weapon; }
    public void setProjectileWeaponXY(int projectileScreenXOffset, int projectileScreenYOffset){ //Only for projectile weapons
        if(weapon instanceof RangedWeapon){
            RangedWeapon projectileWeaponWeapon = (RangedWeapon) this.weapon;
            projectileWeaponWeapon.setProjectileScreenXOffset(projectileScreenXOffset);
            projectileWeaponWeapon.setProjectileScreenYOffset(projectileScreenYOffset);
        }
    }
}