package objects.entity.enemy.boss;

import main.GamePanel;
import objects.entity.enemy.Enemy;

public abstract class Boss extends Enemy {

	public Boss(GamePanel gp) {
		super(gp);
		init();
	}

    public void init() {
        this.worldX = (gp.maxScrollCol-3) * gp.tileSize;
        this.worldY = gp.tileSize*7;
    }

    @Override
    public int getJumpHeight(){ //Queen can jump higher
        return gp.worldFloorY - height - gp.tileSize*3;
    }


}