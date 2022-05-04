package objects.weapon.melee;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.animation.Animation;
import objects.entity.Entity;

public class Sword extends MeleeWeapon {

	public Sword(GamePanel gp, Entity entity) {
		super(gp, entity);
        init();
	}

    public void init(){
        this.name = "Sword";

        this.width = 75;
        this.height = 65;

        this.hitDamage = 6;

        this.screenXOffset = ((int)(entity.getWidth() * 0.50));
        this.screenYOffset = ((int)(entity.getHeight() * 0.001));

        this.image = ImageManager.loadBufferedImage("res/images/objects/sword/sword_1.png");
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
    public void attack() {
        delayer.setDelayTime(800);
        if(!delayer.inDelayPhase()){
            delayer.start();
            playAttackSound();
            resumeAnimation();
        }
    }

    @Override
    public void playAttackSound() {
        soundManager.playClip("sword_attack");
    }

}