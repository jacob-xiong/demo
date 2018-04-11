package demo.com.xiongyantest01.adpater.viewholder;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Map;

import demo.com.xiongyantest01.fragment.TabLayoutFragment;
import demo.com.xiongyantest01.widget.WrapContentHeightViewPager;

/**
 * @author by xiongyan on 2018/4/10.
 */
public class TabLayoutAdapter extends FragmentStatePagerAdapter {
    private ArrayList<String> titleList;
    private TabLayoutFragment mTabLayoutFragment;
    private Map<Integer, ArrayList<String>> mMap;
    private WrapContentHeightViewPager mTabViewPager;

    public TabLayoutAdapter(FragmentManager fm, ArrayList<String> titleList, Map<Integer, ArrayList<String>> map, WrapContentHeightViewPager tabViewPager) {
        super(fm);
        this.titleList = titleList;
        this.mMap = map;
        this.mTabViewPager = tabViewPager;
    }


    @Override
    public int getCount() {
        return titleList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleList.get(position);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mTabLayoutFragment = (TabLayoutFragment) object;
        super.setPrimaryItem(container, position, object);
    }


    public TabLayoutFragment getCurrentFragment() {
        return mTabLayoutFragment;
    }


    @Override
    public Fragment getItem(int position) {
        return TabLayoutFragment.newInstance(position, mMap.get(position),mTabViewPager);
    }

}
