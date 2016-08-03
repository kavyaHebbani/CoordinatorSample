package com.example.ksreeniv.coordinatorsample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MainFragment extends Fragment implements MyItemPresenter.MyItemUpdateListener {

    private MyRecyclerAdapter mAdapter;

    private MyItemPresenter mPresenter;

    private ImageView mImageView;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container,
                             @Nullable final Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_fragment, container, false);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPresenter = new MyItemPresenter(this);
        mImageView = (ImageView) view.findViewById(R.id.expanded_image);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        initializeRecyclerView(recyclerView);
    }

    private void initializeRecyclerView(final RecyclerView recyclerView) {
        mAdapter = new MyRecyclerAdapter(getContext());
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setAdapter(mAdapter);
        recyclerView.setHorizontalScrollBarEnabled(true);
    }

    public void updateRecyclerView(final List<MyItem> itemList) {
        mAdapter.setItemsList(itemList);
    }

    public void showZoomedImage(final int resId) {
        int tag = 0;
        if (mImageView.getTag() != null) {
            tag = (int) mImageView.getTag();
        }
        if (tag != resId) {
            mImageView.setImageResource(resId);
            mImageView.setTag(resId);

            if (mImageView.getVisibility() == View.VISIBLE) {
                return;
            }
        }
        toggleImageVisibility();
    }

    private void toggleImageVisibility() {
        if (mImageView.getVisibility() == View.VISIBLE) {
            mImageView.setVisibility(View.INVISIBLE);
        } else {
            mImageView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        mPresenter.bind();
    }

    @Override
    public void onPause() {
        mPresenter.dispose();

        super.onPause();
    }

}
