package com.tannerjones.simon;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Game3 extends AppCompatActivity implements View.OnClickListener {

    Logic logic;
    ArrayList<Button> buttons;
    TextView title;
    TextView round;
    int counter;
    int roundsCorrect;
    Button startButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelayout);

        title = findViewById(R.id.game_title);
        title.setText("Simon Sound");

        round = findViewById(R.id.round);
        round.setText("Round 1");

        buttons = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            String buttonId;
            buttonId = "button"+(i+1);
            int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
            buttons.add((Button)findViewById(resId));
            buttons.get(i).setTag(i);
            buttons.get(i).setOnClickListener(this);
        }
        startButton = findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startButton.setVisibility(View.GONE);
                playSequence();
            }
        });
    }

    // Handle App Minimization for Music
    @Override
    protected void onPause() {
        super.onPause();
        SoundHandler.stopAudio();
    }

    // Handle App Closing for Music
    @Override
    protected void onStop() {
        super.onStop();
        SoundHandler.stopAudio();
    }

    @Override
    protected void onStart() {
        super.onStart();
        logic = new Logic(3, getApplicationContext(), this, buttons);
        logic.addNewValueToSequence();
        counter = 0;
        roundsCorrect = 0;
    }

    @Override
    public void onClick(View view) {
        logic.playClicked((int)view.getTag());
        Log.i("Tag", ""+(int)view.getTag());
        if(logic.checkIfNext((int) view.getTag(), counter)){
            counter++;
            if(counter == logic.getSizeOfSequence()){
                roundsCorrect++;
                round.setText("Round " + (roundsCorrect+1));
                logic.addNewValueToSequence();
                counter = 0;
                playSequence();
            }
        }
        else{
            // alert Dialog display you made through x number of rounds;
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Game Over");
            builder.setMessage("Game over, you made it through "+roundsCorrect+" rounds of Random Simon. Return to menu");

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
