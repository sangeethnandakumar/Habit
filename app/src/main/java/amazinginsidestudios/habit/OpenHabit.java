package amazinginsidestudios.habit;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

import amazinginsidestudios.habit.components.Habit;
import amazinginsidestudios.habit.components.HabitTemplate;
import devlight.io.library.ArcProgressStackView;

public class OpenHabit extends AppCompatActivity {
    ViewPager viewPager;
    Habit habit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_habit);

        final ArrayList<ArcProgressStackView.Model> models = new ArrayList<>();
        models.add(new ArcProgressStackView.Model("Progress", 80, Color.RED));
        models.add(new ArcProgressStackView.Model("Health", 50, Color.DKGRAY));
        models.add(new ArcProgressStackView.Model("Activity", 75, Color.GRAY));

        final ArcProgressStackView arcProgressStackView = findViewById(R.id.apsv);
        arcProgressStackView.setModels(models);

        viewPager = findViewById(R.id.viewpager);
        habit = new Habit();
        habit.setHabitTemplate(HabitTemplate.PUSH_UP);
        OpenHabitAdapter openHabitAdapter = new OpenHabitAdapter(getSupportFragmentManager(), habit);
        viewPager.setAdapter(openHabitAdapter);
    }
}
