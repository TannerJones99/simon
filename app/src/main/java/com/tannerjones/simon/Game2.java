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

public class Game2 extends AppCompatActivity implements View.OnClickListener {

    Logic logic;
    ArrayList<Button> buttons;
    TextView title;
    TextView round;
    TextView hint;
    int counter;
    int count;
    int roundsCorrect;
    boolean setup = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelayout);

        title = findViewById(R.id.game_title);
        title.setText("Simon Self");

        round = findViewById(R.id.round);
        round.setText("Round 1");

        hint = findViewById(R.id.hint);
        hint.setText("Start a Pattern");

        buttons = new ArrayList<>();

        // Add buttons to ArrayList
        for(int i = 0; i < 4; i++){
            String buttonId;
            buttonId = "button"+(i+1);
            int resId = getResources().getIdentifier(buttonId, "id", getPackageName());
            buttons.add((Button)findViewById(resId));
            buttons.get(i).setTag(i);
            buttons.get(i).setOnClickListener(this);
        }

        findViewById(R.id.startButton).setVisibility(View.INVISIBLE);
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

        // Start the sequence by Gamemode
        logic = new Logic(2, getApplicationContext(), this, buttons);
        counter = 0;
        roundsCorrect = 0;
    }

    @Override
    public void onClick(View view) {
        logic.playClicked((int)view.getTag());
        Log.i("Tag", ""+(int)view.getTag());
        if (setup) {
            logic.addCustomValueToSequence((int)view.getTag());
            count++;
            if (logic.getSizeOfSequence() == count) {
                setup = false;
                playSequence();
                hint.setText("Repeat the Pattern");
                Log.i("ADD", "PLAY");
            }
        } else {
            if (logic.checkIfNext((int) view.getTag(), counter)) {
                counter++;
                if (counter == logic.getSizeOfSequence()) {
                    roundsCorrect++;
                    round.setText("Round " + (roundsCorrect+1));
                    hint.setText("Add to the Pattern");
                    counter = 0;
                    Log.i("ADD", "ADD NEW");
                    setup = true;
                }
            } else {
                // alert Dialog display you made through x number of rounds;
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Game Over");
                builder.setMessage("Game over, you made it through " + roundsCorrect + " rounds of Simon. Return to menu");

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
