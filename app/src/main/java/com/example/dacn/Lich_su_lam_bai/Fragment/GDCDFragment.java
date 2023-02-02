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
import android.widget.Toast;

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

public class GDCDFragment extends Fragment {

    private List<History> newsArrayList = new ArrayList<>();
    private HistoryAdapter historyAdapter;
    private RecyclerView recyclerView;
    String tenmon;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gdcd, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tenmon = "Gdcd";

        recyclerView = view.findViewById(R.id.result);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        historyAdapter = new HistoryAdapter(newsArrayList, new IClickItemHistory() {
            @Override
            public void onClickItemHistory(History history) {
                onClickGoToDeTail(history);
            }
        });
        recyclerView.setAdapter(historyAdapter);

        callApi();
    }

    private void callApi() {
        String url = "https://newdacn.onrender.com/getresult?email="+ TruyenDuLieu.trEmail_dnhap +"&type="+TruyenDuLieu.trDangBai+"&sub="+tenmon;
        Log.e("url",url);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject exam = object.getJSONObject(TruyenDuLieu.trDangBai);
                    JSONArray gdcd = exam.getJSONArray(tenmon);
                    Log.e("length", String.valueOf(gdcd.length()));
                    for (int i=0;i<gdcd.length();i++) {
                        JSONObject arrGdcd = gdcd.getJSONObject(i);
                        String code = arrGdcd.getString("code");
                        String time = arrGdcd.getString("time");
                        String socauchualam = arrGdcd.getString("socauchualam");
                        String socaudung = arrGdcd.getString("socaudung");
                        String socausai = arrGdcd.getString("socausai");
                        /*JSONArray done = arrGdcd.getJSONArray("done");
                        Log.e("done_length", String.valueOf(done.length()));
                        for (int j=0;j<done.length();j++) {
                            JSONObject arrDone = done.getJSONObject(i);
                            String Questions = arrDone.getString("Questions");
                            String Selected = arrDone.getString("Selected");
                            String aws = arrDone.getString("aws");
                            String check = arrDone.getString("check");
                            Log.e("Questions", Questions);
                            Log.e("Selected", Selected);
                            Log.e("aws", aws);
                            Log.e("check", check);
                        }*/
                        newsArrayList.add(new History(code,socauchualam,socaudung,socausai,time));
                    }
                    historyAdapter.setList(newsArrayList);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);

    }

    private void onClickGoToDeTail(History history){
        Intent intent = new Intent(getActivity(), hoanthanhbaithi.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_history", history);
        intent.putExtras(bundle);
        getActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_in_left);
        startActivity(intent);
    }
}

