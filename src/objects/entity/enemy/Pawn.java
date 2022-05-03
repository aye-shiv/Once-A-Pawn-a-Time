package objects.entity.enemy;

import java.awt.*;


import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.Utils;

import objects.weapon.Dagger;

public class Pawn extends Enemy {
    public static int ABC = 90;
	public Pawn(GamePanel gp) {
		super(gp);
		init();
	}

    public void init() {
        this.width = 45;
        this.height = 65;

        this.worldX = Utils.getRandom(13*gp.tileSize, (gp.maxScrollCol-7)*gp.tileSize);
        this.worldY = gp.worldFloorY - height;

        setSpeedX(6);
        setSpeedY(6);
        this.hp = 70;
        this.maxHP = hp;


        this.image = ImageManager.loadBufferedImage("res/images/entity/W_Pawn.png");
        this.soundManager = SoundManager.getInstance();

        setWeapon(new Dagger(gp, this));
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

        if ((worldX - gp.getPlayer().getWorldX()) < 140 || (gp.getPlayer().getWorldX() - Math.abs(worldX)) > 140) {
            i = 51;
            actionBufCounter = temp;
        }
        if(actionBufCounter==ABC) {

            if (i < 25) {
                facing = FACING_LEFT;
                moveLeft();
            } else if (i > 25 && i < 50) {
                facing = FACING_RIGHT;
                moveRight();
            } else if (i > 50 && i < 75) {
                weapon.attack();
            } else if (i > 75 && i < 100) {
            }

            actionBufCounter = 0;
        }
    }

    public void update(){
        super.update();
        this.weapon.update();
        setAction();
    }

    public void draw(Graphics2D g2){
        super.draw(g2);
        screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX();
        screenY = worldY;
        if(gp.player.getWorldX() > (gp.maxScrollCol-14)*gp.tileSize){
            screenX = worldX - ((gp.maxScrollCol-15)*gp.tileSize);
        }
        g2.drawImage(image, screenX, screenY, width, height, null);
        this.weapon.draw(g2);
        drawHP(g2);
    }
}