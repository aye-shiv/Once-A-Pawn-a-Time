package objects.entity.enemy;

import main.GamePanel;
import main.util.Utils;
import objects.HealthPack;
import objects.entity.Entity;

public abstract class Enemy extends Entity {

	protected EnemyAI AI;
	public int hostileRange = 350;

	public Enemy(GamePanel gp) {
		super(gp);
		AI = new EnemyAI(gp, this);
		facing = FACING_LEFT;
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

	@Override
	public void setAction(){
		AI.run();
	}

	@Override
	public void destroy(){
		super.destroy();
		if(EnemyAI.chance(0.20)){ //Chance to drop health pack
			HealthPack healthPack = new HealthPack(gp);
			healthPack.setWorldX(worldX);
			healthPack.setWorldY(worldY - 10);
			healthPack.getAnimation().startLoopLastFrame();
			gp.healthPacks.add(healthPack);
		}
	}

}