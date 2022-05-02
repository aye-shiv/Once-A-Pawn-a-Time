package main.states;

import main.GamePanel;
import main.ui.UI;

import java.awt.*;
import objects.entity.Player;
import objects.entity.enemy.Pawn;
import objects.entity.enemy.boss.Bishop;
import objects.entity.enemy.boss.Knight;
import objects.entity.enemy.boss.Queen;
import objects.entity.enemy.boss.Rook;

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
        if(gp.getPlayer().getHP() == 0){
            currentState = GAMEOVER;
        }

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
        if(gp.getPlayer()==null){gp.player = new Player(gp);}
        else{
            gp.getPlayer().init();
            gp.getPawns().clear();
        }
        for(int i=0; i<6; i++){
            gp.getPawns().add(new Pawn(gp));
        }
        switch(level){
            case 1:
                gp.boss = new Bishop(gp);
                break;
            case 2:
                gp.boss = new Knight(gp);
                break;
            case 3:
                gp.boss = new Rook(gp);
                break;
            case 4:
                gp.boss = new Queen(gp);
                break;
        }
    }

    public void updateEntities(){
        gp.getPlayer().update();
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

    public int getCurrentState(){return currentState;}

    public void setState(int state){currentState = state;}
}
