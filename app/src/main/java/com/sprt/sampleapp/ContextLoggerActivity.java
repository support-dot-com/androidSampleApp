package com.sprt.sampleapp;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.sprt.android.esnuilib.initialize.NexusConnectSDK;

/**
 * Created by preethp on 9/26/2016.
 */
public class ContextLoggerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.context_logging_activity);

        initViews();
    }

    private void initViews(){

        mEditTextEmail = (EditText) findViewById(R.id.editText_email);
        mEditTextFirstName = (EditText) findViewById(R.id.editText_firstname);
        mEditTextLastName = (EditText) findViewById(R.id.editText_lastname);
        mEditTextContact = (EditText) findViewById(R.id.editText_number);
        mRadioBtnPortrait = (RadioButton) findViewById(R.id.radioButton_portrait);
        mRadioBtnLandscape = (RadioButton) findViewById(R.id.radioButton_landscape);
        mEditTextExtID = (EditText) findViewById(R.id.editText_externalID);

        mNextBtn = (Button) findViewById(R.id.btnNext);
        mNextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                logContextData();
                finish();
            }
        });
    }

    private void logContextData(){
        mEmailAddress = mEditTextEmail.getText().toString();
        mFirstName = mEditTextFirstName.getText().toString();

        //Use this API to pass your email id to SDK.
        NexusConnectSDK.setEmail(mEmailAddress);  //Set context individually

        //Use this API to pass your first name to SDK.
        NexusConnectSDK.setFirstName(mFirstName); //Set context individually

        //Or can be done simultaneously as well:
        mLastName = mEditTextLastName.getText().toString();
        mContact = mEditTextContact.getText().toString();
        mExtID = mEditTextExtID.getText().toString();

        if(mRadioBtnLandscape.isChecked())
            mOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        else if(mRadioBtnPortrait.isChecked())
            mOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

        //Use this API to pass your last name, contact and external ID to SDK.
        NexusConnectSDK.setLastName(mLastName).setPhone(mContact).setExternalId(mExtID);

        return;
    }

    private Button mNextBtn;

    private EditText mEditTextEmail;
    private String mEmailAddress;

    private EditText mEditTextFirstName;
    private String mFirstName;

    private EditText mEditTextLastName;
    private String mLastName;

    private EditText mEditTextContact;
    private String mContact;

    private RadioButton mRadioBtnPortrait;
    private RadioButton mRadioBtnLandscape;
    private int mOrientation;

    private EditText mEditTextExtID;
    private String mExtID;
}
