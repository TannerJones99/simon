package com.tannerjones.simon;

import android.media.MediaPlayer;

public class SoundHandler {
    static MediaPlayer soundEffect1; // change these to final once we have the sound files
    static MediaPlayer soundEffect2;
    static MediaPlayer soundEffect3;
    static MediaPlayer soundEffect4;

    public static void playSoundByValue(int value){
        if(value == 1){
            soundEffect1.start();
        }
        else if(value == 2){
            soundEffect2.start();
        }
        else if(value == 3){
            soundEffect3.start();
        }
        else if(value == 4){
            soundEffect4.start();
        }
    }

}
