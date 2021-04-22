package com.spidy.aisle_test;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.spidy.aisle_test.utils.PrefManager;
import com.spidy.aisle_test.utils.WaveDrawable;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class C9354S  extends AppCompatActivity
{
 private ImageView imageView;
 Handler handler;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView = (ImageView)findViewById(R.id.imghrt);
        PrefManager.init(this);
        WaveDrawable chromeWave = new WaveDrawable(C9354S.this, R.drawable.heart);
        chromeWave.setIndeterminate(true);
        imageView.setImageDrawable(chromeWave);
        handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(PrefManager.getPrefLog().equals("true"))
                {
                    Intent intent=new Intent(C9354S.this, C8013E.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Intent intent=new Intent(C9354S.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },3000);
    }

}
