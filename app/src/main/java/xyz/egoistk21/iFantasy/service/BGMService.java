package xyz.egoistk21.iFantasy.service;

import android.app.IntentService;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;

import xyz.egoistk21.iFantasy.R;

public class BGMService extends IntentService implements MediaPlayer.OnPreparedListener {

    private static final String TAG = BGMService.class.getName();
    private static final String URI_PREFIX = "android.resource://xyz.egoistk21.iFantasy/";
    private MediaPlayer mmp;

    public BGMService() {
        super("BGMService");
    }

    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate: ");
        super.onCreate();
        mmp = new MediaPlayer();
        mmp.setOnPreparedListener(this);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        assert intent != null;
        try {
            switch (intent.getStringExtra("name")) {
                case "cant_stop":
                    Log.i(TAG, "onHandleIntent: ");
                    mmp.setDataSource(BGMService.this, Uri.parse(URI_PREFIX + R.raw.cant_stop));
                    mmp.prepareAsync();
                    break;
                default:
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        Log.i(TAG, "onPrepared: ");
        mediaPlayer.start();
    }
}
