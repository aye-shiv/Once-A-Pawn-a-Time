package main;

import javax.swing.*;			// need this for GUI objects
import java.awt.*;			// need this for Layout Managers
import java.awt.image.BufferStrategy; // Image buffering

//@SuppressWarnings({"unchecked"})
public class GameWindow extends JFrame {
    private GamePanel gp;
    private BufferStrategy bs;

    public GameWindow(){
        setTitle("ONCE A-PAWN A TIME");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIgnoreRepaint(true);
        setResizable(false);

        gp = new GamePanel(720, 720);
        add(gp);

        pack();
        setLocationRelativeTo(null);
        setVisible(true);

        gp.startGameThread();
    }
    
}
