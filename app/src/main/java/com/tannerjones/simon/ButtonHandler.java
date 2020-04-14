package com.tannerjones.simon;

import android.graphics.drawable.Drawable;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ButtonHandler extends AppCompatActivity {
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
            if(value == 1){
                // flash button 1;
                buttons.get(0).setBackground(getDrawable(R.drawable.lightred));
                // set timer before
                buttons.get(0).setBackground(getDrawable(R.drawable.darkred));
            }
            else if(value == 2){
                // flash button 2;
                buttons.get(0).setBackground(getDrawable(R.drawable.lightred));
                // set timer before
                buttons.get(0).setBackground(getDrawable(R.drawable.darkred));
            }
            else if(value == 3){
                // flash button 3;
                buttons.get(0).setBackground(getDrawable(R.drawable.lightred));
                // set timer before
                buttons.get(0).setBackground(getDrawable(R.drawable.darkred));
            }
            else if(value == 4){
                // flash button 4;
                buttons.get(0).setBackground(getDrawable(R.drawable.lightred));
                // set timer before
                buttons.get(0).setBackground(getDrawable(R.drawable.darkred));
            }
        }
            // assuming game uses same color buttons
        else{
            if(value == 1){
                // flash button 1;
                buttons.get(0).setBackground(getDrawable(R.drawable.lightred));
                // set timer before
                buttons.get(0).setBackground(getDrawable(R.drawable.darkred));
            }
            else if(value == 2){
                // flash button 2;
                buttons.get(0).setBackground(getDrawable(R.drawable.lightblue));
                // set timer before
                buttons.get(0).setBackground(getDrawable(R.drawable.darkblue));
            }
            else if(value == 3){
                // flash button 3;
                buttons.get(0).setBackground(getDrawable(R.drawable.darkgreen));
                // set timer before
                buttons.get(0).setBackground(getDrawable(R.drawable.darkgreen));
            }
            else if(value == 4){
                // flash button 4;
                buttons.get(0).setBackground(getDrawable(R.drawable.lightpurple));
                // set timer before
                buttons.get(0).setBackground(getDrawable(R.drawable.darkpurple));
            }
        }
    }
}
