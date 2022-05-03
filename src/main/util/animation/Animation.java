package main.util.animation;

import main.GamePanel;
import objects.GameObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Animation {
    protected GamePanel panel;					    // JPanel on which resources.animation is being displayed

    protected List<AnimFrame> frames = new ArrayList<>();
    protected AnimFrame frame; //Current frame
    protected int frameIndex = 0;
    protected long frameStartTime = 0;
    protected long frameEndTime = 0;
    protected long frameTimeRemaining = 0; //Used only when animation gets paused
    protected long totalDuration = 0;

    protected GameObject object;
    protected boolean animating = false;
    protected boolean looping = false;

    public boolean isAnimating() { return animating; }
    public boolean isLooping() { return looping; }
    public void setLooping(boolean loop) { this.looping = loop; }
    //==============

    public Animation(GamePanel gp, GameObject object){
        this.panel = gp;
        this.object = object;
        reset(true);
    }

    public void addFrame(BufferedImage image, long duration){
        totalDuration += duration;
        frames.add(new AnimFrame(image, duration));
    }

    public void clearFrames(){
        frames.clear();
        reset();
    }
    public void reset() { reset(false); }
    public void reset(boolean all) {
        frameIndex = 0;
        frameStartTime = 0;
        frameEndTime = 0;
        frameTimeRemaining = 0;

        if(all){
            frame = new AnimFrame(object.getImage(), 0);
            looping = false;
            animating = false;
        }
    }

    public void startAnimation(){ startAnimation(false); }
    public void startAnimation(boolean loop){
        setLooping(loop);
        reset();
        animating = true;
    }

    public void resumeAnimation(){
        if(animating)
            return;
        frameEndTime = System.currentTimeMillis() + frameTimeRemaining;
        frameTimeRemaining = 0;
        animating = true;
    }

    public void pauseAnimation(){
        long time = System.currentTimeMillis();
        long frameElapsedTime = time - frameStartTime;
        frameTimeRemaining = frame.duration - frameElapsedTime;
        animating = false;
    }

    public void update(){
        if(!animating)
            return;
        frameTimeRemaining = frameEndTime - System.currentTimeMillis();
        if(System.currentTimeMillis() < frameEndTime)
            return;

        frameIndex++;
        if(frameIndex >= frames.size()){ //Animation Complete
            reset();
            if(!looping)
                animating = false;
            return;
        }
        frame = frames.get(frameIndex);
        frameStartTime = System.currentTimeMillis();
        frameEndTime = frameStartTime + frame.duration;
    }

    public void draw (Graphics2D g2) {				// draw the current frame on the graphics context
        g2.drawImage(frame.image, object.getScreenX(), object.getScreenY(), object.getWidth(), object.getHeight(), null);

        if(false){ //Show hitbox
            Rectangle rect = object.getHitBox();
            g2.setColor(Color.BLUE);
            g2.drawRect(rect.x, rect.y, rect.width, rect.height);
        }
    }

    public long getTotalDuration() { return totalDuration; }
}
