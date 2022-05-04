package main.util;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import main.GamePanel;

public class MouseHandler implements MouseListener, MouseMotionListener {
    GamePanel gp;
    private static int mouseX = -1;
    private static int mouseY = -1;
    private static boolean clicked = false;
    private static boolean pressed = true;

    public MouseHandler(GamePanel gp) {
        this.gp = gp;
        gp.addMouseListener(this);
        gp.addMouseMotionListener(this);
    }

    public int getX() {
        return mouseX;
    }

    public int getY() {
        return mouseY;
    }

    public boolean getClicked() {
        return clicked;
    }

    public boolean getPressed() {
        return pressed;
    }

    @Override
    public void mouseClicked(MouseEvent e) { }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        clicked = true;
        pressed = false;
    }

    public void clearMouseClicked(){
        clicked = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
    }
    
}
