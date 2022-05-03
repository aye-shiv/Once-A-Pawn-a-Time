package objects.entity.enemy.boss;

import java.awt.Graphics2D;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import objects.weapon.Spear;

public class Knight extends Boss {

	public Knight(GamePanel gp) {
		super(gp);
        init();
	}

    public void init(){
        this.width = 90;
        this.height = 145;

        this.worldX = (gp.maxScrollCol-3) * gp.tileSize;
        this.worldY = gp.worldFloorY - height;

        setSpeedX(8);
        setSpeedY(7);
        this.hp = 200;
        this.maxHP = hp;

        this.image = ImageManager.loadBufferedImage("res/images/entity/W_Knight.png");
        this.soundManager = SoundManager.getInstance();

        setWeapon(new Spear(gp, this));
        this.weapon.setWidth(200);
        this.weapon.setHeight(55);
        this.weapon.setScreenXOffset(((int)(getWidth() * 0.45)));
        this.weapon.setScreenYOffset(((int)(getHeight() * 0.45)));
    }

    @Override
    public void update() {
        super.update();
        this.weapon.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX();
        screenY = worldY;

        g2.drawImage(image, screenX, screenY, width, height, null);
        weapon.draw(g2);
    }    

}