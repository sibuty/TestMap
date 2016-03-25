package testmap.com.testmap.fragment.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import testmap.com.testmap.fragment.ListFragment_;
import testmap.com.testmap.fragment.MapFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private CharSequence titles[];
    private int countTabs;

    public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
        super(fm);
        this.titles = mTitles;
        this.countTabs = mNumbOfTabsumb;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return MapFragment.newInstance(position);
        } else {
            return ListFragment_.builder().build();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }

    @Override
    public int getCount() {
        return countTabs;
    }
}