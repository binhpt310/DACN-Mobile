package com.example.dacn.Lich_su_lam_bai.Subject_history;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPaperSubjectHisAdapter extends FragmentStateAdapter {


    public ViewPaperSubjectHisAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new HistoryFragment();
            case 1:
                return new GeoFragment();
            case 2:
                return new GDCDFragment();
            case 3:
                return new EngFragment();
            default:
                return new HistoryFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
