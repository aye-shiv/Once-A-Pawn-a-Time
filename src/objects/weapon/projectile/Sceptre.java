package objects.weapon.projectile;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.animation.Animation;
import objects.entity.Entity;
import objects.entity.Player;
import objects.entity.enemy.Enemy;
import objects.weapon.Weapon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Sceptre extends ProjectileWeapon {

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
        this.collision = defaultWeaponCollision;
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
    public void attack() {
        delayer.setDelayTime(700);
        if(!delayer.inDelayPhase()){
            delayer.start();
            Magic magic = new Magic(gp, entity, this);
            magic.setScreenXOffset(projectileScreenXOffset);
            magic.setScreenYOffset(projectileScreenYOffset);
            magic.startAnimation(true);
            projectiles.add(magic);
            resumeAnimation();
        }
    }


    @Override //Special Projectile weapon
    public Rectangle getHitBox(){
        if(width < 0) //Facing Left
            return new Rectangle(screenX-Math.abs(width), screenY, Math.abs(width), Math.abs(height));
        return new Rectangle(screenX, screenY, Math.abs(width), Math.abs(height));
    }


    /*
    ==========================================

    ==========================================
     */

    public static class Magic extends WeaponProjectile {

        public Magic(GamePanel gp, Entity entity, Weapon weapon) {
            super(gp, entity, weapon);
            init();
        }


        public void init() {
            this.name = "Spell";
            this.width = 45;
            this.height = 15;
            this.hitDamage = 20;
            speedX = 13;

            this.screenXOffset = weapon.getScreenXOffset() + ((int)(weapon.getWidth() * 0.75));
            this.screenYOffset = weapon.getScreenYOffset() + ((int)(weapon.getHeight() * 0.01));

            worldX = entity.getWorldX();
            worldY = entity.getWorldY();

            this.image = ImageManager.loadBufferedImage("res/images/objects/sceptre/projectile/projectile_1.png");
            setupAnimation();
        }

        public void setupAnimation() {
            animation = new Animation(gp, this);
            animation.addFrame(image, 100); //First frame should be default image
            animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/sceptre/projectile/projectile_1.png"), 100);
            animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/sceptre/projectile/projectile_2.png"), 100);
            animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/sceptre/projectile/projectile_3.png"), 100);
            animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/sceptre/projectile/projectile_4.png"), 100);
        }

    }
}