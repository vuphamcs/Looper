package com.pham.looper;

import android.content.Context;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by Pham on 3/2/15.
 */

public class RecordButton extends Button {
    static ArrayList<RecordButton> recordButtons = new ArrayList<RecordButton>();

    private static final String LOG_TAG = "RecordButton";
    private static final String external_storage_path = Environment.getExternalStorageDirectory().getAbsolutePath();

    private static int count = 0;

    private int id;
    private String mFilename;

    ExtAudioRecorder extAudioRecorder;
    boolean mStartRecording;

    View.OnClickListener clicker = new OnClickListener() {
        public void onClick(View v) {
            onRecord(mStartRecording);

        }
    };

    public RecordButton(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);

        setText("Start recording");
        setOnClickListener(clicker);
        mStartRecording = true;

        id = ++count % 5;
        mFilename = external_storage_path + "/loop_" + id + ".wav";

        extAudioRecorder = null;

        if(recordButtons.size() == 5)
            recordButtons.clear();

        recordButtons.add(this);
    }

    private void onRecord(boolean start) {
        if (start)
            startRecording();
        else
            stopRecording();
    }

    private void startRecording() {
        extAudioRecorder = ExtAudioRecorder.getInstanse(false); // Uncompressed recording (WAV)
        extAudioRecorder.setOutputFile(mFilename);
        extAudioRecorder.prepare();

        extAudioRecorder.start();
        setText("Stop recording");

        mStartRecording = !mStartRecording;


    }

    public void stopRecording() {
        extAudioRecorder.stop();
        extAudioRecorder.release();
        mStartRecording = !mStartRecording;
        setText("Start recording");
    }
}