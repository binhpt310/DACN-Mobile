package com.example.dacn.Lich_su_lam_bai;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dacn.Lich_su_lam_bai.Fragment.GeoFragment;
import com.example.dacn.Lich_su_lam_bai.Fragment.ViewPaperSubjectHisAdapter;
import com.example.dacn.R;
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
    replacFrament(new GeoFragment());
    }

    private void replacFrament(Fragment fragment) {

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.relative_layout,fragment);
        fragmentTransaction.commit();
    }
}
