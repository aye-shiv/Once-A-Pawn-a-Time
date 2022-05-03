package objects.weapon;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.animation.Animation;
import objects.entity.Entity;
import objects.entity.Player;
import objects.entity.enemy.Enemy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Sceptre extends Weapon {

	public Sceptre(GamePanel gp, Entity entity) {
		super(gp, entity);
        init();
	}

    public void init(){

        this.name = "Sceptre";

        this.width = 85;
        this.height = 65;

        this.hitDamage = 20;

        this.screenXOffset = ((int)(entity.getWidth() * 0.02));
        this.screenYOffset = ((int)(entity.getHeight() * 0.02));

        this.image = ImageManager.loadBufferedImage("res/images/objects/sceptre/sceptre_1.png");
        this.soundManager = SoundManager.getInstance();
        setupAnimation();
    }

    public void setupAnimation(){
        animation = new Animation(gp, this);
        animation.addFrame(image, 100); //First frame should be default image
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/sceptre/sceptre_1.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/sceptre/sceptre_2.png"), 50);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/sceptre/sceptre_3.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/sceptre/sceptre_4.png"), 50);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/sceptre/sceptre_5.png"), 100);
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