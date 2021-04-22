package com.spidy.aisle_test.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.spidy.aisle_test.R;
import com.spidy.aisle_test.network.Api;
import com.spidy.aisle_test.network.ApiEndpoint;
import com.spidy.aisle_test.network.model.PhoneLog;
import com.spidy.aisle_test.utils.PrefManager;
import com.spidy.aisle_test.utils.Utils;
import com.spidy.aisle_test.utils.ViewHelp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends Fragment
{
    private Button btnSub;
    private EditText edNumber,edphocode;
    RelativeLayout relativeLayout;
    Call<PhoneLog> call;
    private ViewHelp viewHelp;
    public static Login newInstance() { return new Login(); }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        setView(view);
        setListner();
        return view;
    }

    private void setView(View view)
    {
        btnSub = (Button)view.findViewById(R.id.btnsub);
        edNumber = (EditText)view.findViewById(R.id.edphone);
        edphocode = (EditText)view.findViewById(R.id.edphocode);
        relativeLayout = (RelativeLayout)view.findViewById(R.id.relative);
        viewHelp = new ViewHelp(getActivity());
    }

    private void setListner()
    {
        try
        {
            btnSub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view)
                {
                    if(Utils.isNetworkAvailable(getActivity()))
                    {
                        viewHelp.showDialog();
                        call = networkCall();
                        if(call == null)
                        {
                            viewHelp.hideDialog();
                            Utils.showSnack("Please Enter Your Phone Number",relativeLayout);
                        }
                        else
                        {
                            call.enqueue(new Callback<PhoneLog>()
                            {
                                @Override
                                public void onResponse(Call<PhoneLog> call, Response<PhoneLog> response)
                                {
                                    viewHelp.hideDialog();
                                    if(response.isSuccessful())
                                    {
                                        if(response.body().getStatus().equals("true"))
                                        {
                                            getActivity().getSupportFragmentManager()
                                                    .beginTransaction()
                                                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                                                    .replace(R.id.signingContainer, VerifyOtp.newInstance(edNumber.getText().toString()), "C4472s")
                                                    .commit();
                                        }
                                        else
                                        {
                                            Utils.showSnack("Mobile number does not exist",relativeLayout);
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<PhoneLog> call, Throwable t)
                                {
                                    viewHelp.hideDialog();
                                    Utils.showSnack("Failed !!",relativeLayout);
                                }
                            });
                        }

                    }
                    else
                    {
                        Utils.showSnack("No internet connection!",relativeLayout);
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Utils.showSnack("Something went wrong ..!!",relativeLayout);
        }
    }

    public Call<PhoneLog> networkCall()
    {
        if(!edNumber.getText().toString().isEmpty() && edNumber.getText().length() == 10)
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(ApiEndpoint.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Api service = retrofit.create(Api.class);
            call = service.phoneLogin(edphocode.getText().toString() + edNumber.getText().toString());
            return call;
        }
        else
        {
            return null;
        }
    }

}
