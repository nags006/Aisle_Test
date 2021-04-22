package com.spidy.aisle_test.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.View;
import android.widget.RelativeLayout;

import com.google.android.material.snackbar.Snackbar;

public class Utils
{
    public static boolean isNetworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public static void showSnack(String message, RelativeLayout relativeLayout)
    {
         Snackbar snackbar;
         snackbar = Snackbar.make(relativeLayout, message, Snackbar.LENGTH_LONG)
                .setAction("UNDO", new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {

                    }
                });

        snackbar.show();
    }

}
