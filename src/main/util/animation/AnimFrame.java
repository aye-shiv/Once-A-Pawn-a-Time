package main.util.animation;

import java.awt.image.BufferedImage;

public class AnimFrame {
    BufferedImage image;
    long duration;

    public AnimFrame(BufferedImage image, long duration) {
        this.image = image;
        this.duration = duration;
    }

}
