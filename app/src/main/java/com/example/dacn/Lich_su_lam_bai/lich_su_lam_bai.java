package com.example.dacn.Lich_su_lam_bai;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.mucluc.mucluc;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class lich_su_lam_bai extends AppCompatActivity {

    LinearLayout llFolderOn, llFolderThi;
    ConstraintLayout toolbar_lichsulambai;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_lam_bai);

        llFolderOn = findViewById(R.id.layout_folder_on);
        llFolderThi = findViewById(R.id.layout_folder_thi);

        llFolderOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lich_su_lam_bai.this, subjectHistory.class);
                TruyenDuLieu.trDangBai = "review";
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_left);
                finish();
            }
        });

        llFolderThi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lich_su_lam_bai.this, subjectHistory.class);
                TruyenDuLieu.trDangBai = "exam";
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
                finish();
            }
        });

        toolbar_lichsulambai = findViewById(R.id.toolbar_lichsulambai);
        toolbar_lichsulambai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(lich_su_lam_bai.this, mucluc.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
            }
        });
    }

    private void callApi() {
        String url = "https://newdacn.onrender.com/getresult?email=a@gm.com&type=exam&sub=Gdcd";
        //String url = "https://newdacn.onrender.com/getresult?email="+ TruyenDuLieu.trEmail_dnhap +"&type="+TruyenDuLieu.trDangBai+"&sub="+tenmon;
        Log.e("url",url);
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject sobai = object.getJSONObject("creview");
                    Log.e("length", String.valueOf(sobai));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(lich_su_lam_bai.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }

}
