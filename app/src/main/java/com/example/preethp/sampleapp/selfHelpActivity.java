package com.example.preethp.sampleapp;

import android.app.Activity;
import android.os.Bundle;

import com.sprt.android.esnuilib.initialize.NexusConnectSDK;

/**
 * Created by preethp on 9/18/2016.
 */
public class selfHelpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NexusConnectSDK.showLiveHelp(this);
    }
}
