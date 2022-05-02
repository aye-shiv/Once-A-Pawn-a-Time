package objects.weapon;

import objects.entity.Entity;
import main.GamePanel;

import main.util.ImageManager;
import main.util.SoundManager;

import java.awt.*;

public class Spear extends Weapon {

	public Spear(GamePanel gp, Entity entity) {
		super(gp, entity);
        init();
	}

    public void init(){
        this.width = 65;
        this.height = 75;

        this.worldX = entity.getWorldX();
        this.worldY = entity.getWorldY();

        this.hitDamage = 7;
        this.speed = 4;

        this.screenXOffset = ((int)(entity.getWidth() * 0.50));
        this.screenYOffset = ((int)(entity.getHeight() * 0.25));

        this.image = ImageManager.loadBufferedImage("res/images/objects/spear.png");
        this.soundManager = SoundManager.getInstance();
    }

    @Override
    public void draw(Graphics2D g2) {
        screenX = entity.getScreenX() + screenXOffset;
        screenY = entity.getScreenY() + screenYOffset;
        if(isAnimating()){
            animation.draw(g2);
            return;
        }
        //g2.drawImage(image, screenX, screenY, width, height, null);
    }

    @Override
    public void update() {
        if(isAnimating()){
            animation.update();
            return;
        }
        if(entity.getFacing() == Entity.FACING_LEFT){
            width = -width;
        } else if(entity.getFacing() == Entity.FACING_RIGHT){
            width = Math.abs(width);
        }
    }

    @Override
    public void attack() {

    }
}