package com.spidy.aisle_test;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.spidy.aisle_test.network.Api;
import com.spidy.aisle_test.network.ApiEndpoint;
import com.spidy.aisle_test.network.model.ResponseProfile;
import com.spidy.aisle_test.ui.Home;
import com.spidy.aisle_test.ui.Matches;
import com.spidy.aisle_test.ui.Notes;
import com.spidy.aisle_test.ui.Profile;
import com.spidy.aisle_test.utils.PrefManager;
import com.spidy.aisle_test.utils.Utils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class C8013E extends AppCompatActivity
{
    Call<ResponseProfile> call;
    BottomNavigationView bottomNavigationView;
    ResponseProfile responseProfile;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setView();
        setListner();
        fetchUser();
    }

    public Call<ResponseProfile> networkCall()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiEndpoint.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api service = retrofit.create(Api.class);
        call = service.fetchUser(PrefManager.getPrefTokKey());
        return call;
    }

    public void fetchUser()
    {
        if(Utils.isNetworkAvailable(this))
        {
            call = networkCall();
            call.enqueue(new Callback<ResponseProfile>()
            {
                @Override
                public void onResponse(Call<ResponseProfile> call, Response<ResponseProfile> response)
                {
                    if(response.isSuccessful())
                    {
                        setResponse(response.body());
                        bottomNavigationView.getOrCreateBadge(R.id.action_matches).setNumber(responseProfile.getLikes().getLikesReceivedCount());
                        bottomNavigationView.getOrCreateBadge(R.id.action_notes).setNumber(responseProfile.getLikes().getLikesReceivedCount());
                        getSupportFragmentManager()
                                .beginTransaction()
                                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                .replace(R.id.signingContainer, Home.newInstance(responseProfile), "C4472s")
                                .commit();
                    }
                    else
                    {
                        Toast.makeText(C8013E.this, "Please try after sometime!!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseProfile> call, Throwable t)
                {
                    Toast.makeText(C8013E.this, "Something went wrong !!", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else
        {
            Toast.makeText(C8013E.this, "No Internet connection !", Toast.LENGTH_SHORT).show();
        }

    }

    public void setView()
    {
        bottomNavigationView = (BottomNavigationView)findViewById(R.id.btnnav);
    }

    public void setListner()
    {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.action_discover :   getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .replace(R.id.signingContainer, Home.newInstance(responseProfile), "C4472s")
                            .commit();
                        break;

                    case R.id.action_notes :   getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .replace(R.id.signingContainer, Notes.newInstance(), "C4472s")
                            .commit();
                        break;

                    case R.id.action_matches :   getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .replace(R.id.signingContainer, Matches.newInstance(), "C4472s")
                            .commit();
                        break;

                    case R.id.action_profile :   getSupportFragmentManager()
                            .beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                            .replace(R.id.signingContainer, Profile.newInstance(), "C4472s")
                            .commit();
                        break;
                }
                return true;
            }
        });

    }

    public void setResponse(ResponseProfile responseProfile) {
        this.responseProfile = responseProfile;
    }
}
