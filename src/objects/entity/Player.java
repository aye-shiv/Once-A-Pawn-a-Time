package objects.entity;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import objects.entity.enemy.Pawn;
import objects.weapon.*;

import java.awt.*;

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
        setSpeedX(6);
        setSpeedY(6);
        this.hp = 100;
        this.maxHP = hp;

        this.image = ImageManager.loadBufferedImage("res/images/entity/B_Pawn.png");
        this.soundManager = SoundManager.getInstance();

        //setWeapon(new Sword(gp, this));
        //setWeapon(new Staff(gp, this));
        //setWeapon(new Dagger(gp, this));
        setWeapon(new Spear(gp, this));
        //setWeapon(new Cannon(gp, this));
    }

    @Override
    public void update(){
        super.update();
        this.move();
        collision = false;
        this.weapon.update();
    }


    @Override
    public void draw(Graphics2D g2){

        screenY = worldY;
        if(gp.player.getWorldX() > (gp.maxScrollCol-14)*gp.tileSize){
            screenX = worldX - ((gp.maxScrollCol-15)*gp.tileSize);
        }
        g2.drawImage(image, screenX, screenY, width, height, null);
        this.weapon.draw(g2);
    }

    public void attack(){
        this.weapon.attack();
    }

    public void move(){
        if (gp.getKeyHandler().upPressed){
            if(canJump()){ //On the ground
                isJumping = true;
                gp.boss.setJumping(true);
            }
        }
        if (gp.getKeyHandler().downPressed){
            isJumping = false;
            gp.boss.setJumping(false);
        }

        if (gp.getKeyHandler().leftPressed){
            moveLeft();
            gp.boss.moveRight();
        }
        if (gp.getKeyHandler().rightPressed){
            moveRight();
            gp.boss.moveLeft();
        }

        if(gp.getKeyHandler().spacePressed){
            attack();

            gp.boss.weapon.attack();
            for(Pawn pawn: gp.pawns){
                pawn.weapon.attack();
            }
        }
    }
    
}