package com.example.dacn.Lich_su_lam_bai.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.dacn.Lich_su_lam_bai.HistoryAdapter;
import com.example.dacn.Lich_su_lam_bai.History;
import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;
import com.example.dacn.hoanthanhbai.hoanthanhbaithi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EngFragment extends Fragment {

    private List<History> newsArrayList = new ArrayList<>();
    private HistoryAdapter historyAdapter;
    private RecyclerView recyclerView;
    String tenmon,id,url;
    TextView tv;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_eng, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tenmon = "English";
        callApi();

        tv = view.findViewById(R.id.tv_eng);
        recyclerView = view.findViewById(R.id.result);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        historyAdapter = new HistoryAdapter(newsArrayList, new IClickItemHistory() {
            @Override
            public void onClickItemHistory(String id) {
                onClickGoToDeTail(id);
            }
        });
        recyclerView.setAdapter(historyAdapter);
    }

    private void callApi() {
        url = "https://newdacn.onrender.com/getresult?email="+ TruyenDuLieu.trEmail_dnhap +"&type="+TruyenDuLieu.trDangBai+"&sub="+tenmon;
        Log.e("url",url);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject exam = object.getJSONObject(TruyenDuLieu.trDangBai);
                    JSONArray gdcd = exam.getJSONArray(tenmon);

                    for (int i=0;i<gdcd.length();i++) {
                        JSONObject arrGdcd = gdcd.getJSONObject(i);
                        id = arrGdcd.getString("_id");
                        String code = arrGdcd.getString("code");
                        String time = arrGdcd.getString("time");
                        String socauchualam = arrGdcd.getString("socauchualam");
                        String socaudung = arrGdcd.getString("socaudung");
                        String socausai = arrGdcd.getString("socausai");
                        newsArrayList.add(new History(id,code,socauchualam,socaudung,socausai,time));
                    }
                    historyAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                tv.setVisibility(View.VISIBLE);
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

    private void onClickGoToDeTail(String id){
        Intent intent = new Intent(getActivity(), hoanthanhbaithi.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_history_id", id);
        Log.e("id",id);
        bundle.putSerializable("object_history_url", url);
        Log.e("url",url);
        intent.putExtras(bundle);
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
    }
}

