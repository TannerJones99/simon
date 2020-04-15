package com.tannerjones.simon;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class ButtonHandler extends Activity {
    ArrayList<Button> buttons;
    int currentGamemode;

    public ButtonHandler(int currentGamemode){
        // set buttons depending on which game it is.
        for(int i = 1; i < 5; i++){
            String buttonId;
            buttonId = "button"+i;
            int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
            buttons.add((Button)findViewById(resId));
        }
        this.currentGamemode = currentGamemode;
        if(currentGamemode == 1){
            // assuming game uses same color buttons
            for(int i = 0; i < buttons.size(); i++){
                buttons.get(i).setBackground(getDrawable(R.drawable.darkred));
            }
        }
        else{
            buttons.get(0).setBackground(getDrawable(R.drawable.darkred));
            buttons.get(1).setBackground(getDrawable(R.drawable.darkblue));
            buttons.get(2).setBackground(getDrawable(R.drawable.darkgreen));
            buttons.get(3).setBackground(getDrawable(R.drawable.darkpurple));
        }
    }


    public void flashButtonByValue(int value){
        if(currentGamemode == 1) {
           performFlash(value, R.drawable.lightred, R.drawable.darkred);
        }
            // assuming game uses same color buttons
        else{
            if(value == 1){
                performFlash(value, R.drawable.lightred, R.drawable.darkred);
            }
            else if(value == 2){
                performFlash(value, R.drawable.lightblue, R.drawable.darkblue);
            }
            else if(value == 3){
                performFlash(value, R.drawable.lightgreen, R.drawable.darkgreen);
            }
            else if(value == 4){
                performFlash(value, R.drawable.lightpurple, R.drawable.darkpurple);
            }
        }
    }

    public void performFlash(int value, int idLight, int idDark){
        buttons.get(value-1).setBackground(getDrawable(idLight));
        Timer timer = new Timer();
        timer.schedule(new switchBack(value, idDark), 2);
    }

    class switchBack extends TimerTask {

        int value;
        int id;
        public switchBack(int value, int id){
            this.value = value;
            this.id = id;
        }

        @Override
        public void run() {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    buttons.get(value).setBackground(getDrawable(id));
                }
            });
        }
    }
}
