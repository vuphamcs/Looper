package com.example.pham.looper;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    ArrayList<RecordButton> recordButtons = new ArrayList<RecordButton>();
    ArrayList<PlayButton> playButtons = new ArrayList<PlayButton>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String external_storage_path = Environment.getExternalStorageDirectory().getAbsolutePath();
        String path_1 = external_storage_path + "/loop_1";
        String path_2 = external_storage_path + "/loop_2";
        String path_3 = external_storage_path + "/loop_3";

        RecordButton recordB1 = (RecordButton) findViewById(R.id.recordB1);
        RecordButton recordB2 = (RecordButton) findViewById(R.id.recordB2);
        RecordButton recordB3 = (RecordButton) findViewById(R.id.recordB3);

        recordB1.mFilename = path_1;
        recordB2.mFilename = path_2;
        recordB3.mFilename = path_3;

        recordButtons.add(recordB1);
        recordButtons.add(recordB2);
        recordButtons.add(recordB3);

        PlayButton playB1 = (PlayButton) findViewById(R.id.playB1);
        PlayButton playB2 = (PlayButton) findViewById(R.id.playB2);
        PlayButton playB3 = (PlayButton) findViewById(R.id.playB3);

        playB1.mFilename = path_1;
        playB2.mFilename = path_2;
        playB3.mFilename = path_3;

        playButtons.add(playB1);
        playButtons.add(playB2);
        playButtons.add(playB3);
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

        for (RecordButton rb : recordButtons) {
            if (rb.mRecorder != null) {
                rb.mRecorder.release();
                rb.mRecorder = null;
            }
        }

        for (PlayButton pb : playButtons) {
            if (pb.mPlayer != null) {
                pb.mPlayer.release();
                pb.mPlayer = null;
            }
        }

    }
}
