package objects.weapon.melee;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.animation.Animation;
import objects.entity.Entity;

public class Dagger extends MeleeWeapon {

    public Dagger(GamePanel gp, Entity entity) {
        super(gp, entity);
        init();
    }

    public void init(){
        this.name = "Dagger";

        this.width = 79;
        this.height = 15;

        this.hitDamage = 4;

        this.screenXOffset = ((int)(entity.getWidth() * 0.50));
        this.screenYOffset = ((int)(entity.getHeight() * 0.45));

        this.image = ImageManager.loadBufferedImage("res/images/objects/dagger/dagger_1.png");
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
    public void attack() {
        delayer.setDelayTime(750);
        if(!delayer.inDelayPhase()){
            delayer.start();
            playAttackSound();
            resumeAnimation();
        }
    }

    @Override
    public void playAttackSound() {
        soundManager.playClip("dagger_attack");
    }

}
