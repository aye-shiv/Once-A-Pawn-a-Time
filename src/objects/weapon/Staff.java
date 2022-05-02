package objects.weapon;

import main.util.animation.Animation;
import objects.GameObject;
import objects.entity.Entity;
import objects.entity.Player;
import main.GamePanel;

import main.util.ImageManager;
import main.util.SoundManager;

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
        screenX = entity.getScreenX() + screenXOffset;
        screenY = entity.getScreenY() + screenYOffset;
        if(isAnimating()){
            animation.draw(g2);
            return;
        }
        for(Magic magic: projectiles){
            magic.draw(g2);
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
        for(Magic magic: projectiles){
            magic.update();
        }

    }

    @Override
    public void attack() {
        projectiles.add(new Magic(gp, entity));
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
            this.width = 79;
            this.height = 15;
            this.hitDamage = 5;
            speedX = 20;

            this.screenXOffset = 0;
            this.screenYOffset = 0;

            screenX = entity.getScreenX() + screenXOffset;
            screenY = entity.getScreenY() + screenYOffset;

            this.image = ImageManager.loadBufferedImage("res/images/objects/knife1.png");
        }

        @Override
        public void draw(Graphics2D g2) {
            //screenX = entity.getScreenX() + screenXOffset;
            //screenY = entity.getScreenY() + screenYOffset;
            if(isAnimating()){
                animation.draw(g2);
                return;
            }
            g2.drawImage(image, screenX, screenY, width, height, null);
        }

        @Override
        public void update() {
            screenX += speedX;
        }

        @Override
        public void attack() {

        }
    }

}