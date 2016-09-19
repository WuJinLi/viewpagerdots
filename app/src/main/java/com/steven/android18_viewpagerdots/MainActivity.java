package com.steven.android18_viewpagerdots;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.steven.android18_viewpagerdots.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Context mContext = this;
    private ViewPager viewPager_main;
    private RadioGroup radioGroup_main;
    private MyPagerAdapter adapter = null;
    private List<ImageView> totalList = new ArrayList<>();
    private RadioButton[] arrRadioButton = null;
    private TypedArray array = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();

        initView();

        initDots();
    }

    private void initData() {
        //初始化无类型数组，数组中存放图片
        array = getResources().obtainTypedArray(R.array.arrImages);
        //初始化数据源
        for (int i = 0; i < array.length(); i++) {
            ImageView imageView = new ImageView(mContext);
            imageView.setImageDrawable(array.getDrawable(i));
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            totalList.add(imageView);
        }
    }

    private void initView() {
        radioGroup_main = (RadioGroup) findViewById(R.id.radioGroup_main);
        viewPager_main = (ViewPager) findViewById(R.id.viewPager_main);
        //初始化adatper
        adapter = new MyPagerAdapter(totalList);
        viewPager_main.setAdapter(adapter);

        //viewPager添加监听器
        viewPager_main.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                arrRadioButton[position].setChecked(true);
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    //初始化小圆点的方法
    private void initDots() {
        //初始化小圆点的数组
        arrRadioButton = new RadioButton[array.length()];
        for (int i = 0; i < array.length(); i++) {
            arrRadioButton[i] = new RadioButton(mContext);
            //将RadioButton前方的圆圈修改成希望的图片
            arrRadioButton[i].setButtonDrawable(R.drawable.dots_selector);
            radioGroup_main.addView(arrRadioButton[i]);
        }
        //默认情况下选中的原点
        arrRadioButton[0].setChecked(true);

        //radioGroup设置监听器
        radioGroup_main.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                for (int i = 0; i < array.length(); i++) {
                    if (arrRadioButton[i].getId() == checkedId) {
                        //点击小圆点对应viewpager显示的图片
                        viewPager_main.setCurrentItem(i);
                    }
                }
            }
        });
    }
}
