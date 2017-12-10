package amazinginsidestudios.habit;


import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import amazinginsidestudios.habit.components.Booler;
import amazinginsidestudios.habit.components.Color;
import amazinginsidestudios.habit.components.Habit;
import amazinginsidestudios.habit.components.HabitState;
import amazinginsidestudios.habit.components.HabitTemplate;
import amazinginsidestudios.habit.components.LogLevel;
import amazinginsidestudios.habit.components.TargetLevel;
import amazinginsidestudios.habit.engines.HabitSyncer;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveFragment extends Fragment {
    ListView cardList;
    private View rootView = null;

    public ActiveFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_active, container, false);

        String xml = "<book category=\"children\">\n" +
                "    <title>Harry Potter</title>\n" +
                "    <author>J K. Rowling</author>\n" +
                "    <year>2005</year>\n" +
                "    <price>29.99</price>\n" +
                "  </book>";
        xml = xml.replace("\"", "_");
        xml = xml.replace("\n", "");

        cardList = rootView.findViewById(R.id.card_list);
        List<Habit> habits = new ArrayList<>();

        Habit habit = new Habit();
        habit.setName("Do Pushups");
        habit.setAccount("sangeethnandakumar@gmail.com");
        habit.setAim("To build up my body");
        habit.setCatageory("Health");
        habit.setCloudSynced(Booler.FALSE);
        habit.setColor(Color.RED);
        habit.setCompletedDays("0");
        habit.setDateExpired("2017-12-09 11:03 p.m.");
        habit.setDateRemoved("2017-12-09 11:03 p.m.");
        habit.setDiscloseProgressToPublic(Booler.FALSE);
        habit.setHabitState(HabitState.ACTIVE);
        habit.setHabitTemplate(HabitTemplate.NIGHT);
        habit.setLogLevel(LogLevel.DAILY);
        habit.setTotalDays("30");
        habit.setTargetLevel(TargetLevel.LIMITED);
        habit.setDateSynced("2017-12-09 11:03 p.m.");
        habit.setLogTime("11:03 p.m.");
        habit.setXmlData(xml);
        habit.setFingerprint(generateFingerprint());
        habit.setDateCreated(generateTimestamp());
        habit.setDevice(Build.MANUFACTURER + "|" + Build.BRAND + "|" + Build.MODEL);
        habit.setAppVersion(generateAppVersion());
        habit.setNeverSync(Booler.FALSE);
        habit.setPublicVisibility(Booler.FALSE);


        Habit habit1 = new Habit();
        habit1.setName("Do Pushups");
        habit1.setAccount("sangeethnandakumar@gmail.com");
        habit1.setAim("To build up my body");
        habit1.setCatageory("Health");
        habit1.setCloudSynced(Booler.FALSE);
        habit1.setColor(Color.RED);
        habit1.setCompletedDays("0");
        habit1.setDateExpired("2017-12-09 11:03 p.m.");
        habit1.setDateRemoved("2017-12-09 11:03 p.m.");
        habit1.setDiscloseProgressToPublic(Booler.FALSE);
        habit1.setHabitState(HabitState.ACTIVE);
        habit1.setHabitTemplate(HabitTemplate.NIGHT);
        habit1.setLogLevel(LogLevel.DAILY);
        habit1.setTotalDays("30");
        habit1.setTargetLevel(TargetLevel.LIMITED);
        habit1.setDateSynced("2017-12-09 11:03 p.m.");
        habit1.setLogTime("11:03 p.m.");
        habit1.setXmlData(xml);
        habit1.setFingerprint(generateFingerprint());
        habit1.setDateCreated(generateTimestamp());
        habit1.setDevice(Build.MANUFACTURER + "|" + Build.BRAND + "|" + Build.MODEL);
        habit1.setAppVersion(generateAppVersion());
        habit1.setNeverSync(Booler.FALSE);
        habit1.setPublicVisibility(Booler.FALSE);

        habits.add(habit);
        habits.add(habit1);

        cardList.setAdapter(new CardAdapter(getActivity(), habits));

        HabitSyncer habitSyncer = new HabitSyncer(getContext(), habits, getActivity());
        habitSyncer.cloudSync();

        return rootView;
    }


    public String generateFingerprint() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    private String generateAppVersion() {
        PackageManager manager = getContext().getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(getContext().getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = info.versionName;
        return version;
    }

    private String generateTimestamp() {
        Date d = new Date();
        CharSequence s = DateFormat.format("yyyy-MM-dd hh:mm a", d.getTime());
        return s.toString();
    }

}
