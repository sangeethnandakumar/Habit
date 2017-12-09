package amazinginsidestudios.habit;

import android.content.Context;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import amazinginsidestudios.habit.components.Habit;

/**
 * Created by Sangeeth Nandakumar on 07-12-2017.
 */

public class FriendsCardAdapter extends BaseAdapter {
    Context context;
    List<Habit> habits;

    public FriendsCardAdapter(Context context, List<Habit> habits) {
        this.context = context;
        this.habits = habits;
    }

    @Override
    public int getCount() {
        return habits.size();
    }

    @Override
    public Object getItem(int i) {
        return habits.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        final ImageView card_template_friends_bg;
        final TextView name;
        final ImageButton popupmenu_friends;
        View v=null;

            v=View.inflate(context,R.layout.card_friend_template,null);
        name = v.findViewById(R.id.card_friends_template_name);
        popupmenu_friends = v.findViewById(R.id.popupmenu_friends);
        card_template_friends_bg = v.findViewById(R.id.card_template_friends_bg);

            name.setText(habits.get(i).getName());
            popupmenu_friends.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    PopupMenu friendspopup = new PopupMenu(context, view);
                    MenuInflater inflater = friendspopup.getMenuInflater();
                    inflater.inflate(R.menu.menu_card_friends, friendspopup.getMenu());
                    friendspopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId())
                            {
                                case R.id.menu_refresh:
                                    break;
                            }
                            return false;
                        }
                    });
                    friendspopup.show();
                }
            });

        switch (habits.get(i).getHabitTemplate())
        {
            case NIGHT:
                Picasso.with(context).load("http://amazinginside.esy.es/amazinginsidestudios/habit/night_profile.jpg").into(card_template_friends_bg);
        }
        return v;
    }

}
