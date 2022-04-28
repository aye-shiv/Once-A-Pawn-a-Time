package main.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.*;
import java.io.*;
import java.util.HashMap;

public class SoundManager {
    HashMap<String, Clip> clips = new HashMap<>();;
	private static SoundManager instance = null;

    private SoundManager(){
        /*=====entities=====*/


        /*=====objects=====*/
        addClip("staff_hit", "res/sounds/object/staff_sound.wav");
        addClip("spear_hit", "res/sounds/object/spear_sound.wav");

        addClip("sword_hit1", "res/sounds/object/sword/sword_clash1.wav");
        addClip("sword_hit2", "res/sounds/object/sword/sword_clash2.wav");
        addClip("sword_hit3", "res/sounds/object/sword/sword_clash3.wav");
        addClip("sword_hit4", "res/sounds/object/sword/sword_clash4.wav");

        /*=====sfx=====*/
        addClip("bgm", "res/sounds/sfx/castle_theme_1.wav");
        addClip("bossfight", "res/sounds/sfx/castle_theme_2.wav");
    }

    public static SoundManager getInstance(){
        if(instance == null)
            instance = new SoundManager();

        return instance;
    }

    private void addClip(String title, String filename){
        Clip clip = loadClip(filename);
        if(clip != null){
            clips.put(title, clip);
        }
    }

    private Clip loadClip(String filename){
        AudioInputStream audioIn;
		Clip clip = null;

        try {
            File file = new File(filename);
            audioIn = AudioSystem.getAudioInputStream(file.toURI().toURL());
            clip = AudioSystem.getClip();
            clip.open(audioIn);
		}
		catch (Exception e) {
            System.out.println ("Error opening sound file \'" + filename + "\'");
            e.printStackTrace();
		}
        return clip;
    }

    public Clip getClip(String title){
        return clips.get(title);
    }

    public void playClip(String title) { playClip(title, false); }
	public void playClip(String title, Boolean looping) {
		Clip clip = getClip(title);
		if (clip != null) {
			clip.setFramePosition(0);
			if (looping)
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			else
				clip.start();
		}
	}

    public void stopClip(String title) {
		Clip clip = getClip(title);
		if (clip != null) {
			clip.stop();
		}
	}
}
