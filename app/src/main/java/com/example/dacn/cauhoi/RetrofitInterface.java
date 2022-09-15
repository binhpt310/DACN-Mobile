package com.example.dacn.cauhoi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitInterface {
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();

    RetrofitInterface retrofitInterface = new Retrofit.Builder()
            .baseUrl("http://192.168.1.6:3000")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RetrofitInterface.class);

    @GET("/ques")
    Call<CauHoiTracNghiem> cauhoitracnghiem(@Query("_id") String _id,
                                            @Query("Question") String Question,
                                            @Query("Answer") String Answer);
}
