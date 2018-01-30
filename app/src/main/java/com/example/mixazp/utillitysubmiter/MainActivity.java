package com.example.mixazp.utillitysubmiter;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.mixazp.utillitysubmiter.fragment.ElectricityFragment;
import com.example.mixazp.utillitysubmiter.fragment.GasFragment;
import com.example.mixazp.utillitysubmiter.fragment.WaterFragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        viewPager.setOffscreenPageLimit(2);
    }

    private void setupViewPager(ViewPager viewPager) {
        AdapterFragment adapter = new AdapterFragment(getSupportFragmentManager());
        adapter.addFragment(new GasFragment(), "GAS");
        adapter.addFragment(new ElectricityFragment(), "ELECTRICITY");
        adapter.addFragment(new WaterFragment(), "WATER");
        viewPager.setAdapter(adapter);
    }
}