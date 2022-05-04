package objects.entity;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import objects.entity.enemy.Pawn;
import objects.weapon.melee.Dagger;
import objects.weapon.melee.Spear;
import objects.weapon.melee.Sword;
import objects.weapon.projectile.Cannon;
import objects.weapon.projectile.Sceptre;
import objects.weapon.projectile.Staff;

import java.awt.*;
import java.util.ArrayList;

public class Player extends Entity {

	public Player(GamePanel gp) {
		super(gp);
        init();
	}

    public void init() {
        this.width = 45;
        this.height = 65;

        this.worldX = gp.tileSize*1;
        this.worldY = gp.worldFloorY - height;
        this.screenX = worldX;
        setSpeedX(5);
        setSpeedY(5);
        this.hp = 100;
        this.maxHP = hp;

        this.image = ImageManager.loadBufferedImage("res/images/entity/B_Pawn.png");
        this.soundManager = SoundManager.getInstance();

        setWeapon(new Sword(gp, this));
        drawHP = false;
    }

    @Override
    public void update(){
        super.update();

        screenY = worldY;
        if(gp.player.getWorldX() > (gp.maxScrollCol-14)*gp.tileSize){
            screenX = worldX - ((gp.maxScrollCol-15)*gp.tileSize);
        }

    }

    @Override
    public void setAction(){
        if (gp.getKeyHandler().upPressed){
            jump();
            //gp.boss.jump();
        }
        if (gp.getKeyHandler().downPressed){

        }

        if (gp.getKeyHandler().leftPressed){
            moveLeft();
            //gp.boss.moveRight();
        }
        if (gp.getKeyHandler().rightPressed){
            moveRight();
            //gp.boss.moveLeft();
        }

        if(gp.getKeyHandler().spacePressed){
            weapon.attack();

            /*
            gp.boss.weapon.attack();
            for(Pawn pawn: new ArrayList<>(gp.pawns)){
                pawn.weapon.attack();
            }
            */
        }
    }

}