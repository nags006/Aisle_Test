package com.spidy.aisle_test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.spidy.aisle_test.ui.Login;
import com.spidy.aisle_test.ui.VerifyOtp;
import com.spidy.aisle_test.utils.PrefManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(PrefManager.getPrefLog().equals("true"))
        {
            Intent intent=new Intent(this, C8013E.class);
            startActivity(intent);
            finish();
        }
        else
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.signingContainer, Login.newInstance(), "MainActivity")
                    .commit();
        }

    }
}