package objects.entity.enemy.boss;

import java.awt.Graphics2D;

import main.GamePanel;
import main.util.ImageManager;
import main.util.SoundManager;

public class Rook extends Boss {

	public Rook(GamePanel gp) {
		super(gp);
        init();
	}

    public void init(){
        this.width = 48;
        this.height = 48*2;

        setSpeedX(22);

        this.image = ImageManager.loadBufferedImage("res/entity/W_Knight.png");
        this.soundManager = SoundManager.getInstance();
    }

    @Override
    public void draw(Graphics2D g2) {

        this.weapon.draw(g2);
    }

    @Override
    public void update() {

        this.weapon.update();
    }

}