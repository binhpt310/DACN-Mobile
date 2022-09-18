package com.example.dacn.Api;

import com.example.dacn.cauhoi.CauHoiTracNghiem;
import com.example.dacn.dangnhap.DangNhapResult;

import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RetrofitInterface {
    @POST("/login")
    Call<DangNhapResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup (@Body HashMap<String, String> map);

    @GET("/ques")
    Call<List<CauHoiTracNghiem>> getCauHoiTracNghiem();

}
