package com.tannerjones.simon;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Game1 extends AppCompatActivity implements View.OnClickListener {

    Logic logic;
    ArrayList<Button> buttons;
    int counter;
    int roundsCorrect;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelayout);
        logic = new Logic(1, getApplicationContext());
        buttons = new ArrayList<>();
        for(int i = 1; i < 5; i++){
            String buttonId;
            buttonId = "button"+i;
            int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
            buttons.add((Button)findViewById(resId));
            buttons.get(i).setTag(i);
            buttons.get(i).setOnClickListener(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        logic.addNewValueToSequence();
        // set delay timer for start.
        playSequence();
        counter = 0;
        roundsCorrect = 0;
    }

    @Override
    public void onClick(View view) {
        logic.playClicked(counter);
        if(logic.checkIfNext((int) view.getTag(), counter)){
            counter++;
            if(counter == logic.getSizeOfSequence()){
                roundsCorrect++;
                playSequence();
            }
        }
        else{
            // alert Dialog display you made through x number of rounds;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Game Over");
            builder.setMessage("Game over, you made it through "+roundsCorrect+" rounds of Simon. Return to menu");

            builder.setPositiveButton("Menu", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    public void playSequence(){
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).setOnClickListener(null);
        }
        logic.playSequence();
        for(int i = 0; i < buttons.size(); i++){
            buttons.get(i).setOnClickListener(this);
        }
    }
}
