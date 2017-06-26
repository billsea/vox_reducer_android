package com.example.android.voxreducer_android;

import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Filter;
import android.widget.TextView;
import android.content.res.AssetManager;
import android.media.AudioManager;

public class MainActivity extends AppCompatActivity {

    static AssetManager assetManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        assetManager = getAssets();

        // initialize native audio system
        createEngine();

        int sampleRate = 0;
        int bufSize = 0;
        /*
         * retrieve fast audio path sample rate and buf size; if we have it, we pass to native
         * side to create a player with fast audio enabled [ fast audio == low latency audio ];
         * IF we do not have a fast audio path, we pass 0 for sampleRate, which will force native
         * side to pick up the 8Khz sample rate.
         */
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            AudioManager myAudioMgr = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
            String nativeParam = myAudioMgr.getProperty(AudioManager.PROPERTY_OUTPUT_SAMPLE_RATE);
            sampleRate = Integer.parseInt(nativeParam);
            nativeParam = myAudioMgr.getProperty(AudioManager.PROPERTY_OUTPUT_FRAMES_PER_BUFFER);
            bufSize = Integer.parseInt(nativeParam);
        }
        createBufferQueueAudioPlayer(sampleRate, bufSize);

        if(createReducer()){
            //reducer ready
            Log.d("tag","create reducer......");

            //create audio player
            //TODO: add .wav audio resource
            String  mp3 = "background.mp3";
            String wav = "hostile_long.wav";
            boolean created = createAssetAudioPlayer(assetManager, wav);

            //play audio
            setPlayingAssetAudioPlayer(true);
        }

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

    }

    // Used to load the 'reducer' library on application startup.
    static {
        System.loadLibrary("reducer");
    }

    //audio player
    public static native void createEngine();
    public static native void createBufferQueueAudioPlayer(int sampleRate, int samplesPerBuf);
    public static native boolean createAssetAudioPlayer(AssetManager assetManager, String filename);
    public static native void setPlayingAssetAudioPlayer(boolean isPlaying);

    //create reducer
    public static native boolean createReducer();
    public native String stringFromJNI();//testing only

}
