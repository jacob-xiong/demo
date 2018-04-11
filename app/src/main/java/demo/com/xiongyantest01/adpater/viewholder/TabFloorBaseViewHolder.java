package demo.com.xiongyantest01.adpater.viewholder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;


import demo.com.xiongyantest01.bean.TabActivityItemBean;

/**
 * @author by xiongyan on 2018/4/9.
 */
abstract public class TabFloorBaseViewHolder extends RecyclerView.ViewHolder {
    public Context mContext;
    int width;
    private View mItemView;
    private int floorPosition = 0;


    public TabFloorBaseViewHolder(Context context, View itemView) {
        super(itemView);
        mContext = context;
    }

    abstract public void createView(int width);

    abstract public void bind(TabActivityItemBean data);

    public void setPosition(int position) {
        floorPosition = position;
    }

    public int getFloorPosition() {
        return floorPosition;
    }
}
