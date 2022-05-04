package main.ui;

import java.awt.Graphics2D;
import main.util.ImageManager;
import java.awt.image.BufferedImage;

public class HealthBar {
    int HP, maxHP, x, y, width, height;
    boolean hasBorder = false;
    BufferedImage border, health;

    public HealthBar(int currentHealth, int maxHealth, int x, int y, int width, int height){
        this.HP = currentHealth;
        this.maxHP = maxHealth;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.health = (BufferedImage)ImageManager.loadBufferedImage("res/images/ui/health.png");
    }

    public HealthBar(int currentHealth, int maxHealth, int x, int y, int width, int height, boolean hasBorder){
        this.HP = currentHealth;
        this.maxHP = maxHealth;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.hasBorder = hasBorder;
        this.border = (BufferedImage)ImageManager.loadBufferedImage("res/images/ui/empty_health_bar.png");
        this.health = (BufferedImage)ImageManager.loadBufferedImage("res/images/ui/health.png");
    }

    public void draw(Graphics2D g2){
        int relative_width = (int)(width*((double)HP/(double)maxHP));
        if(relative_width<0) relative_width = 0;
        if(hasBorder){
            g2.drawImage(border, x, y, width, height, null);
            g2.drawImage(health, x, y, relative_width, height, null);
        }
        else g2.drawImage(border, x, y, relative_width, height, null);
    }
}
