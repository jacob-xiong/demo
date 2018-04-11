package demo.com.xiongyantest01.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.adpater.viewholder.TabSpecialViewHolder;

/**
 * @author by xiongyan on 2018/4/10.
 */
public class MySwipeRefreshLayout extends SwipeRefreshLayout {
    private RecyclerView partRecyle;


    public MySwipeRefreshLayout(Context context) {
        super(context);
    }

    public MySwipeRefreshLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {

        return super.onStartNestedScroll(child, target, nestedScrollAxes);
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {

        super.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {

        super.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        System.out.println("---------------dyConsumed----------------" + dyConsumed);
        System.out.println("---------------dyUnconsumed----------------" + dyUnconsumed);
        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        RecyclerView.ViewHolder containingViewHolder = partRecyle.findContainingViewHolder(target);
        if (containingViewHolder != null && containingViewHolder instanceof TabSpecialViewHolder) {
            int top = containingViewHolder.itemView.getTop();
            if (top == 0 && dy > 0) {
                return;
            }
            if (dy < 0 && target.canScrollVertically(-1) && top == 0) {
                return;
            }
            if (dy < 0 && top > 0 && top + dy < 0) {
                consumed[1] = -top;
            } else if (dy > 0 && top > 0 && top - dy < 0) {
                consumed[1] = top;
            } else {
                consumed[1] = dy;
            }
            partRecyle.scrollBy(0, consumed[1]);
            System.out.println("-----------------------------top----: " + top);
            System.out.println("-----------------------------consumed----: " + consumed[1]);
        }

        System.out.println("-----------------------------dy----: " + dy);

    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {

        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        System.out.println("------------------------------------");
        return false;
    }

    @Override
    public int getNestedScrollAxes() {
        return super.getNestedScrollAxes();


    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        partRecyle = (RecyclerView) findViewById(R.id.tab_main_recyler_view);

    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int action = e.getAction();
        if (action == MotionEvent.ACTION_DOWN && partRecyle != null) {
            View childViewUnder = partRecyle.findChildViewUnder(e.getX(), e.getY());
            RecyclerView.ViewHolder childViewHolder = childViewUnder == null ? null : partRecyle.getChildViewHolder(childViewUnder);
            if (childViewHolder != null && childViewHolder instanceof TabSpecialViewHolder) {
                requestDisallowInterceptTouchEvent(true);
            }
        }

        return super.onInterceptTouchEvent(e);
    }
}
