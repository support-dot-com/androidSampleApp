package com.example.preethp.sampleapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import com.sprt.android.esnuilib.initialize.NexusConnectSDK;

/**
 * Created by preethp on 9/27/2016.
 */
public class Helper {



    static public final String PREFS_FILENAME = "sample_app_prefs";
    private static final String JWT_TOKEN = "jwt_token";

    public static void addJWTToken(Context context) {

        mContext = context;

        AlertDialog.Builder dialog = new AlertDialog.Builder(mContext).setTitle("Add JWT token");
        final EditText editText = new EditText(mContext);
        editText.setHint("JWT Token");
        dialog.setView(editText);
        dialog.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                String token = editText.getText().toString().trim();
                if (!TextUtils.isEmpty(token)) {
                    // Temporary Code
                    String jwtToken = editText.getText().toString().trim();
                    NexusConnectSDK.initialize(token);
                    mContext.getSharedPreferences(PREFS_FILENAME, Activity.MODE_PRIVATE).edit().putString(JWT_TOKEN, token).commit();
                } else
                    Toast.makeText(mContext, "Error: Enter valid token", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }

        });
        dialog.show();
    }

    public static String getJWTToken(Context context){
        return context.getSharedPreferences(PREFS_FILENAME, Activity.MODE_PRIVATE).getString(JWT_TOKEN
                ,"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ0IjoibXljb21wYW55IiwiZCI6InJ3Y3FhLmZpcmViZXQuZGV2LnN1cHBvcnQuY29tIiwicHJtIjpbImxvZzoqIiwic2Vzc2lvbjpjb25uZWN0Il0sImp0aSI6IjE2ZGZhZGU2LTFhZTMtNDFkZi05NTlkLTIzZGUwZTkxMTljYiIsImFwcCI6InRlc3RfYXBwIiwidmVyIjoiMS4wIiwiaWF0IjoxNDc0OTU5OTg0LCJhdWQiOlsiYXBwIl0sImlzcyI6IlN1cHBvcnQuY29tIn0.43aYxgWvoO2rOXlSWZSynUqR2107_swOryTAM2yu83I");
    }

    private static Context mContext;
}
