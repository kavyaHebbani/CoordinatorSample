package com.example.ksreeniv.coordinatorsample;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by ksreeniv on 13/06/16.
 */

public class MyScrollBehaviour extends CoordinatorLayout.Behavior<ImageView> {

    public MyScrollBehaviour() {

    }

    public MyScrollBehaviour(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    public boolean layoutDependsOn(final CoordinatorLayout parent, final ImageView child,
                                   final View dependency) {
        return dependency instanceof RecyclerView;
    }

//    @Override
//    public boolean onDependentViewChanged(final CoordinatorLayout parent,
//                                          final ImageView child,
//                                          final View dependency) {
//
//        if (!(dependency instanceof RecyclerView)) {
//            return super.onDependentViewChanged(parent, child, dependency);
//        }
//        offsetChildAsNeeded(child, dependency);
//        return true;
//    }

//    @Override
//    public boolean onInterceptTouchEvent(final CoordinatorLayout parent, final ImageView child,
//                                         final MotionEvent ev) {
//        offsetChildAsNeeded(child, parent);
//        return super.onInterceptTouchEvent(parent, child, ev);
//    }

    private void offsetChildAsNeeded(ImageView child, View dependency) {
        float targetTransY = child.getY() * (1 - dependency.getScrollY());
        Log.e("Kavya", "CHILD Y" + child.getY());
        Log.e("Kavya", "DEP TransY %f" + dependency.getScrollY());
        Log.e("Kavya", "CHILD TransY" + targetTransY);
        child.setTranslationY(targetTransY);
    }

}
