package objects.entity.enemy.boss;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.Utils;

import objects.weapon.Cannon;

import java.awt.*;

public class Rook extends Boss {
    private static final int ABC = 90;
	public Rook(GamePanel gp) {
		super(gp);
        init();
	}

    public void init(){
        this.width = 100;
        this.height = 160;

        this.worldX = (gp.maxScrollCol-3) * gp.tileSize;
        this.worldY = gp.worldFloorY - height;

        setSpeedX(12);
        setSpeedY(8);
        this.hp = 500;
        this.maxHP = hp;

        this.image = ImageManager.loadBufferedImage("res/images/entity/W_Rook.png");
        this.soundManager = SoundManager.getInstance();

        setWeapon(new Cannon(gp, this));
        this.weapon.setWidth(100);
        this.weapon.setHeight(55);
        this.weapon.setScreenXOffset(((int)(getWidth() * 0.45)));
        this.weapon.setScreenYOffset(((int)(getHeight() * 0.55)));
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
    public void update() {
        super.update();
        this.weapon.update();
        setAction();
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX();
        screenY = worldY;

        g2.drawImage(image, screenX, screenY, width, height, null);
        weapon.draw(g2);
        drawHP(g2);
    }



}