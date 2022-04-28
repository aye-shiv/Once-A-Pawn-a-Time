package objects.entity.enemy.boss;

import main.GamePanel;
import objects.entity.enemy.Enemy;
import java.awt.Graphics2D;

public abstract class Boss extends Enemy {

	public Boss(GamePanel gp) {
		super(gp);
		init();
	}

    public void init() {
        this.worldX = (gp.maxScrollCol-3) * gp.tileSize;
        this.worldY = gp.tileSize*4;
    }

    public abstract void update();
    public abstract void draw(Graphics2D g2);


}