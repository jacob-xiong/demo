package demo.com.xiongyantest01.adpater.viewholder;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import demo.com.xiongyantest01.R;
import demo.com.xiongyantest01.bean.TabActivityItemBean;

/**
 * @author by xiongyan on 2018/4/9.
 */
public class TabNormalViewHolder extends TabFloorBaseViewHolder {
    @BindView(R.id.list_item_title)
    TextView mNormalTilte;

    public TabNormalViewHolder(Context context, View itemView) {
        super(context, itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public void createView(int width) {

    }

    @Override
    public void bind(TabActivityItemBean data) {
        mNormalTilte.setText(data.getItemValue());
    }
}
