package com.example.android.voxreducer_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Filter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("reducer");

    }

    public native String stringFromJNI();

    //create reducer
    public static native boolean createReducer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Example of a call to a native method
        TextView tv = (TextView) findViewById(R.id.sample_text);
        tv.setText(stringFromJNI());

        if(createReducer()){
            //reducer ready
        }


    }







}
