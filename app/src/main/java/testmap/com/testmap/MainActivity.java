package testmap.com.testmap;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.InstanceState;
import org.androidannotations.annotations.ViewById;
import testmap.com.testmap.entity.MapPoint;
import testmap.com.testmap.fragment.adapter.ViewPagerAdapter;
import testmap.com.testmap.fragment.widget.SlidingTabLayout;

import java.util.ArrayList;

@EActivity(R.layout.a_main)
public class MainActivity extends AppCompatActivity {

    private static int countTabs = 2;
    private static CharSequence titles[] = {"Map", "List"};

    @ViewById(R.id.vp)
    ViewPager viewPager;
    @ViewById(R.id.stl)
    SlidingTabLayout slidingTabLayout;
    @ViewById
    Toolbar toolbar;

    Menu menu;

    @InstanceState
    public ArrayList<MapPoint> mapPointList = new ArrayList<MapPoint>();

    @AfterViews
    protected void init() {
        initToolbar();
        initTabs();
    }

    protected void initToolbar() {
        toolbar.setContentInsetsAbsolute(0, 0);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(false);
            actionBar.setDisplayShowCustomEnabled(true);
        }
    }

    protected void initTabs() {
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), titles, countTabs));
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setCustomTabColorizer(position -> getResources().getColor(R.color.expand_block_text));
        slidingTabLayout.setViewPager(viewPager);
    }

    public void showHome(boolean show) {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(show);
            actionBar.setDisplayShowHomeEnabled(show);
        }
        initToolbar();
    }

    public void setToolbarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        boolean show = title != null;
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(show);
        }
        slidingTabLayout.setVisibility(show ? View.GONE : View.VISIBLE);
        toolbar.setTitle(title);
        initToolbar();
    }

    public void showMenuItem(MenuItem menuItem, boolean show) {
        toolbar.post(() -> toolbar.getMenu().findItem(menuItem.getItemId()).setVisible(show));
    }

}
