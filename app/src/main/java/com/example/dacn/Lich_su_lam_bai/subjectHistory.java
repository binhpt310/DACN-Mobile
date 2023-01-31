package com.example.dacn.Lich_su_lam_bai;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dacn.Lich_su_lam_bai.Fragment.ViewPaperSubjectHisAdapter;
import com.example.dacn.R;
import com.example.dacn.mucluc.mucluc;
import com.example.dacn.mucluc.trogiup;
import com.google.android.material.tabs.TabLayout;

public class subjectHistory extends AppCompatActivity
{
    TabLayout tabLayout;
    ViewPager2 viewPager2;
    ViewPaperSubjectHisAdapter viewPaperSubjectHisAdapter;
    @Override
    protected void onCreate(Bundle savedIntanceState)
    {
        super.onCreate(savedIntanceState);
        setContentView(R.layout.activity_subject_history);
        tabLayout = findViewById(R.id.tabLayout);
        viewPager2 = findViewById(R.id.view_page1);

        viewPaperSubjectHisAdapter = new ViewPaperSubjectHisAdapter(this);

        viewPager2.setAdapter(viewPaperSubjectHisAdapter);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener()
        {
            @Override
            public void onTabSelected (TabLayout.Tab tab)
            {
                viewPager2.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabLayout.getTabAt(position).select();
            }
        });
        View img_back_veapp = findViewById(R.id.img_back_veapp);

        img_back_veapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(subjectHistory.this, lich_su_lam_bai.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                finish();
            }
        });
    //replaceFragment(new GeoFragment());
    }

    private void replaceFragment(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.relative_layout,fragment);
        fragmentTransaction.commit();
    }

}
