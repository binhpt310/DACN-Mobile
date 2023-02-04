package com.example.dacn.search;

import static com.example.dacn.RetrofitInterface.retrofitInterface;

import android.app.ProgressDialog;
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
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.hoanthanhbai.hoanthanhbaithi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class search_question extends AppCompatActivity {

    List<DataSearch> searchModelArrayList = new ArrayList<>();
    String tenmon, keyword;
    ImageView btn_search;
    ProgressDialog progressdialog;
    EditText nhaptu;
    RecyclerView recyclerView;
    TextView nullText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchquestion);

        khaibao();

        //if (TruyenDuLieu.trMon )

        progressdialog = new ProgressDialog(search_question.this);
        progressdialog.setMessage("Loadinggg");

        recyclerView.setLayoutManager(new LinearLayoutManager(search_question.this, LinearLayoutManager.VERTICAL, false));

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressdialog.show();
                Log.e("search", nhaptu.getText().toString());
                callApi();
            }
        });
    }

    private void khaibao() {
        btn_search = findViewById(R.id.search_button);
        nhaptu = findViewById(R.id.txtsearch_key);
        recyclerView = findViewById(R.id.searchList);
        nullText = findViewById(R.id.search_nulltext);
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
                /*try {
                    JSONArray arr = new JSONArray(response);
                    Log.e("arr length", String.valueOf(arr.length()));
                    for (int i=0;i<arr.length();i++) {
                        JSONObject obj = arr.getJSONObject(i);
                        String Question = obj.getString("Question");
                        Log.e("Question",Question);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
            }
        }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error == null || error.networkResponse == null) {
                    return;
                }

                /*String body;
                //get status code here
                final String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                try {
                    body = new String(error.networkResponse.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // exception
                }*/

                //do stuff with the body..
                progressdialog.dismiss();
                Log.e("search","fail");
                Toast.makeText(search_question.this,error.getMessage(),Toast.LENGTH_SHORT).show();
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

