package objects.entity;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;

import java.awt.Graphics2D;

public class Player extends Entity {

	public Player(GamePanel gp) {
		super(gp);
        init();
	}

    public void init() {
        this.screenX = gp.tileSize * 1;
        this.screenY = gp.tileSize * 8;
        this.worldX = gp.tileSize*1;
        this.worldY = gp.tileSize*9;
        this.speed = 20;
        this.hp = 3;
        this.maxHP = hp;
        this.width = gp.tileSize;
        this.height = gp.tileSize*2;

        this.image = ImageManager.loadBufferedImage("res/images/entity/B_Pawn.png");
        this.soundManager = SoundManager.getInstance();
    }


    public void move(GamePanel gp){
        if (gp.getKeyHandler().upPressed == true){
            
        }
        if (gp.getKeyHandler().leftPressed == true){
            if(worldX>=48)worldX-=speed;
        }
        if (gp.getKeyHandler().downPressed == true){
            
        }
        if (gp.getKeyHandler().rightPressed == true){
            if(worldX<=(gp.worldWidth))worldX+=speed;
        }
    }


    public void update(GamePanel gp){
        //loadAnimation(gp.getKeyHandler().upPressed, this.gp.getKeyHandler().leftPressed, this.gp.getKeyHandler().downPressed, this.gp.getKeyHandler().rightPressed);
        this.move(gp);
        collision = false;
        //gp.getCollisionDet().checkTile(this);
    }



    public void draw(Graphics2D g2){

        int x = screenX;
        int y = screenY;
        if(gp.player.getWorldX() > (gp.maxScrollCol-14)*gp.tileSize){
            x = worldX - ((gp.maxScrollCol-15)*gp.tileSize);
        }
        g2.drawImage(image, x, y, width, height, null);
    }
    
}