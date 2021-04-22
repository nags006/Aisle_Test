package com.spidy.aisle_test.utils;


import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.widget.ProgressBar;

import com.spidy.aisle_test.R;


public class ViewHelp
{
    private Context context;
    private Dialog dialog;
    private boolean isShowing = false;
    public ViewHelp(Context context)
    {
        this.context = context;
    }


    public void hideDialog(){
        dialog.dismiss();
    }

   public void showDialog()
    {
        dialog  = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.view_dialog);
        ProgressBar progressBar = (ProgressBar)dialog.findViewById(R.id.progressBar);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

}
