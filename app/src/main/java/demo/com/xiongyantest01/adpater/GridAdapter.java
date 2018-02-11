package demo.com.xiongyantest01.adpater;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.bean.GridBean;
import demo.com.xiongyantest01.bean.MainBean;

/**
 * @author by xiongyan on 2017/12/13.
 */

public class GridAdapter extends
        RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private LayoutInflater mInflater;
    private ArrayList<GridBean> dataList;
    private Context context;

    public GridAdapter(Context context, ArrayList<GridBean> list) {
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.dataList = list;
    }

    @Override
    public GridAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_list_item,
                parent, false);
        ViewHolder viewHolder = new GridAdapter.ViewHolder(view);
        viewHolder.mTitle = (TextView) view.findViewById(R.id.grid_item_title);
        viewHolder.mImageView = (ImageView) view.findViewById(R.id.grid_item_img);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final GridAdapter.ViewHolder holder, final int position) {
        holder.mTitle.setText(dataList.get(position).getTitle());
        holder.mImageView.setBackgroundResource(dataList.get(position).getBgId());
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
        ImageView mImageView;
    }

    /**
     * ItemClick的回调接口
     */
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
    }

    private GridAdapter.OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(GridAdapter.OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
