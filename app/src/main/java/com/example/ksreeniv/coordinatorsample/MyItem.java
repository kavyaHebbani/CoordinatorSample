package com.example.ksreeniv.coordinatorsample;

import rx.functions.Action1;

public class MyItem {

    private String mText;

    private int mImageResId;

    private Action1<Integer> mClickAction;

    public MyItem(final String text, final int imageResId, Action1<Integer> action) {
        mText = text;
        mImageResId = imageResId;
        mClickAction = action;
    }

    String getText() {
        return mText;
    }

    int getImageResId() {
        return mImageResId;
    }

    Action1<Integer> getClickAction() {
        return mClickAction;
    }

}
