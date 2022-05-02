package main;

import javax.swing.*;			// need this for GUI objects

/*
import entity.Player;
import entity.enemy.Pawn;
import entity.enemy.boss.*;
*/
import objects.entity.*;
import objects.entity.enemy.*;
import objects.entity.enemy.boss.*;


import main.tiles.TileManager;
import main.util.CollisionDetector;
import main.util.ImageManager;
import main.util.KeyHandler;
import main.util.MouseHandler;
import main.util.SoundManager;
import main.util.Utils;

import java.awt.*;			// need this for Layout Managers
import java.awt.image.BufferStrategy; // Image buffering
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {

    public static int width;
    public static int height;

    final private int ogTileSize = 16;
    final private int scale = 3;
    final public int tileSize = ogTileSize * scale;
    final private int FPS = 60;

    public final int maxScrollCol = 47;
    public final int maxScrollRow = 15;
    public final int worldWidth = tileSize * maxScrollCol;
    public final int worldHeight = tileSize * maxScrollRow;
    public final int worldFloorY = (worldHeight - tileSize*5);

    final public int screenWidth = width;
    final public int screenHeight = height;
    


    private Thread thread;
    private boolean running = false;
    
    private TileManager tileM = new TileManager(this);
    private CollisionDetector colDetect = new CollisionDetector(this);
    private SoundManager soundM;
    private MouseHandler mouse;
    private KeyHandler key;

    private BufferedImage bg_img;

    public Player player;
    public ArrayList<Pawn> pawns = new ArrayList<Pawn>();
    public Boss boss;
    private int level = 1;

    //Constructor
    public GamePanel(int width, int height){
        GamePanel.width = width;
        GamePanel.height = height;
        setBackground(Color.BLUE);
        
        setPreferredSize(new Dimension(width, height));
        this.setDoubleBuffered(true);
        setFocusable(true);
        requestFocus();
    }

    public void init(){
        running = true;
        mouse = new MouseHandler(this);
        key = new KeyHandler(this);
        soundM = SoundManager.getInstance();
    }

    public void startGameThread(){
		thread = new Thread(this);
		run();
	}

    @Override
    public void run() {
        init();
        double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime(), currentTime;
        generateEntities();
        soundM.playClip("bgm", true);
		while(running){
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/drawInterval;
			lastTime = currentTime;
			//Delta method for frames per second
			if(delta>=1){
				update();// Update game entities
				repaint();// redraws game entities based on new position
				delta--;
			}
		}
    }

    public void generateEntities(){
        player = new Player(this);
        for(int i=0; i<6; i++){
            //int pawnX = Utils.getRandom(13*tileSize, (maxScrollCol-7)*tileSize);
            //pawns.add(new Pawn(this, pawnX));
            pawns.add(new Pawn(this));
        }
        boss = new Queen(this);
    }

    public void update(){
        updateEntities();
    }

    public void updateEntities(){
        player.update();
        for(Pawn pawn:pawns){
            pawn.update();
        }
        boss.update();
    }

    public void drawEntities(Graphics2D g2){
        for(Pawn pawn:pawns){
            pawn.draw(g2);
        }
        boss.draw(g2);
        player.draw(g2);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        tileM.draw(g2);
        
        drawEntities(g2);

        g2.dispose();
    }

    public KeyHandler getKeyHandler(){
        return key;
    }

    public CollisionDetector getCollisionDet(){
        return colDetect;
    }
}
