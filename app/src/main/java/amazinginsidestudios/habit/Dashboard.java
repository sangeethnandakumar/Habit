package amazinginsidestudios.habit;

import android.app.ActionBar;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;


public class Dashboard extends AppCompatActivity {
    android.support.v7.app.ActionBar actionBar;
    ViewPager viewPager;
    FloatingActionButton fab;
    private DashboardFragmentAdapter dashboardFragmentAdapter;

    public void init()
    {
        fab = findViewById(R.id.fab);
        dashboardFragmentAdapter = new DashboardFragmentAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewpager);
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

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(Dashboard.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.fab_options);
                dialog.show();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        init();
        viewPager.setCurrentItem(1);
    }
}
