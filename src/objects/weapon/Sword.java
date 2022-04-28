package objects.weapon;

import objects.entity.Player;
import main.GamePanel;

import main.util.ImageManager;
import main.util.SoundManager;

public class Sword extends Weapon {

	public Sword(GamePanel gp, Player player) {
		super(gp, player);
        init();
	}

    public void init(){
        this.width = 4;
        this.height = 6;

        this.worldX = player.getWorldX();
        this.worldY = player.getWorldY();

        this.hitDamage = 2;
        this.speed = 4;

        this.image = ImageManager.loadBufferedImage("");
        this.soundManager = SoundManager.getInstance();
    }
    
}