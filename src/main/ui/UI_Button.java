package main.ui;

import main.GamePanel;
import main.util.ImageManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI_Button {
    GamePanel gp;
    String name;
    int x, y, width, height;
    private boolean isHovered = false;
    private BufferedImage base, hovered;

    public UI_Button(String button_name, int x, int y, int width, int height, GamePanel gp){
        this.gp = gp;
        this.name = button_name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.base = ImageManager.loadBufferedImage("res/images/ui/buttons/"+button_name+"_button_1.png");
        this.hovered = ImageManager.loadBufferedImage("res/images/ui/buttons/"+button_name+"_button_2.png");
    }

    public void update(){
        Rectangle r = new Rectangle(x, y, width, height);
        if((gp.getMouseHandler().getX()>x && gp.getMouseHandler().getX()<x+width)&&(gp.getMouseHandler().getY()>y&&gp.getMouseHandler().getX()<y+height)){
            isHovered = true;
        }
        else isHovered = false;
        if(isHovered && gp.getMouseHandler().getClicked()){
            //System.out.println(name);
            switch(name){
                case "start":
                    gp.getGSM().setState(1);
                    gp.getMouseHandler().clearMouseClicked();
                    break;

                case "resume":
                    gp.getGSM().setState(1);
                    gp.getMouseHandler().clearMouseClicked();
                    break;

                case "exit":
                    System.exit(0);
                    gp.getMouseHandler().clearMouseClicked();
                    break;

            }
        }
    }

    public void draw(Graphics2D g2){
        if(isHovered){
            g2.drawImage(hovered, x, y, width, height, null);
        }
        else g2.drawImage(base, x, y, width, height, null);
        //g2.draw(new Rectangle(x, y, width, height));
    }
}