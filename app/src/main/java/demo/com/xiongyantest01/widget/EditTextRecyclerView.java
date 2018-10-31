package demo.com.xiongyantest01.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author by xiongyan on 2018/10/31.
 */
public class EditTextRecyclerView extends RecyclerView {
    public EditTextRecyclerView(Context context) {
        super(context);
    }

    public EditTextRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EditTextRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void requestChildFocus(View child, View focused) {
//        super.requestChildFocus(child, focused);
    }
}
