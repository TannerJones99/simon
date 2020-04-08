package com.tannerjones.simon;

import java.util.ArrayList;
import java.util.Random;

public class Logic {
    ArrayList<Integer> sequence;
    Random rand;
    int currentGame;
    ButtonHandler buttons;

    Logic(int gamemode){
        sequence = new ArrayList<>();
        rand = new Random();
        currentGame = gamemode;
        buttons = new ButtonHandler(gamemode);
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
            SoundHandler.playSoundByValue(sequence.get(i));
            buttons.flashButtonByValue(sequence.get(i));
            // set timer before next beep.
        }

    }


}
