package com.example.ksreeniv.coordinatorsample;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rx.functions.Action1;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.MyViewHolder> {

    private final List<MyItem> mItemsList = new ArrayList<>();

    private Context mContext;

    MyRecyclerAdapter(Context context) {
        mContext = context;
    }

    public void setItemsList(List<MyItem> itemsList) {
        mItemsList.addAll(itemsList);
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext())
                                              .inflate(R.layout.recycler_item_view, null, false));
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        MyItem myItem = mItemsList.get(position);
        holder.setText(myItem.getText());
        holder.setImage(mContext, myItem.getImageResId());
        holder.setImageAction(myItem.getClickAction(), myItem.getImageResId());
    }

    @Override
    public int getItemCount() {
        return mItemsList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        final TextView mTextView;

        final ImageView mImageView;

        MyViewHolder(final View itemView) {
            super(itemView);

            mTextView = (TextView) itemView.findViewById(R.id.item_text);
            mImageView = (ImageView) itemView.findViewById(R.id.item_image);
        }

        void setText(String itemText) {
            mTextView.setText(itemText);
        }

        void setImage(Context context, int imageResId) {
            Picasso.with(context)
                   .load(imageResId)
                   .centerCrop()
                   .fit()
                   .placeholder(R.mipmap.ic_launcher)
                   .into(mImageView);
        }

        void setImageAction(final Action1<Integer> action, int imageResId) {
            mImageView.setOnClickListener(v -> action.call(imageResId));
        }

    }

}
