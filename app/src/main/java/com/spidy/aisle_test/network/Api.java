package com.spidy.aisle_test.network;

import com.spidy.aisle_test.network.model.PhoneLog;
import com.spidy.aisle_test.network.model.ResponseProfile;
import com.spidy.aisle_test.network.model.VerifyO;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface Api
{
    @FormUrlEncoded
    @POST("phone_number_login")
    Call<PhoneLog> phoneLogin(
            @Field("number") String number);

    @FormUrlEncoded
    @POST("verify_otp")
    Call<VerifyO> verifyOtp(
            @Field("number") String number,
            @Field("otp") String otp);


    @GET("test_profile_list")
    Call<ResponseProfile> fetchUser(@Header("Authorization") String token);

}
