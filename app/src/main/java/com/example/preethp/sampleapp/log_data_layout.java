package com.example.preethp.sampleapp;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.sprt.android.esnuilib.initialize.NexusConnectSDK;
import com.sprt.android.esnuilib.utility.Util;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by preethp on 9/26/2016.
 */
public class log_data_layout extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.log_data_layout);
        initViews();
    }

    private void initViews(){

        mTextViewDisplay = (TextView) findViewById(R.id.textViewDisplay);

        mLogDevData = (Button) findViewById(R.id.topButton);
        mLogDevData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextViewDisplay.setVisibility(View.VISIBLE);
                logDeviceInfo();
                updateTextView();
                mLogDevData.setEnabled(false);
            }
        });

        mLogAppData = (Button) findViewById(R.id.bottomButton);
        mLogAppData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTextViewDisplay.setVisibility(View.VISIBLE);
                logAppData();
                updateTextViewWithAppData();
                mLogAppData.setEnabled(false);
            }
        });

        Button logButton = (Button)findViewById(R.id.btnLogCustomData);
        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(log_data_layout.this, custom_data_layout.class);
                startActivity(intent);
            }
        });

    }

    private void updateTextViewWithAppData(){
        mTextViewDisplay.setText("The following data has been logged: \n \n");
        for(Map.Entry<String,String> entry : mAppInfoMap.entrySet()){
            mTextViewDisplay.append(entry.getKey() + ":" + "\t" + entry.getValue() + "\n");
        }
    }

    private void updateTextView(){

        mTextViewDisplay.setText("The following data has been logged: \n \n");
        for(Map.Entry<String,String> entry : mdevInfoMap.entrySet()){
            mTextViewDisplay.append(entry.getKey() + ":" + "\t" + entry.getValue() + "\n");
        }
    }

    private void logAppData(){

        mIsAppInfoLogged = true;
        mAppInfoMap.put("Name of the app", getString(R.string.app_name));
        try {
            String versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
            mAppInfoMap.put("App Version", versionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mAppInfoMap.put("Has user signed in", "No");
        mAppInfoMap.put("Account expired", "No");
        mAppInfoMap.put("Parental control", "Enabled");
        mAppInfoMap.put("Profiles", "1");

        //Use this API to log device information. Logged data will be shown under device detail section on agent side.
        NexusConnectSDK.logDevice("App Info", mAppInfoMap);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mIsDevInfoLogged)
            mLogDevData.setEnabled(false);
        if(mIsAppInfoLogged)
            mLogAppData.setEnabled(false);
    }

    private void logDeviceInfo(){

        String strConnType = "DISCONNECTED";
        mIsDevInfoLogged = true;

        mdevInfoMap.put("Phone Model", android.os.Build.MANUFACTURER + " " + android.os.Build.MODEL);
        mdevInfoMap.put("Android Version", android.os.Build.VERSION.RELEASE);

        try {
            boolean bIsMobileDataEnabled = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();

            if(null != activeNetInfo) {
                if (activeNetInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                    strConnType = "Mobile Network";
                }
                else if (activeNetInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                    strConnType = "WIFI";
                }
            }
            mdevInfoMap.put("Connected through", strConnType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Use this API to log device information. Logged data will be shown under device detail section on agent side.
        NexusConnectSDK.logDevice("Device Info", mdevInfoMap);
    }

    private Button mLogDevData;
    private Button mLogAppData;
    private TextView mTextViewDisplay;
    HashMap<String, String> mdevInfoMap = new HashMap<String, String>();
    HashMap<String, String> mAppInfoMap = new HashMap<String, String>();
    private boolean mIsDevInfoLogged = false;
    private boolean mIsAppInfoLogged = false;
}
