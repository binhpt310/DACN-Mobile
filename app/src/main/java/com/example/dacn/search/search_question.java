package com.example.dacn.search;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.mucluc.Notification.CreateNotification;
import com.example.dacn.mucluc.Notification.NotificationsActivity;
import com.example.dacn.trangchu2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class search_question extends AppCompatActivity {

    List<DataSearch> searchModelArrayList = new ArrayList<>();
    private DataSearchAdapter dataSearchAdapter;
    String Question,anw,exam,review;
    ImageView btn_search, btn_back;
    ProgressDialog progressdialog;
    EditText nhaptu;
    RecyclerView recyclerView;
    TextView nullText,tv_toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchquestion);

        khaibao();

        progressdialog = new ProgressDialog(search_question.this);
        progressdialog.setMessage("Loadinggg");

        tv_toolbar.setText(TruyenDuLieu.trTenMon);

        recyclerView.setLayoutManager(new LinearLayoutManager(search_question.this, LinearLayoutManager.VERTICAL, false));
        dataSearchAdapter = new DataSearchAdapter(search_question.this, searchModelArrayList, new DataSearchAdapter.ClickCardReview() {
            @Override
            public void onClickCardReview(List<String> listReview) {
                Intent intent = new Intent(search_question.this, popup_search.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", (Serializable) listReview);
                TruyenDuLieu.trLoaiDe = "_review";
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        }, new DataSearchAdapter.ClickCardExam() {
            @Override
            public void onClickCardExam(List<String> listExam) {
                Intent intent = new Intent(search_question.this, popup_search.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("list", (Serializable) listExam);
                TruyenDuLieu.trLoaiDe = "_exam";
                intent.putExtras(bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
            }
        });
        recyclerView.setAdapter(dataSearchAdapter);

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressdialog.show();
                Log.e("search", nhaptu.getText().toString());
                callApi();
                searchModelArrayList.clear();
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(search_question.this, bo_de_thi.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
            }
        });
    }

    private void khaibao() {
        btn_search = findViewById(R.id.search_button);
        btn_back = findViewById(R.id.img_back_search);

        nhaptu = findViewById(R.id.txtsearch_key);
        recyclerView = findViewById(R.id.searchList);

        nullText = findViewById(R.id.search_nulltext);
        tv_toolbar = findViewById(R.id.text_toolbar_search);
    }

    private void callApi() {
        String url = "https://newdacn.onrender.com/search?search="+nhaptu.getText().toString()+"&sub="+TruyenDuLieu.trMon;
        Log.e("url",url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressdialog.dismiss();
                Log.e("search","ok");
                try {
                    JSONArray arr = new JSONArray(response);
                    Log.e("arr length", String.valueOf(arr.length()));
                    for (int i=0;i<arr.length();i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        Question = obj.getString("Question");
                        anw = obj.getString("anw");
                        JSONArray arrReview = obj.getJSONArray("review");

                        List<String> listreview = new ArrayList<>();
                        if (arrReview.length()!=0) {
                            review = "review";
                            for (int h=0;h<arrReview.length();h++) {
                                listreview.add(arrReview.getString(h));
                            }
                        } else review = "";

                        List<String> listexam = new ArrayList<>();
                        JSONArray arrExam = obj.getJSONArray("exam");
                        if (arrExam.length()!=0) {
                            exam = "exam";
                            for (int h=0;h<arrExam.length();h++) {
                                listexam.add(arrExam.getString(h));
                            }
                        } else exam = "";

                        //Hội nghị Pốtxđam
                        searchModelArrayList.add(new DataSearch(Question,anw,exam,review,listexam,listreview));
                    }
                    dataSearchAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressdialog.dismiss();
                nullText.setVisibility(View.VISIBLE);
                Log.e("search","fail");
                Toast.makeText(search_question.this,error.getMessage(),Toast.LENGTH_SHORT);
            }
        }) {
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                int mStatusCode = response.statusCode;
                return super.parseNetworkResponse(response);
            }
        };
        requestQueue.add(stringRequest);

    }


}

