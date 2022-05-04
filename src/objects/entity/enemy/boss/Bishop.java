package objects.entity.enemy.boss;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import objects.weapon.ranged.Staff;

public class Bishop extends Boss {

	public Bishop(GamePanel gp) {
		super(gp);
        init();
	}

    public void init(){
        this.width = 90;
        this.height = 145;

        this.worldX = (gp.maxScrollCol-3) * gp.tileSize;
        this.worldY = gp.worldFloorY - height;

        setSpeedX(8);
        setSpeedY(6);
        this.hp = 120;
        this.maxHP = hp;

        this.image = ImageManager.loadBufferedImage("res/images/entity/W_Bishop.png");
        this.soundManager = SoundManager.getInstance();

        setWeapon(new Staff(gp, this));
        this.weapon.setWidth(105);
        this.weapon.setHeight(85);
        this.weapon.setScreenXOffset(((int)(getWidth() * 0.55)));
        this.weapon.setScreenYOffset(((int)(getHeight() * 0.15)));
        setProjectileWeaponXY(
                ((int)(getWidth() * 0.85)),
                ((int)(getHeight() * 0.55))
        );
    }

}