package com.telmone.akhonin.view;

import static com.telmone.akhonin.constans.AppConstant.PAGES;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.telmone.akhonin.R;
import com.telmone.akhonin.adapter.PagerAdapter;
import com.telmone.akhonin.fragmen.PageFragment;
import com.telmone.akhonin.view_model.NumberViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText mEditText;
    private ViewPager viewPager;
    private PagerAdapter adapter;
    private NumberViewModel model;


    private int onPageSelected = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mEditText = findViewById(R.id.number);
        viewPager = findViewById(R.id.pager);

        TabLayout tabs = findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);
        tabs.setTabMode(TabLayout.MODE_SCROLLABLE);

        adapter = new PagerAdapter(getSupportFragmentManager());

        for(String page : PAGES){
            adapter.addFragment(new PageFragment(), page);
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                onPageSelected = position;

                model.selectedItem(PAGES.get(onPageSelected),mEditText.getText());
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        model = new ViewModelProvider(this).get(NumberViewModel.class);

        model.getRandom().observe(this,inputValue -> {
            mEditText.setText(inputValue);
        });

        viewPager.setCurrentItem(0);
        viewPager.setAdapter(adapter);

        model.selectedItem(PAGES.get(0),null);
    }


    private void getNumbers(String number, int i) {
        model.getNumberInfo(number,PAGES.get(i)).observe(this,numberResponse -> {
            if (numberResponse != null) {
                adapter.mFragmentList.get(PAGES.indexOf(numberResponse.getType())).setNumberInfo(numberResponse);
            };
        });
    }

    public void getNumberInfo(View view) {
        String number = String.valueOf(mEditText.getText());
        getNumbers(number, onPageSelected);
    }
}