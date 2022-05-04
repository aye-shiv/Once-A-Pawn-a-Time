package objects.entity.enemy;

import main.GamePanel;
import objects.entity.Player;
import objects.entity.enemy.boss.Boss;

import java.util.Random;

public class EnemyAI {

    GamePanel gp;
    Enemy enemy;
    Player player;

    boolean inHostileRange = false; //Of Player

    public EnemyAI(GamePanel gp, Enemy enemy){
        /*
        =======================================
        Basic AI
        =======================================
         */
        this.enemy = enemy;
        this.gp = gp;
        this.player = gp.player;
    }

    public void run() {
        if(!enemy.isTakingKB()){
            if (enemy instanceof Pawn) {
                inHostileRange = ((player.getWorldX() >= enemy.getWorldX() - enemy.hostileRange && player.getWorldX() <= enemy.getWorldX() + enemy.hostileRange));
                if (!inHostileRange)
                    return;
                if (chance(0.005)) {
                    enemy.jump();
                    return;
                }
                if (player.getWorldX() < enemy.getWorldX() && enemy.getWorldX() > player.getWorldX() + player.getWidth() + 15) {
                    if (chance(0.50)) {
                        enemy.moveLeft();
                        return;
                    }
                } else if (player.getWorldX() > enemy.getWorldX() + enemy.getWidth() && enemy.getWorldX() < player.getWorldX() - player.getWidth() - 15) {
                    if (chance(0.50)) {
                        enemy.moveRight();
                        return;
                    }
                }
                if (chance(0.10)) {
                    enemy.getWeapon().attack();
                    return;
                }
            } else if (enemy instanceof Boss) {
                inHostileRange = ((player.getWorldX() >= enemy.getWorldX() - gp.tileSize * 12 && player.getWorldX() <= enemy.getWorldX() + gp.tileSize * 12));
                if (!inHostileRange)
                    return;
                if (enemy.attackBufCounter > 90) {
                    enemy.attacking = true;
                    enemy.getWeapon().attack();
                    enemy.attackBufCounter = 0;
                }
                return;
            }
        }
    }


    public static boolean chance(double forYes){ //Yes or No
        return new Random().nextDouble() <= forYes;
    }

}
