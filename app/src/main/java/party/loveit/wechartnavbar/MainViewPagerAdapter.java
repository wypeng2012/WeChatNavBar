package party.loveit.wechartnavbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wangyunpeng on 2016/3/10.
 * 主页viewpagger适配器
 */
public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public MainViewPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.mFragments = fragments;

    }

    @Override
    public Fragment getItem(int index) {

        return mFragments.get(index);
    }

    @Override
    public int getCount() {

        return mFragments.size();
    }

}
