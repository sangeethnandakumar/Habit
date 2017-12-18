package amazinginsidestudios.habit;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sangeeth Nandakumar on 16-12-2017.
 */

public class HistoryAdapter extends BaseAdapter {
    List<String> histories;
    Context context;
    View v;
    TextView day, week, month_year, data;

    public HistoryAdapter(List<String> histories, Context context) {
        this.histories = histories;
        this.context = context;
    }

    @Override
    public int getCount() {
        return histories.size();
    }

    @Override
    public Object getItem(int i) {
        return histories.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        v = View.inflate(context, R.layout.history_item, null);
        day = v.findViewById(R.id.history_day);
        week = v.findViewById(R.id.history_week);
        month_year = v.findViewById(R.id.history_month_year);
        data = v.findViewById(R.id.history_data);

        day.setText(histories.get(i).split("#")[0]);
        week.setText(histories.get(i).split("#")[1]);
        month_year.setText(histories.get(i).split("#")[2] + " " + histories.get(i).split("#")[3]);
        data.setText(histories.get(i).split("#")[4]);
        return v;
    }
}
