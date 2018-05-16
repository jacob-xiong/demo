package demo.com.xiongyantest01.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import java.util.ArrayList;

import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.activity.MyTabActivity;
import demo.com.xiongyantest01.adpater.viewholder.TabFloorBaseViewHolder;
import demo.com.xiongyantest01.adpater.viewholder.TabNormalViewHolder;
import demo.com.xiongyantest01.adpater.viewholder.TabSpecialViewHolder;
import demo.com.xiongyantest01.bean.TabActivityItemBean;

/**
 * @author by xiongyan on 2018/4/9.
 */
public class MyTabActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<TabActivityItemBean> mDataList;
    private Context mContext;
    private RecyclerView mRecyclerView;

    public MyTabActivityAdapter(Context context, ArrayList<TabActivityItemBean> list, RecyclerView recyclerView) {
        this.mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDataList = list;
        this.mRecyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        final View itemView;
        switch (viewType) {
            case MyTabActivity.NORMAL_TYPE:
                itemView = getResView(parent, R.layout.tab_normal_item);
                TabNormalViewHolder normalViewHolder = new TabNormalViewHolder(mContext, itemView);
                normalViewHolder.createView(0);
                return normalViewHolder;
            case MyTabActivity.SPECIAL_TYPE:
                itemView = getResView(parent, R.layout.tab_special_item);
                TabSpecialViewHolder specialViewHolder = new TabSpecialViewHolder(mContext, parent, itemView);
                specialViewHolder.createView(0);
//                mRecyclerView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
//                    @Override
//                    public boolean onPreDraw() {
//                        mRecyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
//                        ViewGroup.LayoutParams layoutParams = itemView.getLayoutParams();
//                        layoutParams.height = mRecyclerView.getMeasuredHeight();
//                        itemView.setLayoutParams(layoutParams);
//                        return false;
//                    }
//                });
                final ViewTreeObserver viewTreeObserver = parent.getViewTreeObserver();
                viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        if (viewTreeObserver.isAlive()) {
                            viewTreeObserver.removeOnPreDrawListener(this);
                        }
//                mTabViewPager.setMaxHeight((int) (parrent.getHeight() - TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 48, itemView.getContext().getResources().getDisplayMetrics()) - 20));
                        ViewGroup.LayoutParams layoutParams=itemView.getLayoutParams();
                        layoutParams.height=parent.getHeight()-20 ;
                        itemView.setLayoutParams(layoutParams);
                        return false;
                    }

                });
                return specialViewHolder;
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        TabFloorBaseViewHolder baseViewHolder = (TabFloorBaseViewHolder) holder;
        baseViewHolder.bind(mDataList.get(position));
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mDataList.get(position).getItemType();
    }

    private View getResView(ViewGroup viewGroup, int viewResId) {
        return mInflater.inflate(viewResId, viewGroup, false);
    }
}
