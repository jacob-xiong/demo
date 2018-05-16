package demo.com.xiongyantest01.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.com.xiongyantest01.R;

/**
 * @author by xiongyan on 2018/4/10.
 */
public class MyTabFragmentAdapter extends RecyclerView.Adapter {
    private LayoutInflater mInflater;
    private Context context;
    private List<String> mData;
    public static final int FOOT_VIEW = 1;
    public static final int NORMAL_VIEW = 2;

    public MyTabFragmentAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == NORMAL_VIEW) {
            View view = mInflater.inflate(R.layout.tab_normal_item, parent, false);
            MyTabFragmentViewHolder holder = new MyTabFragmentViewHolder(view);
            return holder;
        } else {
            View view = mInflater.inflate(R.layout.tab_normal_item, parent, false);
            FootViewHolder holder = new FootViewHolder(view);
            return holder;
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyTabFragmentViewHolder) {
            MyTabFragmentViewHolder holder1 = (MyTabFragmentViewHolder) holder;
            holder1.mTitle.setText(mData.get(position));
        } else if (holder instanceof FootViewHolder) {
            FootViewHolder holder2 = (FootViewHolder) holder;
            holder2.mTitle.setText("正在加载....");
        }

    }


    @Override
    public int getItemCount() {
        return mData.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == mData.size()) {
            return FOOT_VIEW;
        } else {
            return NORMAL_VIEW;
        }
    }

    class MyTabFragmentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.list_item_title)
        TextView mTitle;

        public MyTabFragmentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    class FootViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.list_item_title)
        TextView mTitle;

        public FootViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
