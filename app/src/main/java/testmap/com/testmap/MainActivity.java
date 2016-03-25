package testmap.com.testmap;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import testmap.com.testmap.fragment.adapter.ViewPagerAdapter;
import testmap.com.testmap.fragment.widget.SlidingTabLayout;

@EActivity(R.layout.a_main)
public class MainActivity extends FragmentActivity {

    private static int countTabs = 2;
    private static CharSequence titles[] = { "Map", "List" };

    @ViewById(R.id.vp)
    ViewPager viewPager;
    @ViewById(R.id.stl)
    protected SlidingTabLayout slidingTabLayout;

    @AfterViews
    public void init() {
        initTabs();
    }

    protected void initTabs() {
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), titles, countTabs));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setCustomTabColorizer(position -> getResources().getColor(R.color.expand_block_text));
        slidingTabLayout.setViewPager(viewPager);
    }
}
