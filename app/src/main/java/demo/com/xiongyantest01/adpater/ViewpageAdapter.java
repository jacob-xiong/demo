package demo.com.xiongyantest01.adpater;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * @author by xiongyan on 2018/2/9.
 */

public class ViewpageAdapter extends PagerAdapter {
    private List<View> mViewLists;//View就是GridView

    public ViewpageAdapter(List<View> viewLists) {
        this.mViewLists = viewLists;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(mViewLists.get(position));
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(mViewLists.get(position));
        return container.getChildAt(position);
    }

    @Override
    public int getCount() {
        return mViewLists != null ? mViewLists.size() : 0;
    }


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
