package com.example.dacn.hoanthanhbai;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dacn.Bo_de_thi.BoDe;
import com.example.dacn.Bo_de_thi.Bo_de_on_adapter;
import com.example.dacn.Bo_de_thi.bo_de_thi;
import com.example.dacn.Lich_su_lam_bai.History;
import com.example.dacn.Lich_su_lam_bai.lich_su_lam_bai;
import com.example.dacn.Lich_su_lam_bai.subjectHistory;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.cauhoi.CauHoiTracNghiem;
import com.example.dacn.cauhoi.ontap_tracnghiem;
import com.example.dacn.trangchu2;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class hoanthanhbaithi extends AppCompatActivity {
    private List<NdungCardModel> ndungCardModels = new ArrayList<>();
    RecyclerView rcv_hoanthanhbaithi;
    ImageView img_quaylai;
    HoanThanh_Adapter hoanThanh_adapter;
    String id,url,Question,cauhoidachon,anw,dungsai,tenmon;
    int tamp;

    JSONObject exam,jsArr;
    JSONArray mon;

    ProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoanthanhbaithi);

        progressdialog = new ProgressDialog(hoanthanhbaithi.this);
        progressdialog.setMessage("Loadinggg");

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        id = (String) bundle.get("object_history_id");
        url = (String) bundle.get("object_history_url");

        img_quaylai = findViewById(R.id.img_back_hoanthanhbaithi);
        rcv_hoanthanhbaithi = findViewById(R.id.rcv_hoanthanhbaithi);
        rcv_hoanthanhbaithi.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        hoanThanh_adapter = new HoanThanh_Adapter(ndungCardModels,this);
        rcv_hoanthanhbaithi.setAdapter(hoanThanh_adapter);

        img_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id==null) goTrangchu();
                else goToHistory();
            }
        });

        callApi();
    }

    private void callApi() {
        progressdialog.show();
        Log.e("url",url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                progressdialog.dismiss();
                try {
                    JSONObject object = new JSONObject(response);

                    if (object.has("exam")) {
                        exam = object.getJSONObject("exam");

                        if (exam.has("History")) { mon = exam.getJSONArray("History");}
                        else if (exam.has("English")) {mon = exam.getJSONArray("English");}
                        else if (exam.has("Gdcd")) {mon = exam.getJSONArray("Gdcd");}
                        else if (exam.has("Geography")) {mon = exam.getJSONArray("Geography");}

                        for (int i=0;i<mon.length();i++) {
                            JSONObject arrMon = mon.getJSONObject(i);
                            String idCheck = arrMon.getString("_id");
                            if (id==null) tamp = mon.length()-1;
                            else {
                                if (idCheck.equals(id)) tamp = i;
                            }
                        }
                        jsArr = mon.getJSONObject(tamp);
                        JSONArray done = jsArr.getJSONArray("done");

                        for (int j=0;j<done.length();j++) {
                            JSONObject arrDone = done.getJSONObject(j);

                            Question = arrDone.getString("Question");
                            anw = arrDone.getString("anw");

                            if (arrDone.has("cauhoidachon")) {cauhoidachon = arrDone.getString("cauhoidachon");}
                            else cauhoidachon = "";

                            if (arrDone.has("dungsai")){dungsai = arrDone.getString("dungsai");}
                            else dungsai = "sai";

                            ndungCardModels.add(new NdungCardModel(Question,cauhoidachon,anw,dungsai));
                            hoanThanh_adapter.notifyDataSetChanged();
                        }
                    }

                    else if (object.has("review")) {
                        exam = object.getJSONObject("review");

                        if (exam.has("History")) {mon = exam.getJSONArray("History");}
                        else if (exam.has("English")) {mon = exam.getJSONArray("English");}
                        else if (exam.has("Gdcd")) {mon = exam.getJSONArray("Gdcd");}
                        else if (exam.has("Geography")) {mon = exam.getJSONArray("Geography");}

                        for (int i=0;i<mon.length();i++) {
                            JSONObject arrMon = mon.getJSONObject(i);
                            String idCheck = arrMon.getString("_id");
                            if (id==null) tamp = mon.length()-1;
                            else {
                                if (idCheck.equals(id)) tamp = i;
                            }
                        }
                        jsArr = mon.getJSONObject(tamp);
                        JSONArray done = jsArr.getJSONArray("done");

                        for (int j=0;j<done.length();j++) {
                            JSONObject arrDone = done.getJSONObject(j);

                            Question = arrDone.getString("Question");
                            anw = arrDone.getString("anw");

                            if (arrDone.has("cauhoidachon")) {cauhoidachon = arrDone.getString("cauhoidachon");}
                            else cauhoidachon = "";

                            if (arrDone.has("dungsai")){dungsai = arrDone.getString("dungsai");}
                            else dungsai = "";

                            ndungCardModels.add(new NdungCardModel(Question,cauhoidachon,anw,dungsai));
                            hoanThanh_adapter.notifyDataSetChanged();
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressdialog.dismiss();
                Toast.makeText(hoanthanhbaithi.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    private void goToHistory() {
        Intent intent = new Intent(hoanthanhbaithi.this, subjectHistory.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        finish();
    }

    private void goTrangchu() {
        Intent intent = new Intent(hoanthanhbaithi.this, trangchu2.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        finish();
    }
}