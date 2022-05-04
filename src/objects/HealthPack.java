package objects;

import main.GamePanel;
import main.util.CollisionDetector;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.Utils;
import main.util.animation.Animation;
import objects.entity.Player;

import java.awt.*;

public class HealthPack extends GameObject {

    protected int health = 0;

    public HealthPack(GamePanel gp) {
        super(gp);
        init();
    }

    public void init(){
        this.name = "HealthPack";

        this.width = 30;
        this.height = 30;

        speedY = 3;

        this.health = Utils.getRandom(20, 45); //Randomize health

        this.image = ImageManager.loadBufferedImage("res/images/objects/health_pack.png");
        this.soundManager = SoundManager.getInstance();
        setupAnimation();
        this.collision = healthPackCollision;
    }

    public void setupAnimation(){
        animation = new Animation(gp, this);
        animation.addFrame(image, 100); //First frame should be default image
        animation.addFrame(image, 100);
    }


    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);

        if(isAnimating())
            animation.draw(g2);
    }

    @Override
    public void update() {
        super.update();
        if(worldY+speedY <= gp.worldFloorY - height) //Gravity
            worldY += speedY;

        screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX();
        screenY = worldY;
        if(gp.player.getWorldX() > (gp.maxScrollCol-14)*gp.tileSize){
            screenX = worldX - ((gp.maxScrollCol-15)*gp.tileSize);
        }

        if(isAnimating())
            animation.update();
    }

    @Override
    protected void checkColliding(){
        if(CollisionDetector.isColliding(this, gp.player)){
            collidingObject = gp.player;
        }
    }

    protected Runnable healthPackCollision = () -> {
        if(collidingObject instanceof Player){
            gp.player.takeHealth(health);
            destroy();
            collidingObject = null;
        }
    };

}
