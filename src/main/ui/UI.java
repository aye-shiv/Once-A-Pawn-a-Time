package main.ui;

import main.GamePanel;
import objects.entity.Player;

import java.awt.*;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import main.util.ImageManager;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UI {
    private GamePanel gp;
    Font base, Pixel;
    private ArrayList<UI_Button> buttons;
    private UI_Button start, resume, exit1, exit2;

    protected static final int MENU = 0;
    protected static final int PLAY = 1;
    protected static final int PAUSE = 2;
    protected static final int GAMEOVER = 3;
    protected static final int WIN = 4;
    protected static final int SPLASH = 5;

    public UI(GamePanel gp){
        this.gp = gp;
        init();
    }

    private void init(){
        start = new UI_Button("start", 224, 318, 272, 74, gp);
        resume = new UI_Button("resume", 224, 318, 272, 74, gp);
        exit1 = new UI_Button("exit", 224, 430, 272, 74, gp);
        exit2 = new UI_Button("exit", 224, 318, 272, 74, gp);
        try{
            InputStream is = new BufferedInputStream(new FileInputStream("res/misc/pixel_7.ttf"));
            base = (Font.createFont(Font.TRUETYPE_FONT, is));
            Pixel = base.deriveFont(Font.PLAIN, 24);
        } catch(FontFormatException | IOException e){
            System.err.println("Font not loaded.");
            e.printStackTrace();
        }
        buttons = new ArrayList<UI_Button>();
        buttons.add(start);
        buttons.add(exit1);
    }

    public void update(){
        switch(gp.getGSM().getCurrentState()){
            case MENU:
                buttons.add(start);
                buttons.add(exit1);
                break;

            case PLAY:
                break;

            case PAUSE:
                buttons.add(resume);
                buttons.add(exit1);
                break;

            case GAMEOVER:
                break;

            case WIN:
                break;

            case SPLASH:
                break;
        }
        if(buttons.size()>0) {
            for (UI_Button button : new ArrayList<>(buttons)) {
                button.update();
            }
        }
    }

    public void draw(Graphics2D g2){
        switch(gp.getGSM().getCurrentState()) {
            case MENU:
                BufferedImage menuScreen = ImageManager.loadBufferedImage("res/images/ui/menu_screen.jpg");
                g2.drawImage(menuScreen,0,0,720,720,null);
                buttons.add(start);
                buttons.add(exit1);
                break;

            case PLAY:
                g2.setFont(Pixel);
                g2.setColor(Color.WHITE);
                g2.drawString("Level: "+gp.getGSM().getLevel(), 50, 100);

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
                Font smallPixel = Pixel.deriveFont(Pixel.getSize() * 0.8F);
                g2.setFont(smallPixel);
                g2.drawString( ""+gp.getPlayer().getHP(), 100, 70);
                break;

            case PAUSE:
                BufferedImage pauseScreen = ImageManager.loadBufferedImage("res/images/ui/pause_screen.jpg");
                g2.drawImage(pauseScreen,0,0,720,720,null);
                buttons.add(resume);
                buttons.add(exit1);
                break;

            case GAMEOVER:
                BufferedImage game_overScreen = ImageManager.loadBufferedImage("res/images/ui/game_over_screen.jpg");
                g2.drawImage(game_overScreen,0,0,720,720,null);
                break;

            case WIN:
                BufferedImage winScreen = ImageManager.loadBufferedImage("res/images/ui/win_screen.jpg");
                g2.drawImage(winScreen,0,0,720,720,null);
                break;

            case SPLASH:
                BufferedImage bf = ImageManager.loadBufferedImage("res/images/ui/level_splash/level_"+gp.getGSM().getLevel()+".png");
                g2.drawImage(bf, 0, 0, 720, 720, null);
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
