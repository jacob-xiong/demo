package demo.com.xiongyantest01.widget;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
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
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View childViewUnder = partRecyle.findChildViewUnder(ev.getX(), ev.getY());
            RecyclerView.ViewHolder childViewHolder = childViewUnder == null ? null : partRecyle.getChildViewHolder(childViewUnder);
            if (childViewHolder != null && childViewHolder instanceof TabSpecialViewHolder) {
                requestDisallowInterceptTouchEvent(true);
                return false;
            }
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onStartNestedScroll(View child, View target, int nestedScrollAxes) {
        return (nestedScrollAxes & ViewCompat.SCROLL_AXIS_VERTICAL) != 0;
    }

    @Override
    public void onNestedScrollAccepted(View child, View target, int nestedScrollAxes) {

//        super.onNestedScrollAccepted(child, target, nestedScrollAxes);
    }

    @Override
    public void onStopNestedScroll(View target) {

//        super.onStopNestedScroll(target);
    }

    @Override
    public void onNestedScroll(View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
//        super.onNestedScroll(target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);
    }

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        if (target.getId() == R.id.tab_main_recyler_view) {
            return;
        }
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
        }


    }

    @Override
    public boolean onNestedFling(View target, float velocityX, float velocityY, boolean consumed) {

        return false;
    }

    @Override
    public boolean onNestedPreFling(View target, float velocityX, float velocityY) {
        if (target.getId() == R.id.tab_main_recyler_view) {
            return false;
        }
        LinearLayoutManager llm = (LinearLayoutManager) partRecyle.getLayoutManager();
        int lastVisibleItemPosition = llm.findLastVisibleItemPosition();
        if (lastVisibleItemPosition > 0) {
            View viewByPosition = llm.findViewByPosition(lastVisibleItemPosition);
            RecyclerView.ViewHolder childViewHolder = partRecyle.getChildViewHolder(viewByPosition);
            if (childViewHolder != null
                    && childViewHolder instanceof TabSpecialViewHolder
                    && childViewHolder.itemView.getTop() != 0) {
                partRecyle.fling((int) velocityX, (int) velocityY);
                return true;
            }
        }
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


}
