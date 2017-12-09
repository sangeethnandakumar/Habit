package amazinginsidestudios.habit;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import amazinginsidestudios.habit.components.Habit;

public class Dashboard extends AppCompatActivity {
    android.support.v7.app.ActionBar actionBar;
    ViewPager viewPager;
    private DashboardFragmentAdapter dashboardFragmentAdapter;

    public void init()
    {
        dashboardFragmentAdapter = new DashboardFragmentAdapter(getSupportFragmentManager());
        viewPager=(ViewPager)findViewById(R.id.viewpager);
        viewPager.setAdapter(dashboardFragmentAdapter);
        actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        android.support.v7.app.ActionBar.TabListener tabListener=new android.support.v7.app.ActionBar.TabListener() {
            @Override
            public void onTabSelected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
                viewPager.setCurrentItem(tab.getPosition());
                viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener()
                {
                    @Override
                    public void onPageSelected(int position)
                    {
                        getSupportActionBar().setSelectedNavigationItem(position);
                    }
                });
            }

            @Override
            public void onTabUnselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {
            }

            @Override
            public void onTabReselected(android.support.v7.app.ActionBar.Tab tab, android.support.v4.app.FragmentTransaction ft) {

            }
        };
        actionBar.addTab(actionBar.newTab().setText("Friends").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Active").setTabListener(tabListener));
        actionBar.addTab(actionBar.newTab().setText("Completed").setTabListener(tabListener));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        init();
        viewPager.setCurrentItem(1);
    }
}
