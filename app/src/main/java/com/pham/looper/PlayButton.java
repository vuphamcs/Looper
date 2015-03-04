package com.pham.looper;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Environment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.util.ArrayList;

public class PlayButton extends Button {
    static ArrayList<PlayButton> playButtons = new ArrayList<PlayButton>();

    private static final String LOG_TAG = "PlayButton";
    private static final String external_storage_path = Environment.getExternalStorageDirectory().getAbsolutePath();

    private static int count = 0;
    private static boolean startPlayingAll = true;

    private int id;
    private boolean mStartPlaying;
    private String mFilename;

    MediaPlayer mPlayer;

    OnClickListener clicker = new OnClickListener() {
        public void onClick(View v) {
            onPlay(mStartPlaying);
        }
    };

    public PlayButton(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);

        setText("Start playing");
        setOnClickListener(clicker);

        mStartPlaying = true;

        id = ++count;
        mFilename = external_storage_path + "/loop_" + id + ".wav";

        mPlayer = null;
    }

    private void onPlay(boolean start) {
        if (start)
            startPlaying();
        else
            stopPlaying();
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
        mStartPlaying = !mStartPlaying;
        setText("Stop playing");
    }

    private void stopPlaying() {
        mPlayer.release();
        mPlayer = null;
        mStartPlaying = !mStartPlaying;
        setText("Start playing");
    }

    public static void playAll() {
        if (startPlayingAll) {
            for (PlayButton rb : playButtons) {
                if (!rb.mStartPlaying) {
                    rb.stopPlaying();
                }
                rb.startPlaying();
            }
        } else {
            for (PlayButton rb : playButtons) {
                if (!rb.mStartPlaying) {
                    rb.stopPlaying();
                }
            }
        }
        startPlayingAll = !startPlayingAll;
    }
}