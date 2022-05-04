package main.util;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.*;
import java.io.*;
import java.util.HashMap;

public class SoundManager {
    HashMap<String, Clip> clips = new HashMap<>();;
	private static SoundManager instance = null;

    private SoundManager(){
        /*=====objects=====*/
        addClip("open_door", "res/sounds/object/open_door.wav");

        /*=====entities=====*/
        addClip("take_health", "res/sounds/object/take_health.wav");
        addClip("death", "res/sounds/object/piece_death.wav");

        /*=====weapons=====*/

        addClip("dagger_attack", "res/sounds/object/dagger_attack.wav");
        addClip("sword_attack", "res/sounds/object/sword_attack.wav");
        addClip("spear_attack", "res/sounds/object/spear_attack.wav");
        addClip("staff_attack", "res/sounds/object/staff_attack.wav");
        addClip("cannon_attack", "res/sounds/object/cannon_fire.wav");
        addClip("sceptre_attack", "res/sounds/object/sceptre_attack.wav");

        /*=====sfx=====*/
        addClip("bgm", "res/sounds/sfx/castle_theme_1.wav");

        addClip("click", "res/sounds/sfx/click.wav");
        addClip("hover", "res/sounds/sfx/hover_button.wav");
        addClip("splash", "res/sounds/sfx/splash_interact.wav");
        addClip("health_pack", "res/sounds/sfx/health_pack.wav");
        addClip("game_over", "res/sounds/sfx/game_over.wav");
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
