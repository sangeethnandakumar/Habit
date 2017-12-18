package amazinginsidestudios.habit;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


public class Dashboard extends AppCompatActivity {
    android.support.v7.app.ActionBar actionBar;
    ViewPager viewPager;
    FloatingActionButton fab, fab_habit, fab_note;
    Animation animation;
    CardView card_new_habit, card_new_note;
    boolean isFabClicked = true;
    private DashboardFragmentAdapter dashboardFragmentAdapter;

    public void init()
    {
        fab = findViewById(R.id.fab);
        fab_habit = findViewById(R.id.new_habit);
        fab_note = findViewById(R.id.new_note);
        card_new_habit = findViewById(R.id.new_habit_card);
        card_new_note = findViewById(R.id.new_note_card);

        fab_habit.setVisibility(View.GONE);
        fab_note.setVisibility(View.GONE);
        card_new_note.setVisibility(View.GONE);
        card_new_habit.setVisibility(View.GONE);

        fab_habit.setEnabled(false);
        fab_note.setEnabled(false);

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
        actionBar.addTab(actionBar.newTab().setText("Notes").setTabListener(tabListener));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabClickAnimation();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        init();
        viewPager.setCurrentItem(1);

        fab_habit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabClickAnimation();
                Intent intent = new Intent(Dashboard.this, NewHabit.class);
                startActivity(intent);
            }
        });

        fab_note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabClickAnimation();
                Intent intent = new Intent(Dashboard.this, NewNote.class);
                startActivity(intent);
            }
        });
    }

    public void fabClickAnimation() {
        if (isFabClicked) {
            //Enable all uitems
            fab_habit.setEnabled(true);
            fab_note.setEnabled(true);
            //Rotate action fab
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_fab);
            fab.setImageResource(R.drawable.close_btn_white);
            fab.startAnimation(animation);
            //Zoom in sub fabs
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
            fab_habit.setVisibility(View.VISIBLE);
            fab_habit.startAnimation(animation);
            fab_note.setVisibility(View.VISIBLE);
            fab_note.startAnimation(animation);
            card_new_note.setVisibility(View.VISIBLE);
            card_new_note.setAnimation(animation);
            card_new_habit.setVisibility(View.VISIBLE);
            card_new_habit.setAnimation(animation);
            isFabClicked = false;
        } else {
            //Enable all uitems
            fab_habit.setEnabled(false);
            fab_note.setEnabled(false);
            //Rotate action fab
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_fab);
            fab.setImageResource(R.drawable.create_habit);
            fab.startAnimation(animation);
            //Zoom out sub fabs
            animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_out);
            fab_habit.startAnimation(animation);
            fab_note.startAnimation(animation);
            card_new_note.setAnimation(animation);
            card_new_habit.setAnimation(animation);
            card_new_note.setVisibility(View.GONE);
            card_new_habit.setVisibility(View.GONE);
            isFabClicked = true;
        }
    }

}
