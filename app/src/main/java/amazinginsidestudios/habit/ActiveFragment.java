package amazinginsidestudios.habit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import amazinginsidestudios.habit.components.HabitTemplate;
import amazinginsidestudios.habit.components.Habit;


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

        cardList=(ListView)rootView.findViewById(R.id.card_list);
        List<Habit> myhabits=new ArrayList<>();

        Habit temp=new Habit(getContext());
        temp.setName("Sleep Early");
        temp.setHabitTemplate(HabitTemplate.NIGHT);
        myhabits.add(temp);

        Habit temp1=new Habit(getContext());
        temp1.setName("Read Newspaper");
        temp1.setHabitTemplate(HabitTemplate.YES_NO);
        myhabits.add(temp1);

        Habit temp2=new Habit(getContext());
        temp2.setName("Read a Book");
        temp2.setHabitTemplate(HabitTemplate.NIGHT);
        myhabits.add(temp2);

        cardList.setAdapter(new CardAdapter(getActivity(),myhabits));

        return rootView;
    }

}
