package objects.entity.enemy.boss;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.Utils;


import objects.weapon.Staff;

import java.awt.*;

public class Bishop extends Boss {
    public static int ABC = 90;
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
        setSpeedY(7);
        this.hp = 250;
        this.maxHP = hp;

        this.image = ImageManager.loadBufferedImage("res/images/entity/W_Bishop.png");
        this.soundManager = SoundManager.getInstance();

        setWeapon(new Staff(gp, this));
        this.weapon.setWidth(105);
        this.weapon.setHeight(85);
        this.weapon.setScreenXOffset(((int)(getWidth() * 0.55)));
        this.weapon.setScreenYOffset(((int)(getHeight() * 0.15)));
    }

    public void setAction(){
        int i = Utils.getRandom(1,50);

        actionBufCounter++;
        int temp = actionBufCounter;
        if (gp.getPlayer().getWorldX() < worldX && (worldX - gp.getPlayer().getWorldX()) < 500) {
            i = 1;
            actionBufCounter = ABC;
        }

        if (gp.getPlayer().getWorldX() > worldX && (gp.getPlayer().getWorldX() - Math.abs(worldX)) > 40) {
            i = 26;
            actionBufCounter = ABC;
        }

        if ((worldX - gp.getPlayer().getWorldX()) < 600 || (gp.getPlayer().getWorldX() - Math.abs(worldX)) > 600) {
            i = 51;
            actionBufCounter = temp;
        }
        if(actionBufCounter==ABC) {

            if (i < 25) {
                moveLeft();
            } else if (i > 25 && i < 50) {
                moveRight();
                facing = FACING_LEFT;
            } else if (i > 50 && i < 75) {
                weapon.attack();
            }
            //else if (i > 75 && i < 100) {}

            actionBufCounter = 0;
        }
    }

	@Override
	public void update(){
        super.update();
        weapon.update();
        setAction();
    }

    @Override
    public void draw(Graphics2D g2){
        super.draw(g2);
        screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX();
        screenY = worldY;

        if(gp.player.getWorldX() > (gp.maxScrollCol-14)*gp.tileSize){
            screenX = worldX - ((gp.maxScrollCol-15)*gp.tileSize);
        }

        g2.drawImage(image, screenX, screenY, width, height, null);
        weapon.draw(g2);
        drawHP(g2);
    }

}