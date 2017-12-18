package amazinginsidestudios.habit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import amazinginsidestudios.habit.components.Habit;

/**
 * Created by Sangeeth Nandakumar on 08-12-2017.
 */

public class OpenHabitAdapter extends FragmentPagerAdapter {
    Habit habit;
    boolean given = false;

    public OpenHabitAdapter(FragmentManager fm, Habit habit) {
        super(fm);
        this.habit = habit;
    }

    public OpenHabitAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        if (index == 0) {
            switch (habit.getHabitTemplate()) {
                case RUNNING:
                    return new Running();
                case PUSH_UP:
                    return new Pushup();
                default:
                    return new Running();
            }
        } else {
            return new History();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
