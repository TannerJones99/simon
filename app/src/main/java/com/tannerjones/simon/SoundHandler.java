package com.tannerjones.simon;

import android.content.Context;
import android.media.MediaPlayer;

public class SoundHandler {
    static MediaPlayer soundEffect1; // change these to final once we have the sound files
    static MediaPlayer soundEffect2;
    static MediaPlayer soundEffect3;
    static MediaPlayer soundEffect4;

    public SoundHandler(Context context){
        soundEffect1 = MediaPlayer.create(context, R.raw.select_blip1);
        soundEffect2 = MediaPlayer.create(context, R.raw.select_blip2);
        soundEffect3 = MediaPlayer.create(context, R.raw.select_blip3);
        soundEffect4 = MediaPlayer.create(context, R.raw.select_blip4);
    }

    public void playSoundByValue(int value){
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

    // Helper Method to Stop and Reset MediaPlayer
    public static void stopAudio() {
        soundEffect1.stop();
        soundEffect2.stop();
        soundEffect3.stop();
        soundEffect4.stop();

    }
}
