package main.tiles;

import main.GamePanel;
import main.util.ImageManager;

import java.io.*;
import java.awt.image.BufferedImage;
import java.awt.Graphics2D;

public class TileManager {
    GamePanel gp;
    Tile[] tiles;
    int tileNum[][];
    BufferedImage bg_img;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tiles = new Tile[10];
        tileNum = new int[gp.maxScrollCol][gp.maxScrollRow];
        getTileImage();
        loadScrollMap("scroller_tiles");
        bg_img = (BufferedImage)(ImageManager.loadBufferedImage("res/images/tiles/background.png"));
    }

    public void loadScrollMap(String filename){
        try{
            InputStream is = new FileInputStream("res/images/tiles/"+filename+".txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while(col < gp.maxScrollCol && row < gp.maxScrollRow){
                String line = br.readLine();
                while(col<gp.maxScrollCol){
                    String nums[] = line.split(" ");
                    int num = Integer.parseInt(nums[col]);
                    tileNum[col][row]=num;
                    col++;
                }
                if(col==gp.maxScrollCol){
                    col = 0;
                    row++;
                }
            }
            br.close();
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void getTileImage() {
        try {
            tiles[0] = new Tile();
            tiles[1] = new Tile();
            tiles[1].image = ImageManager.loadBufferedImage("res/images/tiles/black_tile.png");
            tiles[2] = new Tile();
            tiles[2].image = ImageManager.loadBufferedImage("res/images/tiles/white_tile.png");

        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int scrollCol = 0, scrollRow = 0;
        
        int bgX = (gp.player.getScreenX() - gp.player.getWorldX())-48;
        if(gp.player.getWorldX() > (gp.maxScrollCol-14)*gp.tileSize){
            bgX = ((scrollCol * gp.tileSize) - ((gp.maxScrollCol-15)*gp.tileSize))-48;
        }
        g2.drawImage(bg_img,bgX , 0, 740*4, 720*4, null);

        while(scrollCol < gp.maxScrollCol && scrollRow < gp.maxScrollRow){
            int mapIndex = tileNum[scrollCol][scrollRow];

            int scrollX = scrollCol * gp.tileSize;
            int scrollY = scrollRow * gp.tileSize;
            int screenX = scrollX - gp.player.getWorldX() + gp.player.getScreenX();
            int screenY = scrollY;

            if(gp.player.getScreenX() > gp.player.getWorldX()){
                screenX = scrollX;
            }
            if(gp.player.getWorldX() > (gp.maxScrollCol-14)*gp.tileSize){
                screenX = scrollX - ((gp.maxScrollCol-15)*gp.tileSize);
            }

            g2.drawImage(tiles[mapIndex].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            scrollCol++;
            if(scrollCol == gp.maxScrollCol){
                scrollCol = 0;
                scrollRow++;
            }
        }
    }
}
