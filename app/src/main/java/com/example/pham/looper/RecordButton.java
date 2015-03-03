package com.example.pham.looper;

import android.content.Context;
import android.media.MediaRecorder;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

/**
 * Created by Pham on 3/2/15.
 */

class RecordButton extends Button {
    private static final String LOG_TAG = "RecordButton";
    MediaRecorder mRecorder;
    boolean mStartRecording;
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
    String mFilename;

    public RecordButton(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        setText("Start recording");
        setOnClickListener(clicker);
        mStartRecording = true;
        mRecorder = null;
        mFilename = null;
    }

    private void onRecord(boolean start) {
        if (start) {
            startRecording();
        } else {
            stopRecording();
        }
    }

    private void startRecording() {
        mRecorder = new MediaRecorder();
        mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AAC);
        //mRecorder.setAudioEncoder(MediaRecorder.getAudioSourceMax());
        mRecorder.setAudioEncodingBitRate(16);
        mRecorder.setAudioSamplingRate(44100);
        mRecorder.setOutputFile(mFilename);

        try {
            mRecorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }

        mRecorder.start();
    }

    private void stopRecording() {
        mRecorder.stop();
        mRecorder.release();
        mRecorder = null;
    }
}