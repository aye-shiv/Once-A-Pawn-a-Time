package main.util;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import main.GamePanel;

public class KeyHandler implements KeyListener {

	private GamePanel gp;
    public boolean upPressed=false, downPressed=false, leftPressed=false, rightPressed=false;
	public boolean spacePressed=false, enterPressed = false;

	protected static final int MENU = 0;
	protected static final int PLAY = 1;
	protected static final int PAUSE = 2;
	protected static final int GAMEOVER = 3;
	protected static final int WIN = 4;
	protected static final int SPLASH = 5;

    public KeyHandler(GamePanel gp) {
		this.gp = gp;
        gp.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP){
			upPressed = true;
		}
		if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
			leftPressed = true;
		}
		if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){
			downPressed = true;
		}
		if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
			rightPressed = true;
		}
		if (keyCode == KeyEvent.VK_SPACE){
			spacePressed = true;
		}
		if (keyCode == KeyEvent.VK_ENTER){
			enterPressed = true;
			gp.getGSM().nextLevelFlag = false;
		}
		if(keyCode == KeyEvent.VK_ESCAPE){
			gp.getGSM().setState(2);
		}

		// FOR TESTING
//		if(keyCode == KeyEvent.VK_2){
//			if(gp.getGSM().getLevel()<2)gp.getGSM().increaseLvl();
//		}
//		if(keyCode == KeyEvent.VK_3){
//			if(gp.getGSM().getLevel()<3)gp.getGSM().increaseLvl();
//		}
//		if(keyCode == KeyEvent.VK_4){
//			if(gp.getGSM().getLevel()<4)gp.getGSM().increaseLvl();
//		}
//		if(keyCode == KeyEvent.VK_BACK_SPACE){
//			gp.getGSM().setState(1);
//		}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP){
			upPressed = false;
		}
		if (keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT){
			leftPressed = false;
		}
		if (keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_DOWN){
			downPressed = false;
		}
		if (keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT){
			rightPressed = false;
		}
		if (keyCode == KeyEvent.VK_SPACE){
			spacePressed = false;
		}
		if (keyCode == KeyEvent.VK_ENTER) {
			enterPressed = false;
		}
    }
}
