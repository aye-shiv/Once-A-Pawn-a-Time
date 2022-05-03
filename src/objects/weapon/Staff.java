package objects.weapon;

import main.GamePanel;
import main.util.CollisionDetector;
import main.util.ImageManager;
import main.util.SoundManager;
import main.util.animation.Animation;
import objects.entity.Entity;
import objects.entity.Player;
import objects.entity.enemy.Enemy;
import objects.entity.enemy.Pawn;
import objects.entity.enemy.boss.Bishop;
import objects.entity.enemy.boss.Boss;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Staff extends Weapon {

    public List<Magic> projectiles = new ArrayList<>();

	public Staff(GamePanel gp, Entity entity) {
		super(gp, entity);
        init();
	}

    public void init(){

        this.name = "Staff";

        this.width = 85;
        this.height = 65;

        this.screenXOffset = ((int)(entity.getWidth() * 0.02));
        this.screenYOffset = ((int)(entity.getHeight() * 0.02));

        this.image = ImageManager.loadBufferedImage("res/images/objects/staff/staff_1.png");
        this.soundManager = SoundManager.getInstance();
        setupAnimation();
        this.collision = () -> { };
    }

    public void setupAnimation(){
        animation = new Animation(gp, this);
        animation.addFrame(image, 100); //First frame should be default image
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/staff_1.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/staff_2.png"), 50);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/staff_3.png"), 100);
        animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/staff_4.png"), 200);
    }

    @Override
    public void draw(Graphics2D g2) {
        super.draw(g2);
        if(isAnimating())
            animation.draw(g2);

        projectiles.removeIf(projectile -> projectile.isDestroyed());
        for(Magic magic: new ArrayList<>(projectiles)){
            magic.draw(g2);
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
        for(Magic magic: new ArrayList<>(projectiles)){
            magic.update();
        }

    }

    @Override
    public void attack() {
        delayer.setDelayTime(1000);
        if(!delayer.inDelayPhase()){
            delayer.start();
            Magic magic = new Magic(gp, entity, this);
            magic.startAnimation(true);
            projectiles.add(magic);
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


    public static class Magic extends Weapon {

        Weapon weapon;
        int facing = -1;
        public Magic(GamePanel gp, Entity entity, Weapon weapon) {
            this(gp, entity);
            this.weapon = weapon;
            this.facing = entity.getFacing();
            init();
        }
        public Magic(GamePanel gp, Entity entity) {
            super(gp, entity);
        }

        public void init(){
            this.name = "Magic";
            this.width = 45;
            this.height = 15;
            this.hitDamage = 12;
            speedX = 10;

            this.screenXOffset = weapon.getScreenXOffset() + ((int)(weapon.getWidth() * 0.75));
            this.screenYOffset = weapon.getScreenYOffset() + ((int)(weapon.getHeight() * 0.01));

            worldX = entity.getWorldX();
            worldY = entity.getWorldY();

            this.image = ImageManager.loadBufferedImage("res/images/objects/staff/projectile/projectile_1.png");
            setupAnimation();
            this.collision = projectileCollision;
        }

        public void setupAnimation(){
            animation = new Animation(gp, this);
            animation.addFrame(image, 100); //First frame should be default image
            animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/projectile/projectile_1.png"), 100);
            animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/projectile/projectile_2.png"), 100);
            animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/projectile/projectile_3.png"), 100);
            animation.addFrame(ImageManager.loadBufferedImage("res/images/objects/staff/projectile/projectile_4.png"), 100);
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

            screenX = worldX - gp.player.getWorldX() + gp.player.getScreenX();
            screenY = worldY;
            if(isAnimating())
                animation.update();
        }

        @Override public void attack() {}

        Runnable projectileCollision = () -> {
            if(entity instanceof Player){
                if(collidingObject instanceof Enemy){
                    System.out.println("[" + name +"] Player hit a enemy");
                    Enemy enemy = (Enemy) collidingObject;
                    enemy.takeDamage(hitDamage);
                    collidingObject = null;
                    this.destroy();

                    if(animation == null)
                        startCollisionDelay(500);
                    else startCollisionDelay((int) animation.getTotalDuration());
                }
            } else if(entity instanceof Enemy){
                if(collidingObject instanceof Player){
                    System.out.println("[" + name +"] Enemy hit a player");
                    Player player = (Player) collidingObject;
                    player.takeDamage(hitDamage);
                    collidingObject = null;
                    this.destroy();

                    if(animation == null)
                        startCollisionDelay(500);
                    else startCollisionDelay((int) animation.getTotalDuration());

                }
            }
        };
    }

}