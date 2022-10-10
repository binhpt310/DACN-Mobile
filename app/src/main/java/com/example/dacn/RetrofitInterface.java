package com.example.dacn;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.cauhoi.CauHoiTracNghiem;
import com.example.dacn.dangnhap.DangNhapResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    RetrofitInterface retrofitInterface = new Retrofit.Builder()
            //.baseUrl("http://192.168.1.5:3000")
            .baseUrl("http://172.31.10.125:3000")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RetrofitInterface.class);

    @POST("/login")
    Call<DangNhapResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup (@Body HashMap<String, String> map);

    @POST("/ques")
    Call<List<CauHoiTracNghiem>> getCauHoiTracNghiem(@Body HashMap<String, String> map);

    @POST("/sendOTP")
    Call<Void> checkemail(@Body HashMap<String, String> map);

    @POST("/verifyOTP")
    Call<Void> checkOTP (@Body HashMap<String, String> map);

    @POST("/changepass")
    Call<Void> changePassword (@Body HashMap<String, String> map);

    @POST("/changeinfo")
    Call<Void> changeInfo (@Body HashMap<String, String> map);

    @POST("/list")
    Call<List<BoDe>> getBoDe (@Body HashMap<String, String> map);
}
