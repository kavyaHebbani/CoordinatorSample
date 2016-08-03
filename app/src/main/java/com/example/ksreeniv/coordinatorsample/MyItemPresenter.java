package com.example.ksreeniv.coordinatorsample;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

import static rx.Observable.just;

public final class MyItemPresenter {

    private static final String TAG = "MyItemPresenter";

    @NonNull
    private MyItemUpdateListener mItemUpdateListener;

    interface MyItemUpdateListener {

        void updateRecyclerView(final List<MyItem> itemList);

        void showZoomedImage(int resId);
    }

    @NonNull
    private CompositeSubscription mSubscription = new CompositeSubscription();

    MyItemPresenter(@NonNull MyItemUpdateListener listener) {
        mItemUpdateListener = listener;
    }

    void bind() {
        if (mSubscription.isUnsubscribed()) {
            mSubscription = new CompositeSubscription();
        }

        mSubscription.add(just(getItems(5))
                                  .subscribeOn(Schedulers.computation())
                                  .observeOn(AndroidSchedulers.mainThread())
                                  .subscribe(items -> mItemUpdateListener.updateRecyclerView(items),
                                             err -> Log.e(TAG, "Error updating recyclerview.")));
    }

    @NonNull
    private List<MyItem> getItems(final int count) {
        return Observable.range(0, count)
                         .map(this::getItem)
                         .toList()
                         .toBlocking()
                         .single();
    }

    @NonNull
    private MyItem getItem(final int position) {
        switch (position) {
            case 0:
                return new MyItem("Berlin", R.drawable.berlin, getClickAction());
            case 1:
                return new MyItem("Delhi", R.drawable.delhi, getClickAction());
            case 2:
                return new MyItem("London", R.drawable.london, getClickAction());
            case 3:
                return new MyItem("Vienna", R.drawable.vienna, getClickAction());
            case 4:
            default:
                return new MyItem("Paris", R.drawable.paris, getClickAction());
        }
    }

    @NonNull
    private Action1<Integer> getClickAction() {
        return resId -> mItemUpdateListener.showZoomedImage(resId);
    }

    void dispose() {
        mSubscription.unsubscribe();
    }

}
