package com.example.dacn;

import android.widget.ArrayAdapter;

import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.cauhoi.CauHoiTracNghiem;
import com.example.dacn.cauhoi.Result;
import com.example.dacn.dangnhap.DangNhapResult;
import com.example.dacn.hoanthanhbai.NdungCardModel;
import com.example.dacn.mucluc.ThayDoiThongTin.dulieu_thaydoi;
import com.example.dacn.search.DataSearch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;

public interface RetrofitInterface {

    Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();

    RetrofitInterface retrofitInterface = new Retrofit.Builder()
            .baseUrl("https://newdacn.onrender.com/")
            .addConverterFactory(new NullOnEmptyConverterFactory())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RetrofitInterface.class);

    @POST("/login")
    Call<DangNhapResult> executeLogin(@Body HashMap<String, String> map);

    @POST("/signup")
    Call<Void> executeSignup(@Body HashMap<String, String> map);

    @POST("/ques")
    Call<List<CauHoiTracNghiem>> getCauHoiTracNghiem(@Body HashMap<String, String> map);

    @POST("/sendOTP")
    Call<Void> checkemail(@Body HashMap<String, String> map);

    @POST("/verifyOTP")
    Call<Void> checkOTP(@Body HashMap<String, String> map);

    @POST("/changepass")
    Call<Void> changePassword(@Body HashMap<String, String> map);

    @POST("/changeinfo")
    Call<Void> changeInfo(@Body HashMap<String, String> map);

    @POST("/list")
    Call<List<BoDe>> getBoDe(@Body HashMap<String, String> map);

    @Multipart
    @POST("/changeinfo2")
    Call<dulieu_thaydoi> changeInfo2(@Part("email") RequestBody email,
                                     @Part MultipartBody.Part image,
                                     @Part("tenngdung") RequestBody tenngdung,
                                     @Part("matkhau") RequestBody matkhau);

    @POST("/search")
    Call<ArrayList<DataSearch>> getSearch(@Body HashMap<String, String> map);

    @POST("/saveresult")
    Call<Result> saveResult(@Body Result result);

}