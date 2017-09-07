package demo.com.xiongyantest01.custom;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.nineoldandroids.view.ViewHelper;

import demo.com.xiongyantest01.utils.L;

/**
 * Created by xiongyan on 2017/9/1.
 * 滑动View
 */

public class SlideView extends View {
    private int mLastX;
    private int mLastY;

    public SlideView(Context context) {
        super(context);
    }

    public SlideView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SlideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SlideView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                setActionMove(x, y);
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        mLastX = x;
        mLastY = y;
        return true;
    }

    private void setActionMove(int x, int y) {
        int deltaX = x - mLastX;
        int deltaY = y - mLastY;
        L.v("-----------------------", "deltaX:" + deltaX + "-----------" + "deltaY:" + deltaY);
        int translationX = (int) (ViewHelper.getTranslationX(this) + deltaX);
        int translationY = (int) (ViewHelper.getTranslationY(this) + deltaY);
        ViewHelper.setTranslationX(this, translationX);
        ViewHelper.setTranslationY(this, translationY);

    }
}
