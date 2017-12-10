package amazinginsidestudios.habit.engines;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import amazinginsidestudios.habit.components.Habit;
import amazinginsidestudios.habit.components.UpdateHabitAcknowledge;
import bullyfox.sangeeth.testube.component.DataRack;
import bullyfox.sangeeth.testube.network.WebServer;

/**
 * Created by Sangeeth Nandakumar on 09-12-2017.
 */

public class HabitSyncer {
    final String UPDATE_HABIT_URL = "http://amazinginside.esy.es/amazinginsidestudios/habit/updateHabit.php";
    Context context;
    List<Habit> habits;
    Activity activity;

    public HabitSyncer(Context context, List<Habit> habits, Activity activity) {
        this.context = context;
        this.habits = habits;
        this.activity = activity;
    }

    private String serialiseToJson() {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Habit>>() {
        }.getType();
        return gson.toJson(habits, type);
    }

    public void cloudSync() {

        WebServer server = new WebServer(context);
        server.setOnServerStatusListner(new WebServer.OnServerStatusListner() {
            @Override
            public void onServerResponded(String s) {
                Gson gson = new Gson();
                List<String> updates = new ArrayList<>();

                UpdateHabitAcknowledge acknowledge = gson.fromJson(s, UpdateHabitAcknowledge.class);
                updates = acknowledge.getUpdatedHabitsSynced();

                Toast.makeText(context, updates.get(0), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onServerRevoked() {
                Toast.makeText(context, "Syncing habits failed", Toast.LENGTH_SHORT).show();
            }
        });

        Log.println(Log.INFO, "json", serialiseToJson());

        List<DataRack> dataRacks = new ArrayList<>();
        dataRacks.add(new DataRack("json", serialiseToJson()));
        server.connectWithPOST(activity, UPDATE_HABIT_URL, dataRacks);
    }

}
