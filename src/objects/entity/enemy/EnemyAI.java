package objects.entity.enemy;

import main.GamePanel;
import objects.entity.Player;
import objects.weapon.melee.MeleeWeapon;
import objects.weapon.projectile.ProjectileWeapon;

public class EnemyAI {
    GamePanel gp;
    Enemy enemy;
    Player player;

    boolean inHostileRange = false; //Of Player

    public EnemyAI(GamePanel gp, Enemy enemy){
        this.enemy = enemy;
        this.gp = gp;
        this.player = gp.player;
    }

    public void run(){
        inHostileRange = ((player.getWorldX() >= enemy.getWorldX() - enemy.hostileRange && player.getWorldX() <= enemy.getWorldX() + enemy.hostileRange));
        if(!inHostileRange)
            return;

        if(enemy.getWeapon() instanceof MeleeWeapon){

        } else if(enemy.getWeapon() instanceof ProjectileWeapon){

        }

        if(player.getWorldX() < enemy.getWorldX() && enemy.getWorldX() > player.getWorldX() + player.getWidth())
            enemy.moveLeft();
        else if(player.getWorldX() > enemy.getWorldX() + enemy.getWidth() && enemy.getWorldX() < player.getWorldX() + player.getWidth())
            enemy.moveRight();
    }

}
