package objects.weapon;

import main.util.animation.Animation;
import objects.entity.Entity;
import main.GamePanel;

import main.util.ImageManager;
import main.util.SoundManager;

import java.awt.*;

public class Sword extends Weapon {

	public Sword(GamePanel gp, Entity entity) {
		super(gp, entity);
        init();
	}

    public void init(){
        this.screenXOffset = ((int)(entity.getWidth() * 0.50));
        this.screenYOffset = ((int)(entity.getHeight() * 0.001));

        this.width = 75;
        this.height = 65;

        this.hitDamage = 2;
        this.speed = 4;

        this.image = ImageManager.loadBufferedImage("res/images/objects/knife2.png");
        this.soundManager = SoundManager.getInstance();

        setupAnimation();
    }

    public void setupAnimation(){
        animation = new Animation(gp, this);
        animation.addFrame(image, 100); //First frame should be default image
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/sword/sword_1.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/sword/sword_2.png"), 70);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/sword/sword_3.png"), 100);
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