package objects.entity.enemy;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.Utils;
import objects.weapon.melee.Dagger;

public class Pawn extends Enemy {

	public Pawn(GamePanel gp) {
		super(gp);
		init();
	}

    public void init() {
        this.width = 45;
        this.height = 65;

        this.worldX = Utils.getRandom(13*gp.tileSize, (gp.maxScrollCol-7)*gp.tileSize);
        this.worldY = gp.worldFloorY - height;

        setSpeedX(5);
        setSpeedY(5);
        this.hp = 25;
        this.maxHP = hp;

        this.image = ImageManager.loadBufferedImage("res/images/entity/W_Pawn.png");
        this.soundManager = SoundManager.getInstance();

        setWeapon(new Dagger(gp, this));
    }

}