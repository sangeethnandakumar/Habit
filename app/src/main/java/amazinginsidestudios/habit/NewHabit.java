package amazinginsidestudios.habit;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.takisoft.datetimepicker.DatePickerDialog;
import com.takisoft.datetimepicker.TimePickerDialog;
import com.takisoft.datetimepicker.widget.DatePicker;
import com.takisoft.datetimepicker.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import amazinginsidestudios.habit.components.Booler;
import amazinginsidestudios.habit.components.Color;
import amazinginsidestudios.habit.components.Habit;
import amazinginsidestudios.habit.components.HabitState;
import amazinginsidestudios.habit.components.HabitTemplate;
import amazinginsidestudios.habit.components.LogLevel;
import amazinginsidestudios.habit.components.PublicVisibility;
import amazinginsidestudios.habit.components.TargetLevel;
import amazinginsidestudios.habit.components.XmlCompressor;
import amazinginsidestudios.habit.engines.Database;
import amazinginsidestudios.habit.utils.Spanner;

public class NewHabit extends AppCompatActivity {
    LinearLayout template;
    TextView template_result;
    LinearLayout loglevel;
    TextView loglevel_result;
    LinearLayout catageory;
    TextView catageory_result;
    LinearLayout color;
    TextView color_result;
    LinearLayout visibility;
    TextView visibility_result;
    LinearLayout log_time;
    TextView log_time_result;
    LinearLayout target;
    TextView target_result;
    EditText already_completed_result;
    TextView account_result;
    Button save;
    ImageButton close;
    EditText name;
    EditText aim_result;

    private void init() {
        template = this.findViewById(R.id.template);
        template_result = this.findViewById(R.id.template_result);
        loglevel = this.findViewById(R.id.log_level);
        loglevel_result = this.findViewById(R.id.log_level_result);
        catageory = this.findViewById(R.id.catageory);
        catageory_result = this.findViewById(R.id.catageory_result);
        color = this.findViewById(R.id.color);
        color_result = this.findViewById(R.id.color_result);
        visibility = this.findViewById(R.id.visibility);
        visibility_result = this.findViewById(R.id.visibility_result);
        log_time = this.findViewById(R.id.log_time);
        log_time_result = this.findViewById(R.id.log_time_result);
        target = this.findViewById(R.id.target);
        target_result = this.findViewById(R.id.target_result);
        already_completed_result = this.findViewById(R.id.already_completed_result);
        account_result = this.findViewById(R.id.account_result);
        save = this.findViewById(R.id.save);
        close = this.findViewById(R.id.close);
        name = this.findViewById(R.id.name);
        aim_result = this.findViewById(R.id.aim_result);

        Calendar calendar = Calendar.getInstance();
        String delegate = "yyyy-MM-dd";
        CharSequence out = DateFormat.format(delegate, calendar.getTime());
        target_result.setText(String.valueOf(out));

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(NewHabit.this, "Creating habit", Toast.LENGTH_SHORT).show();
                Habit habit = new Habit();
                habit.setName(name.getText().toString());
                habit.setAccount(account_result.getText().toString());
                habit.setAim(aim_result.getText().toString());
                habit.setCatageory(catageory_result.getText().toString());
                habit.setCloudSynced(Booler.FALSE);
                //Set Color
                switch (color_result.getText().toString()) {
                    case "DEFAULT COLOR":
                        habit.setColor(Color.DEFAULT_COLOR);
                        break;
                    case "BLOOD RED":
                        habit.setColor(Color.BLOOD_RED);
                        break;
                    case "SKY BLUE":
                        habit.setColor(Color.SKY_BLUE);
                        break;
                    case "TOUGH GREEN":
                        habit.setColor(Color.TOUGH_GREEN);
                        break;
                    case "GOLD YELLOW":
                        habit.setColor(Color.GOLD_YELLOW);
                        break;
                    default:
                        habit.setColor(Color.BLOOD_RED);
                        break;
                }
                habit.setCompletedDays(already_completed_result.getText().toString());
                habit.setDateExpired(target_result.getText().toString());
                habit.setDateRemoved("null");
                habit.setDiscloseProgressToPublic(Booler.FALSE);
                habit.setHabitState(HabitState.ACTIVE);
                //Set Template
                switch (template_result.getText().toString()) {
                    case "YES OR NO":
                        habit.setHabitTemplate(HabitTemplate.YES_NO);
                        break;
                    case "RUNNING":
                        habit.setHabitTemplate(HabitTemplate.RUNNING);
                        break;
                    case "ABS":
                        habit.setHabitTemplate(HabitTemplate.ABS);
                        break;
                    case "PUSHUP":
                        habit.setHabitTemplate(HabitTemplate.PUSH_UP);
                        break;
                    default:
                        habit.setHabitTemplate(HabitTemplate.YES_NO);
                        break;
                }
                //Set loglevel
                switch (loglevel_result.getText().toString()) {
                    case "DAILY":
                        habit.setLogLevel(LogLevel.DAILY);
                        break;
                    case "WEEKLY":
                        habit.setLogLevel(LogLevel.WEEKLY);
                        break;
                    case "MONTHLY":
                        habit.setLogLevel(LogLevel.MONTHLY);
                        break;
                    default:
                        habit.setLogLevel(LogLevel.DAILY);
                        break;
                }
                //Total days
                Spanner spanner = new Spanner();
                Calendar calendar = Calendar.getInstance();
                String delegate = "yyyy-MM-dd";
                CharSequence today = DateFormat.format(delegate, calendar.getTime());
                long days = spanner.dayDifference(today.toString(), target_result.getText().toString());
                habit.setTotalDays(String.valueOf(days));
                //Limitation
                habit.setTargetLevel(TargetLevel.LIMITED);

                habit.setDateSynced("null");
                habit.setLogTime(log_time_result.getText().toString());
                //Xml compressing
                String xml = "<?xml encoding='UTF-8' version='1.0'?><habit></habit>'";
                XmlCompressor compressor = new XmlCompressor(xml, false);
                habit.setXmlData(compressor.getXml());
                //Autos
                habit.setFingerprint(generateFingerprint());
                habit.setDateCreated(generateTimestamp());
                habit.setDevice(Build.MANUFACTURER + "|" + Build.BRAND + "|" + Build.MODEL);
                habit.setAppVersion(generateAppVersion());
                habit.setNeverSync(Booler.FALSE);
                //Visibility
                switch (visibility_result.getText().toString()) {
                    case "PRIVATE":
                        habit.setPublicVisibility(PublicVisibility.PRIVATE);
                        break;
                    case "GRAND ON REQUEST":
                        habit.setPublicVisibility(PublicVisibility.GRAND_ON_REQUEST);
                        break;
                    case "PUBLIC":
                        habit.setPublicVisibility(PublicVisibility.PUBLIC);
                        break;
                    default:
                        habit.setPublicVisibility(PublicVisibility.PRIVATE);
                        break;
                }
                //Add to local database
                Database database = new Database(getApplicationContext());
                database.insertHabit(habit);
                //Acknoledge
                Toast.makeText(NewHabit.this, "Habit created", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_habit);
        init();

        template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu menu = new PopupMenu(getApplicationContext(), view);
                menu.inflate(R.menu.menu_template);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        template_result.setText(menuItem.getTitle());
                        return false;
                    }
                });
                menu.show();
            }
        });

        loglevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu menu = new PopupMenu(getApplicationContext(), view);
                menu.inflate(R.menu.menu_log_level);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        loglevel_result.setText(menuItem.getTitle());
                        return false;
                    }
                });
                menu.show();
            }
        });

        log_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getLogTime();
            }
        });

        catageory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu menu = new PopupMenu(getApplicationContext(), view);
                menu.inflate(R.menu.menu_catageory);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        catageory_result.setText(menuItem.getTitle());
                        return false;
                    }
                });
                menu.show();
            }
        });

        target.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getTargetDate();
            }
        });

        color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu menu = new PopupMenu(getApplicationContext(), view);
                menu.inflate(R.menu.menu_color);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        color_result.setText(menuItem.getTitle());
                        return false;
                    }
                });
                menu.show();
            }
        });


        visibility.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final PopupMenu menu = new PopupMenu(getApplicationContext(), view);
                menu.inflate(R.menu.menu_vilibility);
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        visibility_result.setText(menuItem.getTitle());
                        return false;
                    }
                });
                menu.show();
            }
        });


    }

    public void getLogTime() {
        TimePickerDialog logTime = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Date temp = new Date();
                temp.setHours(hourOfDay);
                temp.setMinutes(minute);
                String delegate = "hh:mm aaa";
                CharSequence out = DateFormat.format(delegate, temp.getTime());
                log_time_result.setText(String.valueOf(out));
            }
        }, 12, 0, false);
        logTime.show();
    }

    public void getTargetDate() {
        Calendar cal = Calendar.getInstance();
        DatePickerDialog dpd = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Date temp = new Date();
                temp.setYear(year - 1900);
                temp.setMonth(month);
                temp.setDate(dayOfMonth);
                String delegate = "yyyy-MM-dd";
                CharSequence out = DateFormat.format(delegate, temp.getTime());
                target_result.setText(String.valueOf(out));
            }
        }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
        dpd.show();
    }

    public String generateFingerprint() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    private String generateAppVersion() {
        PackageManager manager = getApplicationContext().getPackageManager();
        PackageInfo info = null;
        try {
            info = manager.getPackageInfo(getApplicationContext().getPackageName(), 0);
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
