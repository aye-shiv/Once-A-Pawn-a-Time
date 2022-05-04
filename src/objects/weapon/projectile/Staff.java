package objects.weapon.projectile;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.animation.Animation;
import objects.entity.Entity;
import objects.weapon.Weapon;

public class Staff extends ProjectileWeapon {

	public Staff(GamePanel gp, Entity entity) {
		super(gp, entity);
        init();
	}

    public void init(){

        this.name = "Staff";

        this.width = 85;
        this.height = 65;

        this.screenXOffset = ((int)(entity.getWidth() * 0.02));
        this.screenYOffset = ((int)(entity.getHeight() * 0.02));

        this.image = ImageManager.loadBufferedImage("res/images/objects/staff/staff_1.png");
        this.soundManager = SoundManager.getInstance();
        setupAnimation();
    }

    public void setupAnimation(){
        animation = new Animation(gp, this);
        animation.addFrame(image, 100); //First frame should be default image
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/staff_1.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/staff_2.png"), 50);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/staff_3.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/staff_4.png"), 200);
    }

    @Override
    public void attack() {
        delayer.setDelayTime(1000);
        if(!delayer.inDelayPhase()){
            delayer.start();
            Spell spell = new Spell(gp, entity, this);
            spell.setScreenXOffset(projectileScreenXOffset);
            spell.setScreenYOffset(projectileScreenYOffset);
            spell.startAnimation(true);
            projectiles.add(spell);
            resumeAnimation();
        }
    }

    /*
    ==========================================

    ==========================================
     */


    public static class Spell extends WeaponProjectile {

        public Spell(GamePanel gp, Entity entity, Weapon weapon) {
            super(gp, entity, weapon);
            init();
        }

        public void init(){
            this.name = "Spell";
            this.width = 45;
            this.height = 15;
            this.hitDamage = 12;
            this.speedX = 10;

            this.screenXOffset = weapon.getScreenXOffset() + ((int)(weapon.getWidth() * 0.75));
            this.screenYOffset = weapon.getScreenYOffset() + ((int)(weapon.getHeight() * 0.01));


            worldX = entity.getWorldX();
            worldY = entity.getWorldY();

            this.image = ImageManager.loadBufferedImage("res/images/objects/staff/projectile/projectile_1.png");
            setupAnimation();
        }

        public void setupAnimation(){
            animation = new Animation(gp, this);
            animation.addFrame(image, 100); //First frame should be default image
            animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/projectile/projectile_1.png"), 100);
            animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/projectile/projectile_2.png"), 100);
            animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/projectile/projectile_3.png"), 100);
            animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/projectile/projectile_4.png"), 100);
        }

    }

}