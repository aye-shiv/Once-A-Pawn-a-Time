package objects.weapon;

import main.util.animation.Animation;
import objects.entity.Entity;
import objects.entity.Player;
import main.GamePanel;

import main.util.ImageManager;
import main.util.SoundManager;
import objects.entity.enemy.Enemy;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Cannon extends Weapon {

    public List<Ball> projectiles = new ArrayList<>();

	public Cannon(GamePanel gp, Entity entity) {
		super(gp, entity);
        init();
	}

    public void init(){

	    this.name = "Cannon";

        this.width = 70;
        this.height = 35;

        this.screenXOffset = ((int)(entity.getWidth() * 0.35));
        this.screenYOffset = ((int)(entity.getHeight() * 0.25));

        this.image = ImageManager.loadBufferedImage("res/images/objects/cannon/cannon_1.png");
        this.soundManager = SoundManager.getInstance();
        setupAnimation();
        this.collision = () -> { };
    }

    public void setupAnimation(){
        animation = new Animation(gp, this);
        animation.addFrame(image, 100); //First frame should be default image
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/cannon/cannon_1.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/cannon/cannon_2.png"), 70);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/cannon/cannon_3.png"), 100);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        if(isAnimating())
            animation.draw(g2);

        projectiles.removeIf(projectile -> projectile.isDestroyed());
        for(Ball ball: new ArrayList<>(projectiles)){
            ball.draw(g2);
        }
    }

    @Override
    public void update() {
        super.update();
        if(entity.getFacing() == Entity.FACING_LEFT){
            width = -Math.abs(width);
        } else if(entity.getFacing() == Entity.FACING_RIGHT){
            width = Math.abs(width);
        }

        screenX = entity.getScreenX() + screenXOffset;
        screenY = entity.getScreenY() + screenYOffset;

        if(isAnimating())
            animation.update();

        projectiles.removeIf(projectile -> projectile.isDestroyed());
        for(Ball ball: new ArrayList<>(projectiles)){
            ball.update();
        }
    }

    @Override
    public void attack() {
        delayer.setDelayTime(1000);
        if(!delayer.inDelayPhase()){
            delayer.start();
            Ball ball = new Ball(gp, entity, this);
            ball.startAnimation(true);
            projectiles.add(ball);
            resumeAnimation();
        }
    }

    @Override
    public Rectangle getHitBox(){ //Projectile weapon doesn't have a hitbox
        return new Rectangle(screenX, screenY, 0, 0);
    }


    /*
    ==========================================

    ==========================================
     */


    public static class Ball extends Weapon {

        Weapon weapon;
        int facing = -1;
        public Ball(GamePanel gp, Entity entity, Weapon weapon) {
            this(gp, entity);
            this.weapon = weapon;
            this.facing = entity.getFacing();
            init();
        }

        public Ball(GamePanel gp, Entity entity) {
            super(gp, entity);
        }

        public void init() {
            this.name = "CannonBall";
            this.width = 25;
            this.height = 25;
            this.hitDamage = 15;
            speedX = 10;

            this.screenXOffset = weapon.getScreenXOffset() + ((int)(weapon.getWidth() * 0.25));
            this.screenYOffset = weapon.getScreenYOffset() + ((int)(weapon.getHeight() * 0.25));

            worldX = entity.getWorldX();
            worldY = entity.getWorldY();

            this.image = ImageManager.loadBufferedImage("res/images/objects/cannon/projectile/cannon_ball.png");
            setupAnimation();
            this.collision = projectileCollision;
        }

        public void setupAnimation() {
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
            if(facing == Entity.FACING_LEFT)
                worldX -= speedX;
            else if(facing == Entity.FACING_RIGHT)
                worldX += speedX;

            if(worldX < 0 || worldX > gp.worldWidth)
                isDestroyed = true;

            screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX() + screenXOffset;
            screenY = worldY + screenYOffset;
            if(isAnimating())
                animation.update();
        }

        @Override
        public void attack() { }

        Runnable projectileCollision = () -> {
            if(entity instanceof Player){
                if(collidingObject instanceof Enemy){
                    System.out.println("[" + name +"] Player hit a enemy");
                    Enemy enemy = (Enemy) collidingObject;
                    enemy.takeDamage(hitDamage);
                    this.destroy();
                    collidingObject = null;
                }
            } else if(entity instanceof Enemy){
                if(collidingObject instanceof Player){
                    System.out.println("[" + name +"] Enemy hit a player");
                    Player player = (Player) collidingObject;
                    player.takeDamage(hitDamage);
                    this.destroy();
                    collidingObject = null;
                }
            }
        };
    }

}