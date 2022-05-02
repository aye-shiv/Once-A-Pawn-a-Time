package objects.entity.enemy.boss;

import java.awt.Graphics2D;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;

public class Knight extends Boss {

	public Knight(GamePanel gp) {
		super(gp);
        init();
	}

    public void init(){
        this.width = 48;
        this.height = 48*2;

        setSpeedX(22);

        this.image = ImageManager.loadBufferedImage("res/images/entity/W_Knight.png");
        this.soundManager = SoundManager.getInstance();
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub

        this.weapon.update();
    }

    @Override
    public void draw(Graphics2D g2) {
        // TODO Auto-generated method stub

        this.weapon.draw(g2);
    }    

}