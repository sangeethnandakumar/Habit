package amazinginsidestudios.habit;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Sangeeth Nandakumar on 08-12-2017.
 */

public class DashboardFragmentAdapter extends FragmentPagerAdapter
{

    public DashboardFragmentAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int index)
    {
        switch (index)
        {
            case 0:
                return new FriendsFragment();
            case 1:
                return new ActiveFragment();
            case 2:
                return new CompletedFragment();
        }
        return null;
    }

    @Override
    public int getCount()
    {
        return 3;
    }
}
