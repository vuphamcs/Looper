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
    private boolean mStartRecording;
    private String mFilename;

    ExtAudioRecorder extAudioRecorder;

    View.OnClickListener clicker = new OnClickListener() {
        public void onClick(View v) {
            onRecord(mStartRecording);
            if (mStartRecording) {
                setText("Stop recording");
            } else {
                setText("Start recording");
            }
            mStartRecording = !mStartRecording;
        }
    };

    public RecordButton(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        setText("Start recording");
        setOnClickListener(clicker);
        mStartRecording = true;
        extAudioRecorder = null;
        id = ++count;
        mFilename = external_storage_path + "/loop_" + id + ".wav";
    }

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void startRecording() {
        extAudioRecorder = ExtAudioRecorder.getInstanse(false); // Uncompressed recording (WAV)
        extAudioRecorder.setOutputFile(mFilename);
        extAudioRecorder.prepare();
        extAudioRecorder.start();
    }

    private void stopRecording() {
        extAudioRecorder.stop();
        extAudioRecorder.release();
    }
}