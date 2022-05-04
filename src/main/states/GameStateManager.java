package main.states;

import main.GamePanel;
import main.ui.UI;
import objects.entity.Player;
import objects.entity.enemy.Pawn;
import objects.entity.enemy.boss.Bishop;
import objects.entity.enemy.boss.Knight;
import objects.entity.enemy.boss.Queen;
import objects.entity.enemy.boss.Rook;
import objects.weapon.melee.Spear;
import objects.weapon.melee.Sword;
import objects.weapon.projectile.Cannon;
import objects.weapon.projectile.ProjectileWeapon;
import objects.weapon.projectile.Staff;

import java.awt.*;

public class GameStateManager {

    private GamePanel gp;
    private UI ui;

    protected static final int MENU = 0;
    protected static final int PLAY = 1;
    protected static final int PAUSE = 2;
    protected static final int GAMEOVER = 3;
    protected static final int WIN = 4;
    protected int currentState;

    protected boolean increaseLevel = false;
    protected int level;

    public GameStateManager(GamePanel gp){
        this.gp = gp;
        init();
    }

    public void init(){
        this.level = 1;
        this.currentState = MENU;
        ui = new UI(gp);
    }

    public void update(){
        if(gp.getPlayer().isDestroyed()){ //Player lost
            currentState = GAMEOVER;
        } else if(gp.boss.isDestroyed() && gp.getPawns().isEmpty()){ //Player beat level

        }
        ui.update();

        switch(currentState){
            case PLAY:
                updateEntities();
                if(increaseLevel){
                    generateEntities();
                    increaseLevel = false;
                }
                break;

            case MENU:
                break;

            case PAUSE:
                break;

            case GAMEOVER:
                break;

            case WIN:
                break;
        }
    }

    public int getLevel(){
        return level;
    }

    public void increaseLvl(){
        level+=1;
        increaseLevel = true;
    }

    public void generateEntities(){
        if(gp.getPlayer()==null)
            gp.player = new Player(gp);

        else {
            gp.getPlayer().init();
            gp.getPawns().clear();
        }
        for(int i=0; i<6; i++){
            gp.getPawns().add(new Pawn(gp));
        }
        if(gp.getPawns().size()>0) gp.getPawns().clear();
        switch(level){
            case 1:
                gp.boss = new Knight(gp);
                for(int i=0; i<6; i++){
                    gp.getPawns().add(new Pawn(gp));
                }
                gp.player.setWeapon(new Sword(gp, gp.player));
                gp.player.takeHealth(gp.player.getMaxHP());
                break;
            case 2:
                gp.boss = new Bishop(gp);
                for(int i=0; i<6; i++){
                    gp.getPawns().add(new Pawn(gp));
                }
                gp.player.setWeapon(new Spear(gp, gp.player));
                gp.player.takeHealth(gp.player.getMaxHP());
                break;
            case 3:
                gp.boss = new Rook(gp);
                for(int i=0; i<6; i++){
                    gp.getPawns().add(new Pawn(gp));
                }
                gp.player.setWeapon(new Staff(gp, gp.player));
                gp.player.setProjectileWeaponXY(
                        ((int)(gp.player.getWidth() * 0.85)),
                        ((int)(gp.player.getHeight() * 0.55))
                );
                gp.player.takeHealth(gp.player.getMaxHP());
                break;
            case 4:
                gp.boss = new Queen(gp);
                for(int i=0; i<6; i++){
                    gp.getPawns().add(new Pawn(gp));
                }
                gp.player.setWeapon(new Cannon(gp, gp.player));
                gp.player.setProjectileWeaponXY(
                        ((int)(gp.player.getWidth() * 0.85)),
                        ((int)(gp.player.getHeight() * 0.35))
                );
                gp.player.takeHealth(gp.player.getMaxHP());
                break;
        }
    }

    public void updateEntities(){
        gp.getPlayer().update();
        gp.getPawns().removeIf(pawn -> pawn.isDestroyed());
        for(Pawn pawn:gp.getPawns()){
            pawn.update();
        }
        gp.boss.update();
    }

    public void drawEntities(Graphics2D g2){
        for(Pawn pawn:gp.getPawns()){
            pawn.draw(g2);
        }
        gp.boss.draw(g2);
        gp.getPlayer().draw(g2);
    }

    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        gp.getTileManager().draw(g2);
        drawEntities(g2);
        ui.draw(g2);
        g2.dispose();
    }

    public boolean getLevelStatus(){return increaseLevel;}

    public int getCurrentState(){return currentState;}

    public void setState(int state){currentState = state;}
}
