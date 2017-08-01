package demo.com.xiongyantest01.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.bean.MainBean;

/**
 * Created by xiongyan on 2017/7/27.
 */

public class MyAdapter extends
        RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<MainBean> dataList;
    private Context context;

    public MyAdapter(Context context, ArrayList<MainBean> list) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.dataList = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.main_list_item,
                parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.mTitle = (TextView) view.findViewById(R.id.list_item_title);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.mTitle.setText(dataList.get(position).getItemTitle());
        if (mOnItemClickLitener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(holder.itemView, position);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View arg0) {
            super(arg0);
        }

        TextView mTitle;
    }

    /**
     * ItemClick的回调接口
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
