package main.util;

import java.util.Random;

public class Utils {

    public static int getRandom(int max) { return getRandom(0, max); }
    public static int getRandom(int minRange, int maxRange){
        return new Random().nextInt((maxRange-minRange) + 1) + minRange;
    }

}