package objects.entity.enemy;

import main.GamePanel;
import objects.entity.Player;

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

        inHostileRange = ((player.getWorldX() >= enemy.getWorldX() - enemy.hostileRange && player.getWorldX() <= enemy.getWorldX() + enemy.hostileRange));
        if (!inHostileRange)
            return;

        if (chance(0.005))
            enemy.jump();

        if (player.getWorldX() < enemy.getWorldX() && enemy.getWorldX() > player.getWorldX() + player.getWidth() + 15){
            if (chance(0.50))
                enemy.moveLeft();
        }
        else if(player.getWorldX() > enemy.getWorldX() + enemy.getWidth() && enemy.getWorldX() < player.getWorldX() - player.getWidth() - 15) {
            if (chance(0.50))
                enemy.moveRight();
        }

        if (chance(0.03))
            enemy.getWeapon().attack();

    }


    public static boolean chance(double forYes){ //Yes or No
        return new Random().nextDouble() <= forYes;
    }

}
