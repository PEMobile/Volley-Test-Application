package com.example.listeners;

/**
 * Created by mattschoen on 4/14/14.
 */
import java.util.Observable;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.volley.Response.ErrorListener;
import android.volley.VolleyError;

public class WebServiceErrorListener extends Observable implements ErrorListener {

    private static final String LOG_TAG="WebServiceErrorListener";
    private ProgressDialog mPd=null;
    private String mErrorMessage=null;

    @Override
    public void onErrorResponse(VolleyError error) {
        Log.e(LOG_TAG, "Web Service Error Response: " + error.getMessage());
        mErrorMessage=error.getMessage();

        if(error.getCause() != null) {
            Log.e(LOG_TAG, error.getLocalizedMessage());
        }

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this.mPd.getContext());
        alertDialogBuilder.setTitle("Error");

        // set dialog message
        alertDialogBuilder
                .setMessage(mErrorMessage)
                .setCancelable(false)
                .setPositiveButton("Continue",new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();

        // notify the observer
        this.setChanged();
        this.notifyObservers();
    }
}
