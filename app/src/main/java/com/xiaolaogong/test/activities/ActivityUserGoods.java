package com.xiaolaogong.test.activities;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.xiaolaogong.test.R;
import com.xiaolaogong.test.adapter.UserGoodsAdapter;
import com.xiaolaogong.test.common.tools.IntentUtils;
import com.xiaolaogong.test.custom.layout.CoordinatorTabLayout;
import com.xiaolaogong.test.fragment.UserFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityUserGoods extends AppCompatActivity {


    private int[] mImageArray, mColorArray;

    private ArrayList<Fragment> mFragments;

    private final String[] mTitles = {"Android", "iOS", "前端", "拓展资源"};

    @BindView(R.id.viewPage)
    ViewPager mViewPager;

    @BindView(R.id.coordinatorTabLayout)
    CoordinatorTabLayout mCoordinatorTabLayout;

    private void initImageArray() {

        mImageArray = new int[]{
                R.drawable.bg_android,
                R.drawable.bg_ios,
                R.drawable.bg_js,
                R.drawable.bg_other
        };
    }

    private void initColorArray() {

        mColorArray = new int[]{
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light
        };
    }


    private void initFragments() {
        mFragments = new ArrayList<>();
        for (String title : mTitles) {
            mFragments.add(UserFragment.newInstance());
        }
    }

    private void initViewPage() {
        mViewPager.setOffscreenPageLimit(4);
        mViewPager.setAdapter(new UserGoodsAdapter(getSupportFragmentManager(), mFragments, mTitles));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_goods);
        ButterKnife.bind(this);

        initImageArray();
        initColorArray();
        initFragments();
        initViewPage();

        mCoordinatorTabLayout = (CoordinatorTabLayout) findViewById(R.id.coordinatorTabLayout);
        mCoordinatorTabLayout.setTransulcentStatusBar(this)
                .setTitle("Demo")
                .setBackEnable(true)
                .setImageArray(mImageArray, mColorArray)
                .setupWithViewPager(mViewPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        switch (item.getItemId()) {
            case R.id.action_about:
                IntentUtils.openUrl(this, "https://github.com/hugeterry/CoordinatorTabLayout");
                break;
            case R.id.action_about_me:
                IntentUtils.openUrl(this, "http://hugeterry.cn/about");
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
