package amazinginsidestudios.habit;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import amazinginsidestudios.habit.components.Habit;
import amazinginsidestudios.habit.engines.Database;
import amazinginsidestudios.habit.engines.HabitSyncer;


/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveFragment extends Fragment {
    //Declare variables
    ListView cardList;
    CardAdapter adapter;
    Database database;
    List<Habit> habits;
    SwipeRefreshLayout swipe;
    HabitSyncer syncer;
    private View rootView = null;

    public ActiveFragment() {
        // Required empty public constructor
    }

    //Init methord
    public void init(LayoutInflater inflater, ViewGroup container) {
        rootView = inflater.inflate(R.layout.fragment_active, container, false);
        swipe = rootView.findViewById(R.id.swiperefresh);
        cardList = rootView.findViewById(R.id.card_list);
        habits = new ArrayList<>();
        database = new Database(getContext());
        syncer = new HabitSyncer(getContext(), habits, getActivity(), accountServer());
    }

    //Listners
    public void listners() {
        swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                syncWithServer();
            }
        });
        syncer.setOnSyncStatusListner(new HabitSyncer.OnSyncStatusListner() {
            @Override
            public void onSyncSuccess(String acknoledge) {
                refreshHabits(accountServer());
                swipe.setRefreshing(false);
                Snackbar.make(getActivity().findViewById(R.id.dashboard_ui), acknoledge, Snackbar.LENGTH_LONG).show();
            }

            @Override
            public void onSyncFailed() {
                swipe.setRefreshing(false);
                Snackbar.make(getActivity().findViewById(R.id.dashboard_ui), "Syncing failed", Snackbar.LENGTH_LONG).show();
            }
        });
    }

    //Account server
    private String accountServer() {
        return "sangeethnandakumar@gmail.com";
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        init(inflater, container);
        listners();
        return rootView;
    }

    private void syncWithServer() {
        syncer.cloudSync();
    }

    private void refreshHabits(String account) {
        habits = database.fetchHabits(account);
        adapter = new CardAdapter(getContext(), habits, getActivity());
        cardList.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        cardList.invalidate();
    }

    @Override
    public void onResume() {
        super.onResume();
        refreshHabits(accountServer());
    }
}
