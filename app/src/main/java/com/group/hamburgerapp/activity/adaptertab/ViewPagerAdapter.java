package com.group.hamburgerapp.activity.adaptertab;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.group.hamburgerapp.fragment.fragmenttab.Tab1Fragment;
import com.group.hamburgerapp.fragment.fragmenttab.Tab2Fragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d("POSITION", String.valueOf(position));
        if(position==0){
            return Tab1Fragment.newInstance();
        }
        return Tab2Fragment.newInstance();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
