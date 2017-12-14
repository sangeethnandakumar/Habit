package amazinginsidestudios.habit.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Sangeeth Nandakumar on 13-12-2017.
 */

public class Spanner {
    public long dayDifference(String fromDate, String toDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //FROM DATE
        Date from = null;
        try {
            from = formatter.parse(fromDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar fromCalender = Calendar.getInstance();
        fromCalender.setTime(from);
        //TO DATE
        Date to = null;
        try {
            to = formatter.parse(toDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar toCalender = Calendar.getInstance();
        toCalender.setTime(to);
        //CALCULATE
        long diff = toCalender.getTimeInMillis() - fromCalender.getTimeInMillis();
        long days = diff / (24 * 60 * 60 * 1000);
        return days;
    }

    public long dayDifference(Date fromDate, Date toDate) {
        Calendar fromCalender = Calendar.getInstance();
        fromCalender.setTime(fromDate);
        Calendar toCalender = Calendar.getInstance();
        toCalender.setTime(toDate);
        //CALCULATE
        long diff = toCalender.getTimeInMillis() - fromCalender.getTimeInMillis();
        long days = diff / (24 * 60 * 60 * 1000);
        return days;
    }

    public long dayDifference(String thatDay) {
        thatDay = thatDay.split(" ")[0];
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date d = null;
        try {
            d = formatter.parse(thatDay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar thatCallender = Calendar.getInstance();
        thatCallender.setTime(d);
        Calendar todayCallender = Calendar.getInstance();
        long diff = todayCallender.getTimeInMillis() - thatCallender.getTimeInMillis();
        long days = diff / (24 * 60 * 60 * 1000);
        return days;
    }
}
