package com.example.recordingnotes;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    private Button record;
    private Button notes;
    private Button review;
    private LinearLayout recordLayout;
    private LinearLayout notesLayout;
    private LinearLayout reviewLayout;
    private ViewPager viewPager;
    private FragmentPagerAdapter adapter;
    private List<Fragment> fragment_list;
    private RecordFragment recordFragment;
    private NotesFragment notesFragment;
    private ReviewFragment reviewFragment;
    private View.OnClickListener onClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        viewPager = findViewById(R.id.viewpager);
        recordLayout = findViewById(R.id.LinearLayout1);
        notesLayout = findViewById(R.id.LinearLayout2);
        reviewLayout = findViewById(R.id.LinearLayout3);
        record = findViewById(R.id.button4);
        notes = findViewById(R.id.button5);
        review = findViewById(R.id.button6);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "OUIJADOR.TTF");
        record.setTypeface(typeface);
        notes.setTypeface(typeface);
        review.setTypeface(typeface);
        recordLayout.setBackgroundResource(R.drawable.pressed);
        onClickListener = new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                int currentIndex = 0;
                resetButton();
                switch (v.getId()) {
                    case R.id.button4:
                        currentIndex = 0;
                        break;
                    case R.id.button5:
                        currentIndex = 1;
                        break;
                    case R.id.button6:
                        currentIndex = 2;
                        break;
                }
                pressed(currentIndex);
            }
        };
        click();
        recordFragment = new RecordFragment();
        notesFragment = new NotesFragment();
        reviewFragment = new ReviewFragment();
        fragment_list = new ArrayList<>();
        fragment_list.add(recordFragment);
        fragment_list.add(notesFragment);
        fragment_list.add(reviewFragment);
        adapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return fragment_list.get(position);
            }
            @Override
            public int getCount() {
                return fragment_list.size();
            }
        };
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }
            @Override
            public void onPageSelected(int position) {
                viewPager.setCurrentItem(position);
                resetButton();
                pressed(position);
            }
            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void resetButton() {
        recordLayout.setBackgroundResource(R.drawable.normal);
        notesLayout.setBackgroundResource(R.drawable.normal);
        reviewLayout.setBackgroundResource(R.drawable.normal);
    }

    private void pressed(int position) {
        switch (position) {
            case (0):
                recordLayout.setBackgroundResource(R.drawable.pressed);
                break;
            case (1):
                notesLayout.setBackgroundResource(R.drawable.pressed);
                break;
            case (2):
                reviewLayout.setBackgroundResource(R.drawable.pressed);
                break;
        }
        viewPager.setCurrentItem(position);
    }

    private void click() {
        record.setOnClickListener(onClickListener);
        notes.setOnClickListener(onClickListener);
        review.setOnClickListener(onClickListener);
    }
}
