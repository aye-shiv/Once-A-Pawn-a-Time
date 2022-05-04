package objects.weapon.ranged;

import main.GamePanel;
import objects.entity.Entity;
import objects.weapon.Weapon;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class RangedWeapon extends Weapon {

    public List<Projectile> projectiles = new ArrayList<>();
    protected int projectileScreenXOffset = 0;
    protected int projectileScreenYOffset = 0;
    public void setProjectileScreenXOffset(int x) { this.projectileScreenXOffset = x; }
    public void setProjectileScreenYOffset(int y) { this.projectileScreenYOffset = y; }

    public RangedWeapon(GamePanel gp, Entity entity) {
        super(gp, entity);
        this.collision = () -> { };
    }


    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

        for(Projectile projectile: new ArrayList<>(projectiles)){
            projectile.draw(g2);
        }

    }


    @Override
    public void update() {
        super.update();

        projectiles.removeIf(projectile -> projectile.isDestroyed());
        for(Projectile projectile: new ArrayList<>(projectiles)){
            projectile.update();
        }
    }


    @Override //Ranged weapons don't have a hitbox, their projectiles do
    public Rectangle getHitBox(){ //Projectile weapon doesn't have a hitbox
        return new Rectangle(screenX, screenY, 0, 0);
    }

}
