package objects.weapon.ranged;

import main.util.animation.Animation;
import objects.entity.Entity;
import main.GamePanel;

import main.util.ImageManager;
import main.util.SoundManager;
import objects.weapon.Weapon;

import java.awt.*;

public class Cannon extends RangedWeapon {

	public Cannon(GamePanel gp, Entity entity) {
		super(gp, entity);
        init();
	}

    public void init(){

	    this.name = "Cannon";

        this.width = 70;
        this.height = 35;

        this.screenXOffset = ((int)(entity.getWidth() * 0.35));
        this.screenYOffset = ((int)(entity.getHeight() * 0.25));

        this.image = ImageManager.loadBufferedImage("res/images/objects/cannon/cannon_1.png");
        this.soundManager = SoundManager.getInstance();
        setupAnimation();
    }

    public void setupAnimation(){
        animation = new Animation(gp, this);
        animation.addFrame(image, 100); //First frame should be default image
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/cannon/cannon_1.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/cannon/cannon_2.png"), 70);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/cannon/cannon_3.png"), 100);
    }

    @Override
    public void attack() {
        delayer.setDelayTime(1000);
        if(!delayer.inDelayPhase()){
            delayer.start();
            playAttackSound();
            Ball ball = new Ball(gp, entity, this);
            ball.setScreenXOffset(projectileScreenXOffset);
            ball.setScreenYOffset(projectileScreenYOffset);
            ball.startAnimation(true);
            projectiles.add(ball);
            resumeAnimation();
        }
    }

    @Override
    public void playAttackSound() {
        soundManager.playClip("cannon_attack");
    }

    @Override
    public Rectangle getHitBox(){ //Projectile weapon doesn't have a hitbox
        return new Rectangle(screenX, screenY, 0, 0);
    }


    /*
    ==========================================

    ==========================================
     */


    public static class Ball extends Projectile {

        public Ball(GamePanel gp, Entity entity, Weapon weapon) {
            super(gp, entity, weapon);
            init();
        }

        public void init() {
            this.name = "CannonBall";
            this.width = 25;
            this.height = 25;
            this.hitDamage = 13;
            speedX = 13;

            this.screenXOffset = weapon.getScreenXOffset() + ((int)(weapon.getWidth() * 0.25));
            this.screenYOffset = weapon.getScreenYOffset() + ((int)(weapon.getHeight() * 0.25));

            worldX = entity.getWorldX();
            worldY = entity.getWorldY();

            this.image = ImageManager.loadBufferedImage("res/images/objects/cannon/projectile/cannon_ball.png");
            setupAnimation();
        }

        public void setupAnimation() {
            animation = new Animation(gp, this);
            animation.addFrame(image, 100); //First frame should be default image
            animation.addFrame(image, 100);
        }

    }

}