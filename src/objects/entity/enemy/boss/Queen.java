package objects.entity.enemy.boss;

import java.awt.Graphics2D;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import objects.weapon.Cannon;

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

		setSpeedX(22);
		setSpeedY(7);

        this.image = ImageManager.loadBufferedImage("res/images/entity/W_Queen.png");
        this.soundManager = SoundManager.getInstance();

		setWeapon(new Cannon(gp, this));
		this.weapon.setWidth(100);
		this.weapon.setHeight(55);
		this.weapon.setScreenXOffset(((int)(getWidth() * 0.45)));
		this.weapon.setScreenYOffset(((int)(getHeight() * 0.55)));
    }

	@Override
	public void update() {
		super.update();
		this.weapon.update();
	}

	@Override
	public void draw(Graphics2D g2) {
		screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX();
		screenY = worldY;

		g2.drawImage(image, screenX, screenY, width, height, null);
		this.weapon.draw(g2);
	}

	@Override
	public int getJumpHeight(){ //Queen can jump higher
		return gp.worldFloorY - height - gp.tileSize*4;
	}

}