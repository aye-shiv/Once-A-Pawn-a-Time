package objects;

import main.GamePanel;
import main.util.CollisionDetector;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.animation.Animation;
import objects.entity.Player;

import java.awt.*;

public class NextLevelDoor extends GameObject {
    public boolean playerHitDoor = false;

    public NextLevelDoor(GamePanel gp) {
        super(gp);
        init();
    }

    public void init(){
        this.name = "Door";

        this.width = 90;
        this.height = 90;

        this.worldX = (gp.maxScrollCol-3) * gp.tileSize;
        this.worldY = gp.worldFloorY - height;

        this.image = ImageManager.loadBufferedImage("res/images/objects/door/door_6.png");
        this.soundManager = SoundManager.getInstance();
        setupAnimation();
        this.collision = doorCollision;
    }

    public void setupAnimation(){
        animation = new Animation(gp, this);
        animation.addFrame(image, 100); //First frame should be default image
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/door/door_1.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/door/door_2.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/door/door_3.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/door/door_4.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/door/door_5.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/door/door_6.png"), 100);
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

    protected Runnable doorCollision = () -> {
        if(collidingObject instanceof Player){
            playerHitDoor = true;
            collidingObject = null;
        }
    };

}
