package amazinginsidestudios.habit.engines;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import amazinginsidestudios.habit.components.Habit;
import amazinginsidestudios.habit.components.XmlCompressor;
import bullyfox.sangeeth.testube.managers.SuperDatabase;

/**
 * Created by Sangeeth Nandakumar on 11-12-2017.
 */

public class Database {
    Context context;
    SuperDatabase database;
    XmlCompressor xmlCompressor;

    public Database(Context context) {
        this.context = context;
        xmlCompressor = new XmlCompressor("", false);
        createHabitTable();
    }

    public void createHabitTable() {
        //Creates the table if not existing
        String DBNAME = "Habit";
        String SQL = "CREATE TABLE IF NOT EXISTS Habit_Habits (\n" +
                "account VARCHAR(200),\n" +
                "aim VARCHAR(200),\n" +
                "appVersion VARCHAR(200),\n" +
                "catageory VARCHAR(200),\n" +
                "cloudSynced VARCHAR(200),\n" +
                "color VARCHAR(200),\n" +
                "completedDays VARCHAR(200),\n" +
                "dateCreated VARCHAR(200),\n" +
                "dateExpired VARCHAR(200),\n" +
                "dateRemoved VARCHAR(200),\n" +
                "dateSynced VARCHAR(200),\n" +
                "device VARCHAR(200),\n" +
                "discloseProgressToPublic VARCHAR(200),\n" +
                "fingerprint VARCHAR(200) PRIMARY KEY,\n" +
                "habitState VARCHAR(200),\n" +
                "habitTemplate VARCHAR(200),\n" +
                "logLevel VARCHAR(200),\n" +
                "logTime VARCHAR(200),\n" +
                "name VARCHAR(200),\n" +
                "neverSync VARCHAR(200),\n" +
                "publicVisibility VARCHAR(200),\n" +
                "targetLevel VARCHAR(200),\n" +
                "totalDays VARCHAR(200),\n" +
                "xmlData VARCHAR(5000)\n" +
                ");";
        database = new SuperDatabase(context, DBNAME, SQL);
    }

    public void truncateHabitTable() {
        String SQL = "DELETE FROM Habit_Habits;";
        database.sqlInject(SQL);
    }

    //Main Functions

    public List<Habit> fetchHabits(String account) {
        List<Habit> habits = new ArrayList<>();
        String SQL = "SELECT * FROM Habit_Habits WHERE account='" + account + "' AND habitState='ACTIVE';";
        String json = database.sqlEjectJSON(SQL);
        json = json.substring(8, json.length() - 1);
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<Habit>>() {
        }.getType();
        habits = gson.fromJson(json, type);
        return habits;
    }

    public String habitTableRowCount() {
        String SQL = "SELECT count(*) as total FROM Habit_Habits;";
        List<String> strings = new ArrayList<>();
        strings = database.sqlEjectCSV(SQL);
        return strings.get(0);
    }

    public void insertHabit(Habit habit) {
        String oldXml, newXml;
        oldXml = habit.getXmlData();
        xmlCompressor = new XmlCompressor(oldXml, false);
        newXml = xmlCompressor.getXml();
        habit.setXmlData(newXml);
        String SQL = "REPLACE INTO Habit_Habits VALUES(\n" +
                "    '" + habit.getAccount() + "',\n" +
                "    '" + habit.getAim() + "',\n" +
                "    '" + habit.getAppVersion() + "',\n" +
                "    '" + habit.getCatageory() + "',\n" +
                "    '" + habit.isCloudSynced() + "',\n" +
                "    '" + habit.getColor() + "',\n" +
                "    '" + habit.getCompletedDays() + "',\n" +
                "    '" + habit.getDateCreated() + "',\n" +
                "    '" + habit.getDateExpired() + "',\n" +
                "    '" + habit.getDateRemoved() + "',\n" +
                "    '" + habit.getDateSynced() + "',\n" +
                "    '" + habit.getDevice() + "',\n" +
                "    '" + habit.isDiscloseProgressToPublic() + "',\n" +
                "    '" + habit.getFingerprint() + "',\n" +
                "    '" + habit.getHabitState() + "',\n" +
                "    '" + habit.getHabitTemplate() + "',\n" +
                "    '" + habit.getLogLevel() + "',\n" +
                "    '" + habit.getLogTime() + "',\n" +
                "    '" + habit.getName() + "',\n" +
                "    '" + habit.isNeverSync() + "',\n" +
                "    '" + habit.isPublicVisibility() + "',\n" +
                "    '" + habit.getTargetLevel() + "',\n" +
                "    '" + habit.getTotalDays() + "',\n" +
                "    '" + habit.getXmlData() + "');";
        Log.d("sql", SQL);
        database.sqlInject(SQL);
    }

    public void insertHabits(List<Habit> habits) {
        try {
            for (int i = 0; i < habits.size(); i++) {
                insertHabit(habits.get(i));
            }
        } catch (Exception e) {
            truncateHabitTable();
            e.printStackTrace();
        }
    }

    public void deleteHabit(String fingerprint) {
        String SQL = "DELETE FROM Habit_Habits WHERE fingerprint='" + fingerprint + "';";
        database.sqlInject(SQL);
    }

    public void dropHabitTable() {
        String SQL = "DROP TABLE Habit_Habits;";
        database.sqlInject(SQL);
    }

}
