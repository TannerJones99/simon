package com.tannerjones.simon;

import android.app.Activity;
import android.content.Context;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Logic extends Activity {
    ArrayList<Integer> sequence;
    Random rand;
    int currentGame;
    ButtonHandler buttons;
    SoundHandler soundHandler;

    Logic(int gamemode, Context context){
        sequence = new ArrayList<>();
        rand = new Random();
        currentGame = gamemode;
        buttons = new ButtonHandler(gamemode);
        soundHandler = new SoundHandler(context);
    }

    public void addNewValueToSequence(){
        int value;
        do{
            value = rand.nextInt(5);
        }while(value != 0);
        sequence.add(value);
    }

    public boolean checkIfNext(int value, int position){
        if(sequence.get(position) == value){
            return true;
        }
        return false;
    }

    public int getSizeOfSequence(){
        return sequence.size();
    }

    public ArrayList<Integer> getSequence(){
        return sequence;
    }

    public void playSequence(){
        for(int i = 0; i < sequence.size(); i++){
            Timer timer = new Timer();
            timer.schedule(new SequencePlayer(i), 5);
        }

    }

    public void playClicked(int tag){
        soundHandler.playSoundByValue(sequence.get(tag));
        buttons.flashButtonByValue(sequence.get(tag));
    }

    class SequencePlayer extends TimerTask{

        int i;
        public SequencePlayer(int i){
            this.i = i;
        }

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    soundHandler.playSoundByValue(sequence.get(i));
                    buttons.flashButtonByValue(sequence.get(i));
                }
            });
        }
    }


}
