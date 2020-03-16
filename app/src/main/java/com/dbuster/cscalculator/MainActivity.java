package com.dbuster.cscalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {
//    //Layouts--------------------------------------------
//    RelativeLayout EXLayout, FMLayout;
//    // Options--------------------------------------------
//    boolean calcModIsUnChecked = true;
//    TextView finalMarkOption, examMarkOption;
//    Switch calcMod;
//    //final mark layout------------------------------------
//    TextView finalMarkTextViewFMLayout;
//    EditText currentAverageEditTextFMLayout, examEditTextFMLayout;
//    //exam mark layout-------------------------------------
//    TextView examMarkTextViewEXLayout;
//    EditText currentAverageEditTextEXLayout, finalMarkEditTextEXLayout;

//    private Switch aSwitch;
    private SectionStatePagerAdapter mSectionStatePagerAdapter;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSectionStatePagerAdapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.container);
//        aSwitch = findViewById(R.id.fragmentChanger);
//        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked) {
//                    setViewPager(0);
//                } else {
//                    setViewPager(1);
//                }
//            }
//        });
        setupViewPager(viewPager);
    }
//    private void setViewPager(int fragmentNumber) {
//        viewPager.setCurrentItem(fragmentNumber);
//    }

    private void setupViewPager(ViewPager viewPager) {
        SectionStatePagerAdapter adapter = new SectionStatePagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new FinalMark(), "FinalMark");
        adapter.addFragment(new ExamMark(), "ExamMark");
        viewPager.setAdapter(adapter);
    }

}
