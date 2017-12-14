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
import amazinginsidestudios.habit.components.XmlCompressor;
import bullyfox.sangeeth.testube.component.DataRack;
import bullyfox.sangeeth.testube.network.WebServer;

/**
 * Created by Sangeeth Nandakumar on 09-12-2017.
 */

public class HabitSyncer {
    final String UPDATE_HABIT_URL = "http://amazinginside.esy.es/amazinginsidestudios/habit/updateHabits.php";
    final String FETCH_HABIT_URL = "http://amazinginside.esy.es/amazinginsidestudios/habit/fetchHabits.php";

    Context context;
    List<Habit> habits;
    Activity activity;
    OnSyncStatusListner listner;
    String account;

    public HabitSyncer(Context context, List<Habit> habits, Activity activity, String account) {
        this.context = context;
        this.habits = habits;
        this.activity = activity;
        this.account = account;
        //Compress their XML's
        String oldXml, newXml;
        XmlCompressor xmlCompressor = new XmlCompressor("", false);
        for (int i = 0; i < habits.size(); i++) {
            oldXml = habits.get(i).getXmlData();
            xmlCompressor.setXml(oldXml);
            newXml = xmlCompressor.getXml();
            habits.get(i).setXmlData(newXml);
        }
    }

    private String serialiseToJson() {
        Gson gson = new Gson();
        Type type = new TypeToken<List<Habit>>() {
        }.getType();
        return gson.toJson(habits, type);
    }

    public void setOnSyncStatusListner(OnSyncStatusListner listner) {
        this.listner = listner;
    }

    public void syncToServer() {
        WebServer server = new WebServer(context);
        server.setOnServerStatusListner(new WebServer.OnServerStatusListner() {
            @Override
            public void onServerResponded(String s) {
                Gson gson = new Gson();
                UpdateHabitAcknowledge acknowledge = gson.fromJson(s, UpdateHabitAcknowledge.class);
                if (acknowledge.getStatus() == true) {
                    int news = acknowledge.getNewHabitsSynced().size();
                    int updates = acknowledge.getUpdatedHabitsSynced().size();
                    if (news > 0 && updates > 0) {
                        String ack = String.valueOf(news) + " new habit added to your account and " + String.valueOf(updates) + " habit synced";
                        listner.onSyncSuccess(ack);
                    } else if (news > 0 && updates == 0) {
                        String ack = String.valueOf(news) + " new habit(s) added to your account";
                        listner.onSyncSuccess(ack);
                    } else if (news == 0 && updates > 0) {
                        String ack = String.valueOf(updates) + " habit(s) synced on your account";
                        listner.onSyncSuccess(ack);
                    } else if (news == 0 && updates == 0) {
                        String ack = "Nothing synced to server";
                        listner.onSyncSuccess(ack);
                    }
                } else {
                    listner.onSyncFailed();
                }
            }

            @Override
            public void onServerRevoked() {
                listner.onSyncFailed();
            }
        });
        //Upload local database in JSON
        Log.println(Log.INFO, "json", serialiseToJson());
        List<DataRack> dataRacks = new ArrayList<>();
        dataRacks.add(new DataRack("json", serialiseToJson()));
        server.connectWithPOST(activity, UPDATE_HABIT_URL, dataRacks);
    }

    public void syncFromServer() {
        WebServer server = new WebServer(context);
        server.setOnServerStatusListner(new WebServer.OnServerStatusListner() {
            @Override
            public void onServerResponded(String s) {
                if (s != "null") {
                    //Insert server data to Database
                    List<Habit> serverHabits = new ArrayList<>();
                    Gson gson = new Gson();
                    Type type = new TypeToken<List<Habit>>() {
                    }.getType();
                    serverHabits = gson.fromJson(s, type);
                    Database database = new Database(context);
                    database.insertHabits(serverHabits);
                }
                //Upload client data to server
                syncToServer();
            }

            @Override
            public void onServerRevoked() {
                Toast.makeText(context, "Error occured", Toast.LENGTH_SHORT).show();
            }
        });
        server.connectWithGET(FETCH_HABIT_URL + "?account=" + account);
    }

    public void cloudSync() {
        syncFromServer();
    }

    public interface OnSyncStatusListner {
        void onSyncSuccess(String acknoledge);

        void onSyncFailed();
    }

}
