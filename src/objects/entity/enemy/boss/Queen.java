package objects.entity.enemy.boss;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import objects.weapon.ranged.Sceptre;

public class Queen extends Boss {

	public Queen(GamePanel gp) {
		super(gp);
        init();
	}

    public void init(){
		this.width = 100;
		this.height = 180;

		this.worldX = (gp.maxScrollCol-3) * gp.tileSize;
		this.worldY = gp.worldFloorY - height;

		setSpeedX(9);
		setSpeedY(10);
		this.hp = 400;
		this.maxHP = hp;

        this.image = ImageManager.loadBufferedImage("res/images/entity/W_Queen.png");
        this.soundManager = SoundManager.getInstance();

		setWeapon(new Sceptre(gp, this));
		this.weapon.setWidth(195);
		this.weapon.setHeight(100);
		this.weapon.setScreenXOffset(((int)(getWidth() * 0.45)));
		this.weapon.setScreenYOffset(((int)(getHeight() * 0.25)));
		setProjectileWeaponXY(
				((int)(getWidth() * 0.85)),
				((int)(getHeight() * 0.65))
		);
    }

	@Override
	public int getJumpHeight(){ //Queen can jump higher
		return gp.worldFloorY - height - gp.tileSize*4;
	}

}