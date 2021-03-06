package objects;

import main.GamePanel;
import main.util.SoundManager;
import main.util.animation.Animation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public abstract class GameObject {

    public GameObject(GamePanel gp) {
        this.gp = gp;
	}

    protected String name = "GameObject";

    protected int screenX = 0, screenY = 0;
    protected int worldX = 0, worldY = 0;
    protected int speedX = 2, speedY = 2;

    protected int width = 0;
    protected int height = 0;

    protected Animation animation;

    protected SoundManager soundManager;
    protected GamePanel gp;
    protected BufferedImage image;

    protected Delay delayer = new Delay();

    /* =====Checks==== */
    protected boolean isDestroyed = false;
    public boolean isDestroyed(){ return isDestroyed; }


    /* =====Functions to Override==== */
    public void draw(Graphics2D g2){
        if(false) { //Show hitbox
            Rectangle rect = getHitBox();
            g2.setColor(Color.RED);
            g2.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
    }

    public void update(){
        if(isAnimating())
            checkColliding();
        if(collidingObject != null && !inCollisionDelayPhase)
            onCollision();
    }

    public void destroy(){
        isDestroyed = true;
    }

    /* ======Custom======= */

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
        if(worldX+speedX <= (gp.worldWidth - gp.tileSize*4) + width)
            worldX += speedX;
    }

    /* =====Animations==== */
    private boolean noAnimation() {
        return animation == null;
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

    /* =====Collision==== */
    protected Object collidingObject = null;
    protected Runnable collision = () -> {
        //Let each entity that uses a weapon override this method
        //This needs to be overridden
    };

    private void onCollision(){
        collision.run();
    }
    protected void checkColliding(){
        //Override to add collision
    }

    protected boolean inCollisionDelayPhase = false;
    protected Timer collisionDelay = new Timer(0, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            inCollisionDelayPhase = false;
        }
    });

    public void startCollisionDelay(int time){
        collisionDelay.stop();
        collisionDelay.setInitialDelay(time);
        collisionDelay.setDelay(time);
        inCollisionDelayPhase = true;
        collisionDelay.restart();
    }


    /* =====Getters==== */

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public int getScreenX() { return screenX; }
    public int getScreenY() { return screenY; }

    public int getWorldX() { return worldX; }
    public int getWorldY() { return worldY; }

    public int getSpeedX() { return speedX; }
    public int getSpeedY() { return speedY; }

    public Rectangle getHitBox(){
        if(width < 0) //Facing Left
            return new Rectangle(screenX-Math.abs(width), screenY, Math.abs(width), Math.abs(height));
        return new Rectangle(screenX, screenY, Math.abs(width), Math.abs(height));
    }

    public Animation getAnimation() { return animation; }
    public BufferedImage getImage() { return image; }
    public SoundManager getSoundManager() { return soundManager; }

    /* =====Setters==== */

    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }

    public void setScreenX(int x) { this.screenX = x; }
    public void setScreenY(int y) { this.screenY = y; }

    public void setWorldX(int x) { this.worldX = x; }
    public void setWorldY(int y) { this.worldY = y; }

    public void setSpeedX(int speed) { this.speedX = speed; }
    public void setSpeedY(int speed) { this.speedY = speed; }


}