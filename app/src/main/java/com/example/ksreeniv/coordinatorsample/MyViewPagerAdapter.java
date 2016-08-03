package com.example.ksreeniv.coordinatorsample;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ksreeniv on 13/06/16.
 */

public class MyViewPagerAdapter extends PagerAdapter {

    private final List<MyItem> mItemsList = new ArrayList<>();

    public MyViewPagerAdapter(List<MyItem> itemsList) {
        mItemsList.addAll(itemsList);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(container.getContext()).inflate(
                R.layout.view_pager_item, container, false);

        RecyclerView recyclerView = (RecyclerView) viewGroup.findViewById(R.id.recycler_view);
        setUpRecyclerView(recyclerView, container.getContext());

        container.addView(viewGroup);
        return viewGroup;
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view == object;
    }

    private void setUpRecyclerView(RecyclerView recyclerView, Context context) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        MyRecyclerAdapter adapter = new MyRecyclerAdapter(context);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

}
