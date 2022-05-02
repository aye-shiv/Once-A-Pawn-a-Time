package main.util;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

import main.GamePanel;

public class KeyHandler implements KeyListener {

	private GamePanel gp;
    public boolean upPressed=false, downPressed=false, leftPressed=false, rightPressed=false;
	public boolean spacePressed=false;

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
		if(keyCode == KeyEvent.VK_ESCAPE){
			gp.getGSM().setState(2);
		}
		if(keyCode == KeyEvent.VK_BACK_SPACE){
			gp.getGSM().setState(1);
		}
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
    }
}
