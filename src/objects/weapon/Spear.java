package objects.weapon;

import main.util.animation.Animation;
import objects.entity.Entity;
import main.GamePanel;

import main.util.ImageManager;
import main.util.SoundManager;
import objects.entity.Player;
import objects.entity.enemy.Enemy;

import java.awt.*;

public class Spear extends Weapon {

	public Spear(GamePanel gp, Entity entity) {
		super(gp, entity);
        init();
	}

    public void init(){

        this.name = "Spear";

        this.width = 155;
        this.height = 35;

        this.worldX = entity.getWorldX();
        this.worldY = entity.getWorldY();

        this.hitDamage = 9;

        this.screenXOffset = ((int)(entity.getWidth() * 0.50));
        this.screenYOffset = ((int)(entity.getHeight() * 0.30));

        this.image = ImageManager.loadBufferedImage("res/images/objects/spear/spear_1.png");
        this.soundManager = SoundManager.getInstance();

        setupAnimation();
    }

    public void setupAnimation(){
        animation = new Animation(gp, this);
        animation.addFrame(image, 100); //First frame should be default image
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/spear/spear_1.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/spear/spear_2.png"), 70);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/spear/spear_3.png"), 100);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        if(isAnimating())
            animation.draw(g2);

    }

    @Override
    public void update() {
        super.update();
        if(entity.getFacing() == Entity.FACING_LEFT){
            width = -Math.abs(width);
        } else if(entity.getFacing() == Entity.FACING_RIGHT){
            width = Math.abs(width);
        }

        screenX = entity.getScreenX() + screenXOffset;
        screenY = entity.getScreenY() + screenYOffset;

        if(isAnimating())
            animation.update();

    }

    @Override
    public void attack() {
        delayer.setDelayTime(700);
        if(!delayer.inDelayPhase()){
            delayer.start();
            resumeAnimation();
        }
    }


}