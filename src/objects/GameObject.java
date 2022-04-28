package objects;

import java.awt.image.BufferedImage;
import java.awt.Rectangle;

import main.GamePanel;
import main.util.SoundManager;

public abstract class GameObject {

    public GameObject(GamePanel gp) {
        this.gp = gp;
	}

    protected int screenX = 0, screenY = 0;
    protected int worldX = 0, worldY = 0;

    protected int width = 0;
    protected int height = 0;

    protected Rectangle hitbox;
    protected boolean collision = false;

    protected SoundManager soundManager;
    protected GamePanel gp;
    protected BufferedImage image;



    /* =====Getters==== */

    public int getWidth() { return width; }
    public int getHeight() { return height; }

    public int getWorldX() { return worldX; }
    public int getWorldY() { return worldY; }

    public int getScreenX() { return screenX; }
    public int getScreenY() { return screenY; }

    public Rectangle getHitBox(){return hitbox;}

    public BufferedImage getImage() { return image; }
    public SoundManager getSoundManager() { return soundManager; }

    /* =====Setters==== */

    public void setWidth(int width) { this.width = width; }
    public void setHeight(int height) { this.height = height; }

    public void setWorldX(int x) { this.worldX = x; }
    public void setWorldY(int y) { this.worldY = y; }

}