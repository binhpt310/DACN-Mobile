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

import com.example.dacn.R;
import com.example.dacn.TruyenDuLieu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        progressdialog = new ProgressDialog(search_question.this);
        progressdialog.setMessage("Loadinggg");

        recyclerView.setLayoutManager(new LinearLayoutManager(search_question.this, LinearLayoutManager.VERTICAL, false));

        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressdialog.show();

                Log.e("search", nhaptu.getText().toString());
                Log.e("sub", TruyenDuLieu.trMon);
            }
        });
    }

    private void khaibao() {
        btn_search = findViewById(R.id.search_button);
        nhaptu = findViewById(R.id.txtsearch_key);
        recyclerView = findViewById(R.id.searchList);
        nullText = findViewById(R.id.search_nulltext);
    }

}

