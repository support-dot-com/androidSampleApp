package com.example.preethp.sampleapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sprt.android.esnuilib.SprtApplication;
import com.sprt.android.esnuilib.initialize.NexusConnectSDK;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Support.com Sample App");
        initButtons();
    }

    private void initButtons(){

        mSelfSupport = (Button) findViewById(R.id.btnSelfHelp);
        mSelfSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Use this API to show Self-help using helpId.
                NexusConnectSDK.showSelfHelpForId( MainActivity.this, "Thermostat_Embed", null, -1);
            }
        });

        mSelfSupportByTags = (Button) findViewById(R.id.btnSelfHelpByTags);
        mSelfSupportByTags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NexusConnectSDK.showSelfHelpForTags(MainActivity.this, new String[] {"showDetails", "free-ptawin-support"}, null, -1);
            }
        });

        mLiveHelp = (Button) findViewById(R.id.btnLiveHelp);
        mLiveHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Use this API to show Self-help using Tags
                NexusConnectSDK.showLiveHelp(MainActivity.this);
            }
        });

        mContextLog = (Button) findViewById((R.id.btnContextLog));
        mContextLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ContextLog.class);
                startActivity(intent);
            }
        });

        mLogData = (Button) findViewById((R.id.btnLog));
        mLogData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, log_data_layout.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dash_board_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.change_jwt) {
            Helper.addJWTToken(getApplicationContext());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private Button mSelfSupport;
    private Button mSelfSupportByTags;
    private Button mLiveHelp;
    private Button mContextLog;
    private Button mLogData;

}

