package objects.entity.enemy.boss;

import java.awt.Graphics2D;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;

public class Bishop extends Boss {

	public Bishop(GamePanel gp) {
		super(gp);  
        this.worldX = (gp.maxScrollCol-3) * gp.tileSize;
        this.worldY = gp.tileSize*4;
        init();
	}

    public void init(){
        this.width = 48;
        this.height = 48*2;

        this.hitDamage = 12;
        this.speed = 14;

        this.image = ImageManager.loadBufferedImage("res/images/entity/W_Bishop.png");
        this.soundManager = SoundManager.getInstance();
    }

	@Override
	public void update(){

    }

    @Override
    public void draw(Graphics2D g2){
        screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX();
        screenY = worldY;
        if(gp.player.getWorldX() > (gp.maxScrollCol-14)*gp.tileSize){
            screenX = worldX - ((gp.maxScrollCol-15)*gp.tileSize);
        }

        g2.drawImage(image, screenX, screenY, width*3, height*3, null);
    }

}