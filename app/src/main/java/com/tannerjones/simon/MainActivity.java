package com.tannerjones.simon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Setup MediaPlayer
    enum MediaState {NOT_READY, PLAYING, PAUSED, STOPPED};
    private MediaState mediaState;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Handle Game Variation 1
        findViewById(R.id.game1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game1.class);
                startActivity(intent);
            }
        });

        // Handle Game Variation 2
        findViewById(R.id.game2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game2.class);
                startActivity(intent);
            }
        });

        // Handle Game Variation 3
        findViewById(R.id.game3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game3.class);
                startActivity(intent);
            }
        });

        // Handle Game Credits
        findViewById(R.id.credits).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = "<html>" +
                        "<h2>Music</h2>" +
                        "<p><a href='https://opengameart.org/content/coffee-black'>Coffee Black</a> <br>" +
                        "<b>Creator</b>: GrimFrenzy <br>" +
                        "<b>License</b>: CC-BY 3.0 <br>" +
                        "<h2>Sounds</h2>" +
                        "<p><a href='https://opengameart.org/content/backwards-button-press-ui-sound'>Select Blip and Error</a> <br>" +
                        "<b>Creator</b>: dklon <br>" +
                        "<b>License</b>: CC-BY 3.0" +
                        "</p></html>";

                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage(Html.fromHtml(message));
                builder.setPositiveButton("OK", null);

                AlertDialog dialog = builder.create();
                dialog.show();

                // Handle Links for Credits
                TextView tv = dialog.findViewById(android.R.id.message);
                tv.setMovementMethod(LinkMovementMethod.getInstance());
            }
        });
    }

    // Handle App Minimization for Music
    @Override
    protected void onPause() {
        super.onPause();
        stopAudio();
    }

    // Handle App Closing for Music
    @Override
    protected void onStop() {
        super.onStop();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            mediaState = MediaState.NOT_READY;
        }
    }

    // Play Music Again when App Opens
    @Override
    protected void onResume() {
        super.onResume();
        playAudio();
    }

    // Helper Method to Stop and Reset MediaPlayer
    private void stopAudio() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaState = MediaState.STOPPED;
        }
    }

    // Helper Method to Reset MediaPlayer
    private void playAudio() {
        if (mediaPlayer == null) {
            mediaState = MediaState.NOT_READY;

            mediaPlayer = MediaPlayer.create(getApplicationContext(),
                    R.raw.coffee_black);
            mediaPlayer.setLooping(true);

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    Log.i("MEDIA", "--------------- ready to play");

                    mediaPlayer.start();
                    mediaState = MediaState.PLAYING;
                }
            });

            mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Log.i("MEDIA", "--------------- problem playing sound");
                    return false;
                }
            });
        } else if (mediaState == MediaState.PAUSED) {
            mediaPlayer.start();
            mediaState = MediaState.PLAYING;
        } else if  (mediaState == MediaState.STOPPED) {
            mediaPlayer.prepareAsync();
            mediaState = MediaState.NOT_READY;
        }
    }
}
