package objects.weapon;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.animation.Animation;
import objects.entity.Entity;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Staff extends Weapon {

	public Staff(GamePanel gp, Entity entity) {
		super(gp, entity);
        init();
	}

    public List<Magic> projectiles = new ArrayList<>();

    public void init(){

        this.width = 55;
        this.height = 55;

        this.hitDamage = 2;
        this.speed = 4;

        this.screenXOffset = ((int)(entity.getWidth() * 0.25));
        this.screenYOffset = ((int)(entity.getHeight() * 0.15));

        this.image = ImageManager.loadBufferedImage("res/images/objects/staff1.png");
        this.soundManager = SoundManager.getInstance();
        setupAnimation();
    }

    public void setupAnimation(){
        animation = new Animation(gp, this);
        animation.addFrame(image, 100); //First frame should be default image
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/staff_1.png"), 250);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/staff_2.png"), 250);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/staff_3.png"), 250);
    }

    @Override
    public void draw(Graphics2D g2) {

        if(isAnimating())
            animation.draw(g2);

        projectiles.removeIf(projectile -> projectile.isDestroyed());
        for(Magic magic: new ArrayList<>(projectiles)){
            magic.draw(g2);
        }

    }

    @Override
    public void update() {

        if(entity.getFacing() == Entity.FACING_LEFT){
            width = -Math.abs(width);
        } else if(entity.getFacing() == Entity.FACING_RIGHT){
            width = Math.abs(width);
        }

        screenX = entity.getScreenX() + screenXOffset;
        screenY = entity.getScreenY() + screenYOffset;

        if(isAnimating())
            animation.update();

        projectiles.removeIf(projectile -> projectile.isDestroyed());
        for(Magic magic: new ArrayList<>(projectiles)){
            magic.update();
        }

    }

    @Override
    public void attack() {
        delayer.setDelayTime(1000);
        if(!delayer.inDelayPhase()){
            delayer.start();
            Magic magic = new Magic(gp, entity);
            magic.startAnimation(true);
            projectiles.add(magic);
            resumeAnimation();
        }
    }


    /*
    ==========================================

    ==========================================
     */


    public static class Magic extends Weapon {

        public Magic(GamePanel gp, Entity entity) {
            super(gp, entity);
            init();
        }

        public void init(){
            this.width = 45;
            this.height = 15;
            this.hitDamage = 5;
            speedX = 10;

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

        @Override
        public void draw(Graphics2D g2) {

            if(isAnimating())
                animation.draw(g2);
        }

        @Override
        public void update() {
            worldX += speedX;
            if(worldX > gp.worldWidth)
                isDestroyed = true;

            screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX();
            screenY = worldY;
            if(isAnimating())
                animation.update();
        }

        @Override public void attack() {}

    }

}