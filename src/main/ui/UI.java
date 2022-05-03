package main.ui;

import main.GamePanel;
import objects.entity.Player;
import main.ui.HealthBar;

import java.awt.*;
import main.util.ImageManager;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class UI {
    private GamePanel gp;
    private ArrayList<UI_Button> buttons;
    private UI_Button start, resume, exit1, exit2;

    protected static final int MENU = 0;
    protected static final int PLAY = 1;
    protected static final int PAUSE = 2;
    protected static final int GAMEOVER = 3;
    protected static final int WIN = 4;

    public UI(GamePanel gp){
        this.gp = gp;
        init();
    }

    private void init(){
        start = new UI_Button("start", 224, 318, 272, 74, gp);
        resume = new UI_Button("resume", 224, 318, 272, 74, gp);
        exit1 = new UI_Button("exit", 224, 430, 272, 74, gp);
        exit2 = new UI_Button("exit", 224, 318, 272, 74, gp);
        buttons = new ArrayList<UI_Button>();
        buttons.add(start);
        buttons.add(exit1);
    }

    public void update(){
        switch(gp.getGSM().getCurrentState()){
            case PLAY:
                break;
            case MENU:
                buttons.add(start);
                buttons.add(exit1);
                break;

            case PAUSE:
                buttons.add(resume);
                buttons.add(exit1);
                break;

            case GAMEOVER:
                break;

            case WIN:
                break;
        }
        if(buttons.size()>0) {
            for (UI_Button button : new ArrayList<>(buttons)) {
                button.update();
            }
        }
    }

    public void draw(Graphics2D g2){
        switch(gp.getGSM().getCurrentState()){
            case PLAY:
                Player player = gp.getPlayer();
                HealthBar hp_bar = new HealthBar(
                        player.getHP(),
                        player.getMaxHP(),
                        40,
                        40,
                        150,
                        50,
                        true
                );
                hp_bar.draw(g2);
                break;

            case MENU:
                BufferedImage menuScreen = ImageManager.loadBufferedImage("res/images/ui/menu_screen.jpg");
                g2.drawImage(menuScreen,0,0,720,720,null);
                buttons.add(start);
                buttons.add(exit1);
                break;

            case PAUSE:
                BufferedImage pauseScreen = ImageManager.loadBufferedImage("res/images/ui/pause_screen.jpg");
                g2.drawImage(pauseScreen,0,0,720,720,null);
                buttons.add(resume);
                buttons.add(exit1);

            case GAMEOVER:
                BufferedImage game_overScreen = ImageManager.loadBufferedImage("res/images/ui/game_over_screen.jpg");
                g2.drawImage(game_overScreen,0,0,720,720,null);
                break;

            case WIN:
                BufferedImage winScreen = ImageManager.loadBufferedImage("res/images/ui/win_screen.jpg");
                g2.drawImage(winScreen,0,0,720,720,null);
                break;
        }
        if(buttons.size()>0) {
            for (UI_Button button : new ArrayList<>(buttons)) {
                button.draw(g2);
            }
            buttons.clear();
        }
    }
}
