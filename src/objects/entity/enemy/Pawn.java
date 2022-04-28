package objects.entity.enemy;

import java.awt.Graphics2D;


import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.Utils;

public class Pawn extends Enemy {

	public Pawn(GamePanel gp) {
		super(gp);
		init();
	}

    public void init() {
        this.worldX = Utils.getRandom(13*gp.tileSize, (gp.maxScrollCol-7)*gp.tileSize);
        this.worldY = gp.tileSize*8;

        this.width = 48;
        this.height = 48*2;

        this.hitDamage = 2;
        this.speed = 3;

        this.image = ImageManager.loadBufferedImage("res/images/entity/W_Pawn.png");
        this.soundManager = SoundManager.getInstance();
    }


    public void update(){

    }

    public void draw(Graphics2D g2){
        screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX();
        screenY = gp.player.getScreenY();
        if(gp.player.getWorldX() > (gp.maxScrollCol-14)*gp.tileSize){
            screenX = worldX - ((gp.maxScrollCol-15)*gp.tileSize);
        }

        g2.drawImage(image, screenX, screenY, width, height, null);
    }
}