package com.pham.looper;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String external_storage_path = Environment.getExternalStorageDirectory().getAbsolutePath();

        RecordButton recordB1 = (RecordButton) findViewById(R.id.recordB1);
        RecordButton recordB2 = (RecordButton) findViewById(R.id.recordB2);
        RecordButton recordB3 = (RecordButton) findViewById(R.id.recordB3);
        RecordButton recordB4 = (RecordButton) findViewById(R.id.recordB4);
        RecordButton recordB5 = (RecordButton) findViewById(R.id.recordB5);

        RecordButton.recordButtons.add(recordB1);
        RecordButton.recordButtons.add(recordB2);
        RecordButton.recordButtons.add(recordB3);
        RecordButton.recordButtons.add(recordB4);
        RecordButton.recordButtons.add(recordB5);

        PlayButton playB1 = (PlayButton) findViewById(R.id.playB1);
        PlayButton playB2 = (PlayButton) findViewById(R.id.playB2);
        PlayButton playB3 = (PlayButton) findViewById(R.id.playB3);
        PlayButton playB4 = (PlayButton) findViewById(R.id.playB4);
        PlayButton playB5 = (PlayButton) findViewById(R.id.playB5);

        PlayButton.playButtons.add(playB1);
        PlayButton.playButtons.add(playB2);
        PlayButton.playButtons.add(playB3);
        PlayButton.playButtons.add(playB4);
        PlayButton.playButtons.add(playB5);

        final Button playAllB = (Button) findViewById(R.id.playAllB);
        playAllB.setText("Play All");
        playAllB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayButton.playAll();
                if (playAllB.getText().equals("Play All"))
                    playAllB.setText("Stop All");
                else
                    playAllB.setText("Play All");
            }
        });
    }

    public void onRestoreInstanceState(Bundle savedInstanceState) {
        // Always call the superclass so it can restore the view hierarchy
        super.onRestoreInstanceState(savedInstanceState);
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
            if (rb.extAudioRecorder != null) {
                rb.extAudioRecorder.release();
            }
        }

        for (PlayButton pb : PlayButton.playButtons) {
            if (pb.mPlayer != null) {
                pb.mPlayer.release();
                pb.mPlayer = null;
            }
        }
    }
}
