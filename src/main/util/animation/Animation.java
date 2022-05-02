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
    }

}




/*
public class Animation {
    
    protected GamePanel panel;					    // JPanel on which resources.animation is being displayed
    protected ArrayList<AnimFrame> frames;			// collection of frames for resources.animation
    protected int currFrameIndex = 0;				// current frame being displayed
    protected long animTime = 0;					// time that the resources.animation has run for already
    protected long startTime = -1;					// start time of the resources.animation or time since last update
    protected long totalDuration = 0;				// total duration of the resources.animation

    public boolean isAnimating = false;

    protected GameObject object;
    int xOffset = 0;
    int yOffset = 0;

    public Animation(GamePanel gp, GameObject object){ this(gp, object, 0, 0); }
    public Animation(GamePanel gp, GameObject object, int xOffset, int yOffset){
        this.panel = gp;
        this.object = object;
    }

    public synchronized void start() {
        animTime = 0;						// reset time resources.animation has run for to zero
        currFrameIndex = 0;					// reset current frame to first frame
	    startTime = System.currentTimeMillis();			// reset start time to current time
        isAnimating = true;
    }

    public synchronized void update() {
        long currTime = System.currentTimeMillis();		// find the current time
        long elapsedTime = currTime - startTime;		// find how much time has elapsed since last update
        startTime = currTime;					// set start time to current time

        if (frames.size() > 1) {
            animTime += elapsedTime;				// add elapsed time to amount of time resources.animation has run for
            if (animTime >= totalDuration) {			// if the time resources.animation has run for > total duration
                animTime = animTime % totalDuration;		//    reset time resources.animation has run for
                currFrameIndex = 0;				//    reset current frame to first frame
            }

            while (animTime > getFrame(currFrameIndex).endTime) {
                currFrameIndex++;				// set frame corresponding to time resources.animation has run for
            }
        }
	
	    //x = x + dx;
        //y = y + dy;

    }

    public void draw (Graphics2D g2) {				// draw the current frame on the graphics context
        int x = object.getWorldX() + xOffset;
        int y = object.getWorldY() + yOffset;
        int width = object.getWidth();
        int height = object.getHeight();
       g2.drawImage(getImage(), x, y, width, height, null);
    }

    public synchronized BufferedImage getImage() {
        if (frames.size() == 0) {
            return null;
        } else {
            return getFrame(currFrameIndex).image;
        }
    }


    public int getNumFrames() {					// find out how many frames in resources.animation
	    return frames.size();
    }
    private AnimFrame getFrame(int i) {				// returns ith frame in the collection
        return frames.get(i);
    }

    public synchronized void addFrame(BufferedImage image, long duration){
        totalDuration += duration;
        frames.add(new AnimFrame(image, totalDuration));
    }

    public static class AnimFrame {

        BufferedImage image;
        long endTime;

        public AnimFrame(BufferedImage image, long endTime) {
            this.image = image;
            this.endTime = endTime;
        }
    }
}
*/