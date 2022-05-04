package objects.entity.enemy;

import main.GamePanel;
import main.util.Utils;
import objects.entity.Entity;

public abstract class Enemy extends Entity {

	protected EnemyAI AI;
	public Enemy(GamePanel gp) {
		super(gp);
		AI = new EnemyAI(gp, this);
	}

	@Override
	public void update() {
		super.update();
		screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX();
		screenY = worldY;
		if(gp.player.getWorldX() > (gp.maxScrollCol-14)*gp.tileSize){
			screenX = worldX - ((gp.maxScrollCol-15)*gp.tileSize);
		}
	}

	public int hostileRange = 300;

	@Override
	public void setAction(){

		AI.run();
		/*
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
				moveLeft();
			} else if (i > 25 && i < 50) {
				moveRight();
				facing = FACING_LEFT;
			} else if (i > 50 && i < 75) {
				weapon.attack();
			}
			//else if (i > 75 && i < 100) {}

			actionBufCounter = 0;
		}
		*/
	}

}