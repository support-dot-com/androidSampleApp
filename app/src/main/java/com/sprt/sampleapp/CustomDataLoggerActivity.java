package com.sprt.sampleapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.sprt.android.esnuilib.initialize.NexusConnectSDK;

/**
 * Created by preethp on 9/26/2016.
 */
public class CustomDataLoggerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.custom_data_layout);

        initViews();
    }

    private void initViews(){
        mEditTextKey1 = (EditText) findViewById(R.id.editText_rowone_key);
        mEditTextValue1 = (EditText) findViewById(R.id.editText_rowone_value);
        mEditTextKey2 = (EditText) findViewById(R.id.editText_rowtwo_key);
        mEditTextValue2 = (EditText) findViewById(R.id.editText_rowtwo_value);

        mButton = (Button) findViewById(R.id.btnSubmit);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customDataKey = mEditTextKey1.getText().toString();
                String customDataValue = mEditTextValue1.getText().toString();
                String customDataKey1 = mEditTextKey2.getText().toString();
                String customDataValue1= mEditTextValue2.getText().toString();

                NexusConnectSDK.setData(customDataKey, customDataValue);
                NexusConnectSDK.setData(customDataKey1, customDataValue1);

                AlertDialog dialog = new AlertDialog.Builder(CustomDataLoggerActivity.this).create();
                dialog.setTitle("Your data has been succesffuly logged");
                dialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                                Intent intent = new Intent(CustomDataLoggerActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                dialog.show();
            }
        });

    }

    private EditText mEditTextKey1;
    private EditText mEditTextValue1;
    private EditText mEditTextKey2;
    private EditText mEditTextValue2;
    private Button mButton;
}
