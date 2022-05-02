package objects.weapon;

import objects.entity.Entity;
import objects.entity.Player;
import main.GamePanel;

import main.util.ImageManager;
import main.util.SoundManager;

import java.awt.*;

public class Cannon extends Weapon {

	public Cannon(GamePanel gp, Entity entity) {
		super(gp, entity);
        init();
	}

    public void init(){
        this.width = 4;
        this.height = 6;

        this.worldX = entity.getWorldX();
        this.worldY = entity.getWorldY();

        this.hitDamage = 15;
        this.speed = 7;

        this.image = ImageManager.loadBufferedImage("");
        this.soundManager = SoundManager.getInstance();
    }

    @Override
    public void draw(Graphics2D g2) {

    }

    @Override
    public void update() {

    }

    @Override
    public void attack() {

    }

}