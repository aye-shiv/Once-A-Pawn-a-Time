package objects;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Delay {
    protected boolean delayPhase = false;
    protected int delayTime = 500;
    protected Timer delayer;

    public Delay(){

        delayer = new Timer(0, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delayPhase = false;
            }
        });
    }

    /* =====Custom==== */
    public void start(){
        delayer.setInitialDelay(delayTime);
        delayer.setDelay(delayTime);
        delayPhase = true;
        delayer.restart();
    }

    public boolean inDelayPhase() { return delayPhase; }

    /* =====Getters==== */

    public Timer getDelayer() { return delayer; }
    public int getDelayTime() { return delayTime; }

    /* =====Setters==== */

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }
}
