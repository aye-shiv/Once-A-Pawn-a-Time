package objects.entity.enemy.boss;

import java.awt.Graphics2D;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import objects.weapon.Staff;
import objects.weapon.Sword;

public class Bishop extends Boss {

	public Bishop(GamePanel gp) {
		super(gp);
        init();
	}

    public void init(){
        this.width = 120;
        this.height = 200;

        this.worldX = (gp.maxScrollCol-3) * gp.tileSize;
        this.worldY = gp.worldFloorY - height;

        setSpeedX(14);

        this.image = ImageManager.loadBufferedImage("res/images/entity/W_Bishop.png");
        this.soundManager = SoundManager.getInstance();

        setWeapon(new Staff(gp, this));
        this.weapon.setWidth(100);
        this.weapon.setWidth(100);
        this.weapon.setScreenXOffset(((int)(getWidth() * 0.51)));
        this.weapon.setScreenYOffset(((int)(getHeight() * 0.35)));
    }

	@Override
	public void update(){
        weapon.update();
    }

    @Override
    public void draw(Graphics2D g2){
        screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX();
        screenY = worldY;
        /*if(gp.player.getWorldX() > (gp.maxScrollCol-14)*gp.tileSize){
            screenX = worldX - ((gp.maxScrollCol-15)*gp.tileSize);
        }*/

        g2.drawImage(image, screenX, screenY, width, height, null);
        weapon.draw(g2);
    }

}