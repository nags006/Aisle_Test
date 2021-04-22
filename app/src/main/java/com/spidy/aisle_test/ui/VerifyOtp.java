package com.spidy.aisle_test.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.spidy.aisle_test.C8013E;
import com.spidy.aisle_test.R;
import com.spidy.aisle_test.network.Api;
import com.spidy.aisle_test.network.ApiEndpoint;
import com.spidy.aisle_test.network.model.PhoneLog;
import com.spidy.aisle_test.network.model.VerifyO;
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

public class VerifyOtp extends Fragment
{
    private Button btnsub;
    private EditText ednum;
    RelativeLayout relativeLayout;
    private TextView tvtime,tvnumber;
    String phnumber;
    private ViewHelp viewHelp;
    Call<VerifyO> call;
    public static VerifyOtp newInstance() { return new VerifyOtp(); }

    public static VerifyOtp newInstance(String number)
    {
        Bundle args = new Bundle();
        args.putSerializable("number", number);
        VerifyOtp verifyOtp = new VerifyOtp();
        verifyOtp.setArguments(args);
        return verifyOtp;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_verify, container, false);
        setView(view);
        setListner();
        return view;
    }

    private void setView(View view)
    {
        btnsub = (Button)view.findViewById(R.id.btnsub);
        ednum = (EditText)view.findViewById(R.id.edotp);
        tvtime = (TextView) view.findViewById(R.id.tvtime);
        tvnumber = (TextView) view.findViewById(R.id.tvnumber);
        relativeLayout = (RelativeLayout)view.findViewById(R.id.relative);
        viewHelp = new ViewHelp(getActivity());
        if (getArguments() != null && getArguments().containsKey("number"))
        {
            phnumber = "+91" + getArguments().getString("number");
            tvnumber.setText(phnumber);
        }

        new CountDownTimer(60000,1000)
        {
            @Override
            public void onTick(long l)
            {
                tvtime.setText("00:" + l / 1000);
            }

            @Override
            public void onFinish()
            {
                tvtime.setText("00:00");
            }
        }.start();
    }

    private void setListner()
    {
        btnsub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                try
                {
                    if(Utils.isNetworkAvailable(getActivity()))
                    {
                        viewHelp.showDialog();
                        if(!ednum.getText().toString().isEmpty() && ednum.getText().length() == 4)
                        {
                            call = networkCall(phnumber,ednum.getText().toString().trim());
                            call.enqueue(new Callback<VerifyO>()
                            {
                                @Override
                                public void onResponse(Call<VerifyO> call, Response<VerifyO> response)
                                {
                                    viewHelp.hideDialog();
                                    if(response.isSuccessful())
                                    {
                                        if(response.body().getStatus() != null && !response.body().getStatus().equals("null") )
                                        {
                                            PrefManager.setPrefLog("true");
                                            PrefManager.setPrefTokKey(response.body().getStatus());
                                            Intent intent=new Intent(getActivity(), C8013E.class);
                                            startActivity(intent);
                                            getActivity().finish();
                                        }
                                        else
                                        {
                                            Utils.showSnack("Invalid OTP !!",relativeLayout);
                                        }
                                    }
                                    else
                                    {
                                        Utils.showSnack("Failed !!",relativeLayout);
                                    }
                                }

                                @Override
                                public void onFailure(Call<VerifyO> call, Throwable t)
                                {
                                    viewHelp.hideDialog();
                                    Utils.showSnack("Failed !!",relativeLayout);
                                }
                            });
                        }
                        else
                        {
                            viewHelp.hideDialog();
                            Utils.showSnack("Please Enter otp",relativeLayout);
                        }
                    }
                    else
                    {
                        Utils.showSnack("No internet connection!",relativeLayout);
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    Utils.showSnack("Something went wrong.!!!",relativeLayout);
                }
            }
        });
    }

    public Call<VerifyO> networkCall(String number, String otp)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiEndpoint.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api service = retrofit.create(Api.class);
        call = service.verifyOtp(number,otp);
        return call;
    }


}
