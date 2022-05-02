package objects.entity.enemy;

import java.awt.Graphics2D;


import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.Utils;
import objects.weapon.Dagger;

public class Pawn extends Enemy {

	public Pawn(GamePanel gp) {
		super(gp);
		init();
	}

    public void init() {
        this.width = 45;
        this.height = 65;

        this.worldX = Utils.getRandom(13*gp.tileSize, (gp.maxScrollCol-7)*gp.tileSize);
        this.worldY = gp.worldFloorY - height;

        setSpeedX(5);
        setSpeedY(5);

        this.image = ImageManager.loadBufferedImage("res/images/entity/W_Pawn.png");
        this.soundManager = SoundManager.getInstance();

        setWeapon(new Dagger(gp, this));
    }


    public void update(){
        super.update();
        this.weapon.update();
    }

    public void draw(Graphics2D g2){
        screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX();
        screenY = worldY;
        if(gp.player.getWorldX() > (gp.maxScrollCol-14)*gp.tileSize){
            screenX = worldX - ((gp.maxScrollCol-15)*gp.tileSize);
        }

        g2.drawImage(image, screenX, screenY, width, height, null);
        this.weapon.draw(g2);
    }
}