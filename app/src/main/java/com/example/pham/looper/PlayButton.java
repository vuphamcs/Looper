package com.example.pham.looper;

import android.content.Context;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

class PlayButton extends Button {
    private static final String LOG_TAG = "PlayButton";
    MediaPlayer mPlayer;
    boolean mStartPlaying;
    OnClickListener clicker = new OnClickListener() {
        public void onClick(View v) {
            onPlay(mStartPlaying);
        }
    };
    String mFilename;

    public PlayButton(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        setText("Start playing");
        setOnClickListener(clicker);
        mPlayer = null;
        mStartPlaying = true;
        mFilename = null;
    }

    private void onPlay(boolean start) {
        if (start) {
            startPlaying();
            setText("Stop playing");
        } else {
            stopPlaying();
            setText("Start playing");
        }
        mStartPlaying = !mStartPlaying;
    }

    private void startPlaying() {
        mPlayer = new MediaPlayer();
        try {
            mPlayer.setDataSource(mFilename);
            mPlayer.setLooping(true);
            mPlayer.prepare();
            mPlayer.start();
        } catch (IOException e) {
            Log.e(LOG_TAG, "prepare() failed");
        }
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
    }
}