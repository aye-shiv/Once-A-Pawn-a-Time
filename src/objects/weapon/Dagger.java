package objects.weapon;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.animation.Animation;
import objects.entity.Entity;
import objects.entity.Player;

import java.awt.*;

public class Dagger extends Weapon {
    public Dagger(GamePanel gp, Entity entity) {
        super(gp, entity);
        init();
    }

    public void init(){
        this.width = 79;
        this.height = 15;

        this.hitDamage = 5;
        this.speed = 4;

        this.screenXOffset = ((int)(entity.getWidth() * 0.50));
        this.screenYOffset = ((int)(entity.getHeight() * 0.45));

        this.image = ImageManager.loadBufferedImage("res/images/objects/knife1.png");
        this.soundManager = SoundManager.getInstance();
        setupAnimation();
    }

    public void setupAnimation(){
        animation = new Animation(gp, this);
        animation.addFrame(image, 100); //First frame should be default image
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/dagger/dagger_1.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/dagger/dagger_2.png"), 70);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/dagger/dagger_3.png"), 100);
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