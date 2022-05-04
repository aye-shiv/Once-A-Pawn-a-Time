package objects.entity;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import objects.weapon.melee.Sword;

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
        }
        if (gp.getKeyHandler().downPressed){

        }

        if (gp.getKeyHandler().leftPressed){
            moveLeft();
        }
        if (gp.getKeyHandler().rightPressed){
            moveRight();
        }

        if(gp.getKeyHandler().spacePressed){
            weapon.attack();
        }
    }

}