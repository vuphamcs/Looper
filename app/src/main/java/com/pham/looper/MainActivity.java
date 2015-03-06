package com.pham.looper;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

    static boolean startPlayingAll = true;
    static Button playAllB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playAllB = (Button) findViewById(R.id.playAllB);
        playAllB.setText("Play All");

        playAllB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAll();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPause() {
        super.onPause();

        for (RecordButton rb : RecordButton.recordButtons) {
            if (!rb.mStartRecording) {
                rb.stopRecording();
            }
        }

        for (PlayButton pb : PlayButton.playButtons) {
            if (!pb.mStartPlaying) {
                pb.stopPlaying();
            }
        }

        playAllB.setText("Play All");
    }

    public static void playAll() {
        if (startPlayingAll) {
            for (PlayButton pb : PlayButton.playButtons) {
                if (!pb.mStartPlaying) {
                    pb.stopPlaying();
                }
                pb.startPlaying();
            }
            playAllB.setText("Stop All");
        } else {
            for (PlayButton pb : PlayButton.playButtons) {
                if (!pb.mStartPlaying) {
                    pb.stopPlaying();
                }
            }
            playAllB.setText("Play All");
        }
        startPlayingAll = !startPlayingAll;
    }
}
