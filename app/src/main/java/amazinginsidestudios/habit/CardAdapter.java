package amazinginsidestudios.habit;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.graphics.Palette;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import amazinginsidestudios.habit.components.HabitTemplate;
import amazinginsidestudios.habit.components.Habit;

/**
 * Created by Sangeeth Nandakumar on 07-12-2017.
 */

public class CardAdapter extends BaseAdapter {
    Context context;
    List<Habit> habits;

    public CardAdapter(Context context, List<Habit> habits) {
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
        final ImageView card_template_bg;
        final TextView name;
        final ImageButton popupmenu_yes_no,popupmenu_template;
        View v=null;

        if (habits.get(i).getHabitTemplate()== HabitTemplate.YES_NO)
        {
            v=View.inflate(context,R.layout.card_yes_no,null);
            name=(TextView)v.findViewById(R.id.card_yes_no_name);
            popupmenu_yes_no=(ImageButton)v.findViewById(R.id.popupmenu_yes_no);

            name.setText(habits.get(i).getName());
            popupmenu_yes_no.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    PopupMenu yesnopopup = new PopupMenu(context, view);
                    MenuInflater inflater = yesnopopup.getMenuInflater();
                    inflater.inflate(R.menu.menu_card_yes_no, yesnopopup.getMenu());
                    yesnopopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId())
                            {
                                case R.id.menu_sync_now:
                                    Toast.makeText(context, "Syncing habit", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            return false;
                        }
                    });
                    yesnopopup.show();
                }
            });


        }
        else
        {
            v=View.inflate(context,R.layout.card_template,null);
            name=(TextView)v.findViewById(R.id.card_template_name);
            card_template_bg=(ImageView)v.findViewById(R.id.card_template_bg);
            popupmenu_template=(ImageButton)v.findViewById(R.id.popupmenu_template);

            name.setText(habits.get(i).getName());
            popupmenu_template.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu templatepopup = new PopupMenu(context, view);
                    MenuInflater inflater = templatepopup.getMenuInflater();
                    inflater.inflate(R.menu.menu_card_yes_no, templatepopup.getMenu());
                    templatepopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem menuItem) {
                            switch (menuItem.getItemId())
                            {
                                case R.id.menu_sync_now:
                                    Toast.makeText(context, "Syncing habit", Toast.LENGTH_SHORT).show();
                                    break;
                            }
                            return false;
                        }
                    });
                    templatepopup.show();
                }
            });

            switch (habits.get(i).getHabitTemplate())
            {
                case NIGHT:
                    Picasso.with(context).load("http://amazinginside.esy.es/amazinginsidestudios/habit/night_profile.jpg").into(card_template_bg);
            }
        }
        return v;
    }

}
