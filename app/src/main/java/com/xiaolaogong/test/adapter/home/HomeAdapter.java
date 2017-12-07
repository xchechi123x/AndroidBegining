package com.xiaolaogong.test.adapter.home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiaolaogong.test.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 17/12/7.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    public void setItemList(List<HomeItem> itemList) {
        this.itemList = itemList;
    }

    List<HomeItem> itemList = new ArrayList<>();

    public HomeAdapter() {
        for (int i = 0; i < 20; i++) {
            HomeItem homeItem = new HomeItem();
            itemList.add(homeItem);
        }

    }

    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        HomeViewHolder homeViewHolder = new HomeViewHolder(LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_instagram_item, parent, false));

        return homeViewHolder;
    }

    @Override
    public void onBindViewHolder(HomeAdapter.HomeViewHolder holder, int position) {
        holder.homeItem = itemList.get(position);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class HomeViewHolder extends RecyclerView.ViewHolder {

        HomeItem homeItem;

        public HomeViewHolder(View itemView) {
            super(itemView);

        }
    }
}
