package com.example.android.voxreducer_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        if(createReducer()){
            //reducer ready

            //create audio player
            //TODO: add .wav audio resource
            boolean created = createAssetAudioPlayer(assetManager, "background.mp3");

            //play audio
            setPlayingAssetAudioPlayer(true);
        }

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

    }



    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("reducer");
    }

    public native String stringFromJNI();

    //create reducer
    public static native boolean createReducer();

    //audio player
    public static native boolean createAssetAudioPlayer(AssetManager assetManager, String filename);
    public static native void setPlayingAssetAudioPlayer(boolean isPlaying);




}
