package xyz.egoistk21.iFantasy.service;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import xyz.egoistk21.iFantasy.R;

public class BGMService extends IntentService {

    private static final String TAG = BGMService.class.getName();
    private static final String URI_PREFIX = "android.resource://xyz.egoistk21.iFantasy/";
    private MediaPlayer mmp;

    public BGMService() {
        super("BGMService");
    }

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate");
        super.onCreate();
        mmp = new MediaPlayer();
        mmp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.d(TAG, "onPrepared");
                mp.start();
            }
        });
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        try {
            if (mmp.isPlaying()) {
                mmp.stop();
                mmp.release();
                mmp = new MediaPlayer();
                mmp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mp) {
                        Log.d(TAG, "onPrepared");
                        mp.start();
                    }
                });
            }
            switch (intent.getStringExtra("name")) {
                case "cant_stop":
                    Log.d(TAG, "onHandleIntent");
                    mmp.setDataSource(this, Uri.parse(URI_PREFIX + R.raw.cant_stop));
                    mmp.prepareAsync();
                    break;
                default:
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }
}
