package com.xiaolaogong.test.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/**
 * Created by chechi on 2017/8/26.
 */

public class UserGoodsAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mFragmentList = new ArrayList<>();
    private String[] mTitles;

    public UserGoodsAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragmentList, String[] titles) {
        super(fragmentManager);
        mFragmentList = fragmentList;
        mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }
}
