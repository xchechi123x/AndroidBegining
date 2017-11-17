package com.xiaolaogong.test.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaolaogong.test.R;


public class DiscoveryFragment extends Fragment {


    public static final String TAG = "discovery";

    public DiscoveryFragment() {

    }

    public static DiscoveryFragment newInstance() {
        DiscoveryFragment fragment = new DiscoveryFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discovery, container, false);
    }

}
