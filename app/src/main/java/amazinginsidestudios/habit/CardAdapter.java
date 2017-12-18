package amazinginsidestudios.habit;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
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

import amazinginsidestudios.habit.components.Habit;
import amazinginsidestudios.habit.components.HabitTemplate;
import amazinginsidestudios.habit.engines.Database;
import amazinginsidestudios.habit.engines.HabitSyncer;
import bullyfox.sangeeth.testube.network.WebServer;

/**
 * Created by Sangeeth Nandakumar on 07-12-2017.
 */

public class CardAdapter extends BaseAdapter {
    Context context;
    Activity activity;
    List<Habit> habits;
    HabitSyncer habitSyncer;
    ImageView card_template_bg;
    TextView name;
    ImageButton popupmenu_yes_no, popupmenu_template;
    View v = null;
    Database database;
    CardView habit_card;

    public CardAdapter(Context context, List<Habit> habits, Activity activity) {
        this.context = context;
        this.habits = habits;
        this.activity = activity;
        database = new Database(context);
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
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (habits.get(i).getHabitTemplate() == HabitTemplate.YES_NO) {
            v = View.inflate(context, R.layout.card_yes_no, null);
            perfomYesNoCard(i);
        } else {
            v = View.inflate(context, R.layout.card_template, null);
            performTemplateCard(i);
        }
        fetchImages(i);
        return v;
    }

    public void perfomYesNoCard(final int i) {
        name = v.findViewById(R.id.card_yes_no_name);
        popupmenu_yes_no = v.findViewById(R.id.popupmenu_yes_no);
        name.setText(habits.get(i).getName());
        popupmenu_yes_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu yesnopopup = new PopupMenu(context, view);
                MenuInflater inflater = yesnopopup.getMenuInflater();
                inflater.inflate(R.menu.menu_card_yes_no, yesnopopup.getMenu());
                yesnopopup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()) {
                            case R.id.menu_delete_habit:
                                deleteMenu(i);
                                break;
                        }
                        return false;
                    }
                });
                yesnopopup.show();
            }
        });
    }

    public void performTemplateCard(final int i) {
        name = v.findViewById(R.id.card_template_name);
        habit_card = v.findViewById(R.id.habit_card);
        card_template_bg = v.findViewById(R.id.card_template_bg);
        popupmenu_template = v.findViewById(R.id.popupmenu_template);

        habit_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(activity, OpenHabit.class);
                context.startActivity(i);
            }
        });

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
                        switch (menuItem.getItemId()) {
                            case R.id.menu_delete_habit:
                                deleteMenu(i);
                                break;
                        }
                        return false;
                    }
                });
                templatepopup.show();
            }
        });
    }

    public void fetchImages(int i) {
        switch (habits.get(i).getHabitTemplate()) {
            case RUNNING:
                Picasso.with(context).load("http://amazinginside.esy.es/amazinginsidestudios/habit/running.jpeg").into(card_template_bg);
                break;
            case ABS:
                Picasso.with(context).load("http://amazinginside.esy.es/amazinginsidestudios/habit/abs.jpeg").into(card_template_bg);
                break;
            case PUSH_UP:
                Picasso.with(context).load("http://amazinginside.esy.es/amazinginsidestudios/habit/pushups.jpeg").into(card_template_bg);
                break;
        }
    }

    public void deleteMenu(final int pos) {
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle("Delete Habit");
        alert.setMessage("Are you sure to wan't to delete the habit '" + habits.get(pos).getName() + "' ?\n\nOnce deleted, You can't recover these informations. Cloud synced data will also be deleted if cloud syncing is active.\n\nIf you  wan't to remove the habit information from your dashboard, Then archive it and all the informations will be stored persistantly");
        alert.setPositiveButton("Delete Anyway", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                deleteFromServer(habits.get(pos).getAccount(), habits.get(pos).getFingerprint(), pos);
            }
        });
        alert.setNegativeButton("Don't Delete", null);
        alert.show();
    }

    public void deleteFromServer(String account, String fingerprint, final int pos) {
        String DELETE_HABIT_URL = "http://amazinginside.esy.es/amazinginsidestudios/habit/deleteHabit.php?account=" + account + "&fingerprint=" + fingerprint;
        if (isNetworkAvailable()) {
            WebServer server = new WebServer(context);
            server.setOnServerStatusListner(new WebServer.OnServerStatusListner() {
                @Override
                public void onServerResponded(String s) {
                    database.deleteHabit(habits.get(pos).getFingerprint());
                    Snackbar.make(activity.findViewById(R.id.dashboard_ui), "Deleted Habit '" + habits.get(pos).getName() + "'", Snackbar.LENGTH_LONG).show();
                    habits.remove(pos);
                }

                @Override
                public void onServerRevoked() {

                }
            });
            server.connectWithGET(DELETE_HABIT_URL);
        } else {
            Toast.makeText(context, "A working internet connection is required for deleting an account", Toast.LENGTH_SHORT).show();
        }

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
