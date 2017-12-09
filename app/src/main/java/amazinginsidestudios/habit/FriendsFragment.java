package amazinginsidestudios.habit;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import amazinginsidestudios.habit.components.Habit;
import amazinginsidestudios.habit.components.HabitTemplate;


/**
 * A simple {@link Fragment} subclass.
 */
public class FriendsFragment extends Fragment {

    ListView cardFriendsList;
    private View rootView = null;

    public FriendsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getContext(), "Working...", Toast.LENGTH_SHORT).show();
        rootView = inflater.inflate(R.layout.fragment_friends, container, false);

        cardFriendsList=(ListView)rootView.findViewById(R.id.card_friends_list);
        List<Habit> myhabits=new ArrayList<>();

        Habit temp=new Habit(getContext());
        temp.setName("Test");
        temp.setHabitTemplate(HabitTemplate.NIGHT);
        myhabits.add(temp);

        Habit temp2=new Habit(getContext());
        temp2.setName("Read a Book");
        temp2.setHabitTemplate(HabitTemplate.NIGHT);
        myhabits.add(temp2);

        cardFriendsList.setAdapter(new FriendsCardAdapter(getActivity(),myhabits));

        return rootView;
    }
}
