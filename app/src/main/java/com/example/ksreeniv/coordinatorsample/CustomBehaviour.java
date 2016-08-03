package com.example.ksreeniv.coordinatorsample;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.NestedScrollView.OnScrollChangeListener;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

public class CustomBehaviour extends CoordinatorLayout.Behavior<RecyclerView> {

    public CustomBehaviour() {
        super();
    }

    public CustomBehaviour(Context context, AttributeSet attr) {
        super(context, attr);
    }

    @Override
    public boolean layoutDependsOn(final CoordinatorLayout parent,
                                   final RecyclerView child,
                                   final View dependency) {
        return dependency instanceof NestedScrollView;
    }

    @Override
    public boolean onDependentViewChanged(final CoordinatorLayout parent,
                                          final RecyclerView child,
                                          final View dependency) {
        if (dependency instanceof NestedScrollView) {
            final NestedScrollView scrollView = (NestedScrollView) dependency;

            scrollView.setOnScrollChangeListener(
                    (OnScrollChangeListener) (v, scrollX, scrollY, oldScrollX, oldScrollY) -> {
                        int bottom = v.getChildAt(v.getChildCount() - 1).getBottom();
                        int diff = bottom - (v.getHeight() + scrollY + child.getHeight());

                        // when diff <= 0, end of scrollview has been reached
                        if (diff <= 0) {
                            child.setVisibility(View.VISIBLE);
                            child.setTranslationY(child.getHeight() + diff);
                        } else {
                            child.setVisibility(View.INVISIBLE);
                        }
                    });

            return true;
        }
        return false;
    }

}
