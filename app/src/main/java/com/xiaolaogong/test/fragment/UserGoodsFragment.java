package com.xiaolaogong.test.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaolaogong.test.R;
import com.xiaolaogong.test.adapter.UserGoodsAdapter;
import com.xiaolaogong.test.adapter.UserGoodsRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chechi on 2017/8/26.
 */

public class UserGoodsFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private UserGoodsRecyclerAdapter mAdapter;

    private List<String> mDatas;

    private static final String ARG_TITLE = "title";

    private String mTitle;

    public static UserGoodsFragment getInstance(String title) {

        UserGoodsFragment userGoodsFragment = new UserGoodsFragment();

        Bundle bundle = new Bundle();

        bundle.putString(ARG_TITLE, title);

        userGoodsFragment.setArguments(bundle);

        return userGoodsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        mTitle = bundle.getString(ARG_TITLE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.user_goods_fragment, container, false);

        initData();

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recyclerview);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));

        mRecyclerView.setAdapter(mAdapter = new UserGoodsRecyclerAdapter(mRecyclerView.getContext(), mDatas));

        return v;
    }

    protected void initData() {
        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++) {
            mDatas.add(mTitle + (char) i);
        }
    }
}
