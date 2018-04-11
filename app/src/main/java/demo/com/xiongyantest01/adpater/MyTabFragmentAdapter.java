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

    public MyTabFragmentAdapter(Context context, List<String> data) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = data;
    }

    @Override
    public MyTabFragmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.tab_normal_item, parent,false);
        MyTabFragmentViewHolder holder = new MyTabFragmentViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyTabFragmentViewHolder holder1 = (MyTabFragmentViewHolder) holder;
        holder1.mTitle.setText(mData.get(position));
    }


    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MyTabFragmentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.list_item_title)
        TextView mTitle;

        public MyTabFragmentViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
