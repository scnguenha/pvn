package mz.co.sidy.pvn.util;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;

import mz.co.sidy.pvn.listener.OnSwipeTouchListener;


/**
 * Created by elcidioExi on 10/11/2016.
 */
public class CustomViewPager extends ViewPager {

    private boolean enabled;
    private GestureDetector detector;
    private OnSwipeTouchListener onSwipeTouchListener;

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.enabled = true;
        detector = new GestureDetector(context, new OnSwipeListener());

    }
    public CustomViewPager(Context context) {
        super(context);

        // In OnCreate or custome view constructor (which extends one of Android views)
        detector = new GestureDetector(context, new OnSwipeListener());
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (this.enabled)
            return detector.onTouchEvent(ev);

        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (this.enabled)
            return detector.onTouchEvent(ev);

        return false;
    }

    public void setPagingEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setOnSwipeTouchListener(OnSwipeTouchListener onSwipeTouchListener) {
        this.onSwipeTouchListener = onSwipeTouchListener;
    }

    private static final int SWIPE_MIN_DISTANCE = 10;
    private static final int SWIPE_THRESHOLD_VELOCITY = 100;

    private class OnSwipeListener extends GestureDetector.SimpleOnGestureListener {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            float diffY = e2.getY() - e1.getY();
            float diffX = e2.getX() - e1.getX();

            if (Math.abs(diffX) > Math.abs(diffY)) {
                if (Math.abs(diffX) > SWIPE_MIN_DISTANCE && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    if (diffX > 0) {
                        return onSwipeTouchListener == null || onSwipeTouchListener.onLeftSwipe(); // Left to right
                    } else if (diffX < 0) {
                        return onSwipeTouchListener == null || onSwipeTouchListener.onRightSwipe(); // Right to left
                    }
                }
            }

            return false;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            return Math.abs(distanceX) > Math.abs(distanceY);
        }
    }
}
