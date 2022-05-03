package main.ui;

import main.GamePanel;
import objects.entity.Player;
import main.ui.HealthBar;

import main.util.ImageManager;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class UI {
    private GamePanel gp;
    protected static final int MENU = 0;
    protected static final int PLAY = 1;
    protected static final int PAUSE = 2;
    protected static final int GAMEOVER = 3;
    protected static final int WIN = 4;

    public UI(GamePanel gp){
        this.gp = gp;
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
                BufferedImage menuScreen = (BufferedImage) ImageManager.loadBufferedImage("res/images/ui/menu_screen.jpg");
                g2.drawImage(menuScreen,0,0,720,720,null);
                break;

            case PAUSE:
                BufferedImage pauseScreen = (BufferedImage) ImageManager.loadBufferedImage("res/images/ui/pause_screen.jpg");
                g2.drawImage(pauseScreen,0,0,720,720,null);
                break;

            case GAMEOVER:
                BufferedImage game_overScreen = (BufferedImage) ImageManager.loadBufferedImage("res/images/ui/game_over_screen.jpg");
                g2.drawImage(game_overScreen,0,0,720,720,null);
                break;

            case WIN:
                BufferedImage winScreen = (BufferedImage) ImageManager.loadBufferedImage("res/images/ui/win_screen.jpg");
                g2.drawImage(winScreen,0,0,720,720,null);
                break;
        }
    }
}
