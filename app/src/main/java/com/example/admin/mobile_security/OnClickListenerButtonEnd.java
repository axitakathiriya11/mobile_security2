package com.example.admin.mobile_security;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

public class OnClickListenerButtonEnd implements OnClickListener {
    final String TAG = "OnClickListenerButtonEnd.java";

    location loc;
    private View view;
    Context context = view.getContext();

    @Override
    public void onClick(View view) {

        Log.e(TAG, "Ended getting user location.");

        // to get the context and main activity
        this.loc = ((location) context);

        // enable the START button, disable the STOP button
        loc.buttonStart.setEnabled(true);
        loc.buttonStop.setEnabled(false);

        // stop the listener
        loc.locationHelper.stopGettingLocationUpdates();

    }

}