package demo.com.xiongyantest01.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.NestedScrollingParent;
import android.support.v4.view.NestedScrollingParentHelper;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * @author by xiongyan on 2018/5/10.
 */
public class SuctionTopParentLayout extends RelativeLayout implements NestedScrollingParent {
    private NestedScrollingParentHelper mParentHelper;
    private SuctionTopParentChlidLayout nsc;
    private int imgHeight;

    public SuctionTopParentLayout(@NonNull Context context) {
        super(context);
        init();
    }

    public SuctionTopParentLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mParentHelper = new NestedScrollingParentHelper(this);
    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return mParentHelper.getNestedScrollAxes();
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        return super.onNestedPreFling(target, velocityX, velocityY);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if (showImg(dy) || hideImg(dy)) {
            scrollBy(0, -dy);
            consumed[1] = dy;
        }
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int axes) {
        mParentHelper.onNestedScrollAccepted(child, target, axes);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        if (target instanceof SuctionTopParentChlidLayout) {
            return true;
        }
        return false;
    }

    @Override
    public void onStopNestedScroll(View child) {
        mParentHelper.onStopNestedScroll(child);
    }


    /**
     * 下拉的时候是否要向下滚动以显示
     *
     * @param dy
     * @return
     */
    public boolean showImg(int dy) {
        if (dy > 0) {
            if (getScrollY() > 0 && nsc.getScrollY() >= 0) {
                return true;
            }
        }

        return false;
    }

    /**
     * 上拉的时候，是否要向上滚动，隐藏
     */
    public boolean hideImg(int dy) {
        if (dy < 0) {
            if (getScrollY() < imgHeight) {
                return true;
            }
        }
        return false;
    }


    /**
     * 限制滚动范围
     *
     * @param x
     * @param y
     */
    @Override
    public void scrollTo(int x, int y) {
        if (y < 0) {
            y = 0;
        }
        if (y > imgHeight) {
            y = imgHeight;
        }

        super.scrollTo(x, y);
    }

    public void setImgHeight(int imgHeight) {
        this.imgHeight = imgHeight;
    }

    public void setNsc(SuctionTopParentChlidLayout nsc) {
        this.nsc = nsc;
    }
}
