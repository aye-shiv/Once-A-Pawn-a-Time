package objects.weapon.melee;

import main.GamePanel;
import objects.entity.Entity;
import objects.weapon.Weapon;

public abstract class MeleeWeapon extends Weapon {

    public MeleeWeapon(GamePanel gp, Entity entity) {
        super(gp, entity);
    }

}
