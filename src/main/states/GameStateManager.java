package main.states;

import main.GamePanel;
import main.ui.UI;
import objects.HealthPack;
import objects.NextLevelDoor;
import objects.entity.Player;
import objects.entity.enemy.Pawn;
import objects.entity.enemy.boss.Bishop;
import objects.entity.enemy.boss.Knight;
import objects.entity.enemy.boss.Queen;
import objects.entity.enemy.boss.Rook;
import objects.weapon.melee.Spear;
import objects.weapon.melee.Sword;
import objects.weapon.ranged.Cannon;
import objects.weapon.ranged.Staff;

import java.awt.*;
import java.util.ArrayList;

public class GameStateManager {

    private GamePanel gp;
    private UI ui;

    protected static final int MENU = 0;
    protected static final int PLAY = 1;
    protected static final int PAUSE = 2;
    protected static final int GAMEOVER = 3;
    protected static final int WIN = 4;
    protected static final int SPLASH = 5;
    protected int currentState;

    public boolean nextLevelFlag = false;
    boolean increaseLevel = false;
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
        if(gp.getPlayer().isDestroyed() && currentState != GAMEOVER){ //Player lost
            gp.getSound().playClip("game_over");
            currentState = GAMEOVER;
        } else if(gp.boss.isDestroyed() && gp.getPawns().isEmpty()){ //Player beats level
            if(gp.door == null) {
                gp.door = new NextLevelDoor(gp);
                gp.getSound().playClip("open_door");
                gp.door.getAnimation().startLoopLastFrame();
            }
        }
        ui.update();

        switch(currentState){
            case PLAY:
                updateEntities();
                if(increaseLevel){
                    generateEntities();
                    setState(SPLASH);
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

            case SPLASH:
                if(gp.getKeyHandler().enterPressed){
                    gp.getSound().playClip("splash");
                    setState(PLAY);
                }
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
            gp.player.setWorldX(gp.tileSize*1);
            gp.player.setWorldY(gp.worldFloorY - gp.player.getHeight());
            gp.player.setScreenX(gp.player.getWorldX());
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
                break;
            case 2:
                gp.boss = new Bishop(gp);
                for(int i=0; i<6; i++){
                    gp.getPawns().add(new Pawn(gp));
                }
                gp.player.setWeapon(new Spear(gp, gp.player));
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
                break;
        }
    }

    public void updateEntities(){
        gp.pawns.removeIf(pawn -> pawn.isDestroyed());
        for(Pawn pawn: new ArrayList<>(gp.pawns)){
            pawn.update();
        }
        gp.healthPacks.removeIf(healthPack -> healthPack.isDestroyed());
        for(HealthPack pack: new ArrayList<>(gp.healthPacks)){
            pack.update();
        }
        if(!gp.boss.isDestroyed())
            gp.boss.update();
        if(gp.door != null) {
            gp.door.update();
            if(gp.door.playerHitDoor) {
                gp.door.playerHitDoor = false;
                gp.door = null;
                increaseLvl();
            }
        }
        gp.getPlayer().update();
    }

    public void drawEntities(Graphics2D g2){
        for(Pawn pawn: new ArrayList<>(gp.pawns)){
            pawn.draw(g2);
        }
        for(HealthPack pack: new ArrayList<>(gp.healthPacks)){
            pack.draw(g2);
        }
        if(!gp.boss.isDestroyed())
            gp.boss.draw(g2);
        if(gp.door != null)
            gp.door.draw(g2);
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
