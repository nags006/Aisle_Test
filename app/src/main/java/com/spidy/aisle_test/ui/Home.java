package com.spidy.aisle_test.ui;

import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.spidy.aisle_test.R;
import com.spidy.aisle_test.network.model.ResponseProfile;
import com.spidy.aisle_test.utils.ViewHelp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import retrofit2.Call;

public class Home extends Fragment
{
    Call<ResponseProfile> call;
    ResponseProfile responseProfile;
    ImageView proOne,proTwo;
    private ViewHelp viewHelp;
    public static Home newInstance() { return new Home(); }

    public static Home newInstance(ResponseProfile responseProfile)
    {
       Home home = new Home();
       home.setResponse(responseProfile);
       return home;
    }

    public void setResponse(ResponseProfile responseProfile) {
        this.responseProfile = responseProfile;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_discover, container, false);
        setView(view);
        return view;
    }

    public void setView(View view)
    {
        proOne = (ImageView)view.findViewById(R.id.proOne);
        proTwo = (ImageView)view.findViewById(R.id.proTwo);
        viewHelp = new ViewHelp(getActivity());
        viewHelp.showDialog();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        viewHelp.hideDialog();
    }
}
