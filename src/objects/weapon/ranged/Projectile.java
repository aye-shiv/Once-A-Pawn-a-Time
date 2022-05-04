package objects.weapon.ranged;

import main.GamePanel;
import objects.entity.Entity;
import objects.entity.Player;
import objects.entity.enemy.Enemy;
import objects.weapon.Weapon;

public abstract class Projectile extends Weapon {

    Weapon weapon;
    int facing = -1;
    public Projectile(GamePanel gp, Entity entity, Weapon weapon) {
        super(gp, entity);
        this.weapon = weapon;
        this.facing = entity.getFacing();
        this.collision = defaultProjectileCollision;
    }


    @Override
    public void update() {
        super.update();
        if(facing == Entity.FACING_LEFT) {
            width = -Math.abs(width);
            worldX -= speedX;
        }
        else if(facing == Entity.FACING_RIGHT) {
            width = Math.abs(width);
            worldX += speedX;
        }

        if(worldX < 0 || worldX > gp.worldWidth)
            isDestroyed = true;

        screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX() + screenXOffset ;
        screenY = worldY + screenYOffset;

    }


    /*==========Collision==============*/


    Runnable defaultProjectileCollision = () -> {
        if(entity instanceof Player){
            if(collidingObject instanceof Enemy){
                if(animation == null)
                    startCollisionDelay(500);
                else startCollisionDelay((int) animation.getTotalDuration());

                //System.out.println("[" + name +"] Player hit a enemy");
                Enemy enemy = (Enemy) collidingObject;
                enemy.takeDamage(hitDamage);
                collidingObject = null;
                this.destroy();

            }
        } else if(entity instanceof Enemy){
            if(collidingObject instanceof Player){
                if(animation == null)
                    startCollisionDelay(500);
                else startCollisionDelay((int) animation.getTotalDuration());

                //System.out.println("[" + name +"] Enemy hit a player");
                Player player = (Player) collidingObject;
                player.takeDamage(hitDamage);
                collidingObject = null;
                this.destroy();
            }
        }
    };

}
