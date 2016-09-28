package com.sprt.sampleapp;

import com.sprt.android.esnuilib.SprtApplication;
import com.sprt.android.esnuilib.initialize.NexusConnectSDK;

/**
 * Created by preethp on 9/18/2016.
 */
public class SampleAppApplication extends SprtApplication {

    @Override
    public void onCreate() {
        super.onCreate();

        String JWTToken = Helper.getJWTToken(this);

        //Initialize SDK by passing valid JWT Token. This method needs to be called only once by the application.
        // You should invoke initialize API from onCreate method of your Application class.
        NexusConnectSDK.initialize(JWTToken);
    }
}
