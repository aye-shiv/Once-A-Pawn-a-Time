package objects;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.util.animation.Animation;
import main.util.SoundManager;
import objects.entity.Entity;

import javax.swing.*;

public abstract class GameObject {

    public GameObject(GamePanel gp) {
        this.gp = gp;
	}

    protected int screenX = 0, screenY = 0;
    protected int worldX = 0, worldY = 0;
    protected int speedX = 2, speedY = 2;

    protected int width = 0;
    protected int height = 0;

    protected Animation animation;
    protected Rectangle hitbox;
    protected boolean collision = false;

    protected SoundManager soundManager;
    protected GamePanel gp;
    protected BufferedImage image;

    protected Delay delayer = new Delay();

    /* =====Functions to Override==== */
    public abstract void draw(Graphics2D g2);
    public abstract void update();
    //public abstract void destroy();

    /* =====Animations==== */
    private boolean noAnimation() {
        if(animation == null) {
            //System.out.println("There is no Animation");
            return true;
        } else
            return false;
    }

    public boolean isAnimating() {
        if(noAnimation())
            return false;
        return animation.isAnimating();
    }

    public void startAnimation(){ startAnimation(false); }
    public void startAnimation(boolean loop){
        if(noAnimation())
            return;
        animation.startAnimation(loop);
    }

    public void resumeAnimation(){
        if(noAnimation())
            return;
        animation.resumeAnimation();
    }

    public void pauseAnimation(){
        if(noAnimation())
            return;
        animation.pauseAnimation();
    }

    /* =====Getters==== */

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public int getWorldX() { return worldX; }
    public int getWorldY() { return worldY; }

    public int getSpeedX() { return speedX; }
    public int getSpeedY() { return speedY; }

    public int getScreenX() { return screenX; }
    public int getScreenY() { return screenY; }

    public Rectangle getHitBox(){return hitbox;}

    public Animation getAnimation() { return animation; }
    public BufferedImage getImage() { return image; }
    public SoundManager getSoundManager() { return soundManager; }

    /* =====Setters==== */

    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }

    public void setWorldX(int x) { this.worldX = x; }
    public void setWorldY(int y) { this.worldY = y; }

    public void setSpeedX(int speed) { this.speedX = speed; }
    public void setSpeedY(int speed) { this.speedY = speed; }

    /* ================= */
    public void moveUp(){
        if(worldY-speedY >= 0)
            worldY -= speedY;
    }

    public void moveDown(){
        if(worldY+speedY <= gp.worldFloorY - height)
            worldY += speedY;
    }

    public void moveLeft(){
        if(worldX-speedX >= width)
            worldX -= speedX;
    }

    public void moveRight(){
        if(worldX+speedX <= (gp.worldWidth - gp.tileSize*2) + width)
            worldX += speedX;
    }


    /* =====Checks==== */
    protected boolean isDestroyed = false;
    public boolean isDestroyed(){ return isDestroyed; }

}