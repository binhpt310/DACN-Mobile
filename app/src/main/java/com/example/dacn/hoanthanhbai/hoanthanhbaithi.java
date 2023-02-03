package com.example.dacn.hoanthanhbai;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
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
    String id,url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoanthanhbaithi);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            id = (String) bundle.get("object_history_id");
            url = (String) bundle.get("object_history_url");
        }


        img_quaylai = findViewById(R.id.img_back_hoanthanhbaithi);
        rcv_hoanthanhbaithi = findViewById(R.id.rcv_hoanthanhbaithi);
        rcv_hoanthanhbaithi.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        hoanThanh_adapter = new HoanThanh_Adapter(ndungCardModels,this);
        rcv_hoanthanhbaithi.setAdapter(hoanThanh_adapter);

        View img_back_hoanthanhbaithi = findViewById(R.id.img_back_hoanthanhbaithi);

        img_back_hoanthanhbaithi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(hoanthanhbaithi.this, subjectHistory.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
            }
        });

        //callApi();

    }

    private void callApi() {
        String url = "https://newdacn.onrender.com/getresult?email=a@gm.com&type=exam&sub=Gdcd";
        //String url = "https://newdacn.onrender.com/getresult?email="+ TruyenDuLieu.trEmail_dnhap +"&type="+TruyenDuLieu.trDangBai+"&sub="+tenmon;
        Log.e("url",url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject exam = object.getJSONObject("exam");
                    JSONArray gdcd = exam.getJSONArray("Gdcd");
                    Log.e("length", String.valueOf(gdcd.length()));

                    for (int i=0;i<gdcd.length();i++) {
                        JSONObject arrGdcd = gdcd.getJSONObject(i);
                        String code = arrGdcd.getString("code");
                        String time = arrGdcd.getString("time");
                        JSONArray done = arrGdcd.getJSONArray("done");
                        Log.e("code", code);
                        Log.e("time", time);
                        Log.e("done_length", String.valueOf(done.length()));

                        JSONObject arrDone = done.getJSONObject(0);
                        String Questions = arrDone.getString("Questions");
                        String Selected = arrDone.getString("Selected");
                        String aws = arrDone.getString("aws");
                        String check = arrDone.getString("check");
                        Log.e("Questions", Questions);
                        Log.e("Selected", Selected);
                        Log.e("aws", aws);
                        Log.e("check", check);

                        ndungCardModels.add(new NdungCardModel(Questions,Selected,aws,check));
                        hoanThanh_adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(hoanthanhbaithi.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}