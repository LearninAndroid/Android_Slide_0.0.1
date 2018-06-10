package dev.brian.com.onboard;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ViewPager mSlideViewPager;
    private LinearLayout mDotLayout;
    private SliderAdapter sliderAdapter;

    private TextView[] mDots;
    private Button mPrevious;
    private Button mNext;
    private int mCurrentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSlideViewPager = (ViewPager) findViewById(R.id.viewPage);
        mDotLayout = (LinearLayout) findViewById(R.id.dotsLayout);
        mPrevious = (Button) findViewById(R.id.BtnPrevious);
        mNext  = (Button) findViewById(R.id.BtnNext);

        sliderAdapter = new SliderAdapter(this);
        mSlideViewPager.setAdapter(sliderAdapter);
        addDotsIndicator(0);
        mSlideViewPager.addOnPageChangeListener(viewListener);

        mNext.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage +1);
            }
        });
        mPrevious.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                mSlideViewPager.setCurrentItem(mCurrentPage -1);
            }
        });
    }

    public void addDotsIndicator(int position){
        mDots = new TextView[3];
        mDotLayout.removeAllViews();
        for(int i=0; i <mDots.length;i++){
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.colorTransparentWhite));
            mDotLayout.addView(mDots[i]);
        }
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.colorWhite));
        }
    }
    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDotsIndicator(position);
            mCurrentPage = position;
            if(position ==0){
                mNext.setEnabled(true);
                mPrevious.setEnabled(false);
                mPrevious.setVisibility(View.INVISIBLE);
                mNext.setText("Next");
                mPrevious.setText("");
            }else if(position == mDots.length -1){
                mNext.setEnabled(true);
                mPrevious.setEnabled(true);
                mPrevious.setVisibility(View.VISIBLE);
                mNext.setText("Finish");
                mPrevious.setText("Previous");
            }else{
                mNext.setEnabled(true);
                mPrevious.setEnabled(true);
                mPrevious.setVisibility(View.VISIBLE);
                mNext.setText("Next");
                mPrevious.setText("Previous");
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}
