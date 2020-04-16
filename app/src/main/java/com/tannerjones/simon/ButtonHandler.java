package com.tannerjones.simon;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ButtonHandler {
    int currentGamemode;
    Activity activty;
    ArrayList<Button> buttons;

    public ButtonHandler(int currentGamemode, Activity activity, ArrayList<Button> buttons){
        // set buttons depending on which game it is.
        this.currentGamemode = currentGamemode;
        this.activty = activity;
        this.buttons = buttons;
        if(currentGamemode == 1){
            // assuming game uses same color buttons
            for(int i = 0; i < buttons.size(); i++){
                buttons.get(i).setBackground(activity.getDrawable(R.drawable.darkred));
            }
        }
        else{
            buttons.get(0).setBackground(activity.getDrawable(R.drawable.darkred));
            buttons.get(1).setBackground(activity.getDrawable(R.drawable.darkblue));
            buttons.get(2).setBackground(activity.getDrawable(R.drawable.darkgreen));
            buttons.get(3).setBackground(activity.getDrawable(R.drawable.darkpurple));
        }
    }


    public void flashButtonByValue(int value){
        if(currentGamemode == 1) {
           performFlash(value, R.drawable.lightred, R.drawable.darkred);
        }
            // assuming game uses same color buttons
        else{
            if(value == 0){
                performFlash(value, R.drawable.lightred, R.drawable.darkred);
            }
            else if(value == 1){
                performFlash(value, R.drawable.lightblue, R.drawable.darkblue);
            }
            else if(value == 2){
                performFlash(value, R.drawable.lightgreen, R.drawable.darkgreen);
            }
            else if(value == 3){
                performFlash(value, R.drawable.lightpurple, R.drawable.darkpurple);
            }
        }
    }

    public void performFlash(int value, int idLight, final int idDark){
        final int valueUsed = value;
        buttons.get(value).setBackground(activty.getDrawable(idLight));
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                buttons.get(valueUsed).setBackground(activty.getDrawable(idDark));
            }
        }, 500);
    }


}
