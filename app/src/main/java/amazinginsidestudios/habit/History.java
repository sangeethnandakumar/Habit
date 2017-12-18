package amazinginsidestudios.habit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class History extends Fragment {
    ListView history_list;
    View rootView;

    public History() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.history, container, false);
        history_list = rootView.findViewById(R.id.history_list);

        List<String> histories = new ArrayList<>();
        histories.add("28#Mon#Jan#2017#Super history week");
        histories.add("29#Tue#Feb#2018#Perfect history week\nAnd one more");
        histories.add("30#Wed#Mar#2019#Nice history week\nTwo\nAnd one more");


        HistoryAdapter adapter = new HistoryAdapter(histories, getContext());
        history_list.setAdapter(adapter);

        return rootView;
    }

}
