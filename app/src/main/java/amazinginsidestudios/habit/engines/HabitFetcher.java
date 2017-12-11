package amazinginsidestudios.habit.engines;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import amazinginsidestudios.habit.components.Habit;
import bullyfox.sangeeth.testube.network.WebServer;

/**
 * Created by Sangeeth Nandakumar on 10-12-2017.
 */

public class HabitFetcher {
    final String FETCH_HABIT_URL = "http://amazinginside.esy.es/amazinginsidestudios/habit/fetchHabits.php";
    List<Habit> habits;
    HabitFetchListner listner;
    Context context;

    public HabitFetcher(Context context) {
        this.context = context;
    }

    public void setOnHabitFetchListner(HabitFetchListner listner) {
        this.listner = listner;
    }

    public void fetchHabits() {
        WebServer server = new WebServer(context);
        server.setOnServerStatusListner(new WebServer.OnServerStatusListner() {
            @Override
            public void onServerResponded(String s) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<Habit>>() {
                }.getType();
                List<Habit> habits = gson.fromJson(s, type);
                listner.onHabitsFetched(habits);
            }

            @Override
            public void onServerRevoked() {
                Toast.makeText(context, "Connectivity Issues", Toast.LENGTH_SHORT).show();
            }
        });
        server.connectWithGET(FETCH_HABIT_URL);
    }

    public interface HabitFetchListner {
        void onHabitsFetched(List<Habit> habits);
    }

}
