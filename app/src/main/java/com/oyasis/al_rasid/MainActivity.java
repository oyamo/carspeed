package com.oyasis.al_rasid;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;
import com.oyasis.al_rasid.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    VPAdapter vpAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        vpAdapter = new VPAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        binding.viewpager.setAdapter(vpAdapter);
        binding.tabLayout.setupWithViewPager(binding.viewpager);

        if(binding.tabLayout.getTabCount() == 2) {
            TabLayout.Tab tab0 = binding.tabLayout.getTabAt(0);
            TabLayout.Tab tab1 = binding.tabLayout.getTabAt(1);

            tab0.setIcon(R.drawable.ic_sharp_home_24);
            tab1.setIcon(R.drawable.ic_baseline_history_24);
        }

        setContentView(binding.getRoot());
    }
}
