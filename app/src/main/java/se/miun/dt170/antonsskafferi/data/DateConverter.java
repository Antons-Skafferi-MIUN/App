package se.miun.dt170.antonsskafferi.data;

import android.icu.text.SimpleDateFormat;
import android.icu.util.GregorianCalendar;
import android.icu.util.TimeZone;

import java.text.ParseException;
import java.util.Date;

public class DateConverter {
    SimpleDateFormat formatHHMM;
    SimpleDateFormat formatYYYYMMDD;
    SimpleDateFormat standard;

    public DateConverter() {
        formatHHMM = new SimpleDateFormat("HH:mm"); // MMMM for full month name
        formatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd"); // MMMM for full month name
        standard = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    }
    public String formatStandard(String dateString){
        Date date = null;
        try {
            date = standard.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return standard.format(date);
    }
    public String getCurrentTime() {
        Date time = GregorianCalendar.getInstance(TimeZone.getDefault()).getTime();
        String currentTime = standard.format(time);
        return currentTime;
    }

    public String formatHHMM(String dateString) {
        Date date = null;
        try {
            date = standard.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formatHHMM.format(date);
    }

    public String formatYYYYMMDD(String dateString) {
        Date date = null;
        try {
            date = standard.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatYYYYMMDD.format(date);
    }

    /**
     * Compares two dates
     *
     * @param dateOne
     * @param dateTwo
     * @return
     */
    public boolean compareDates(String dateOne, String dateTwo) {
        return formatYYYYMMDD(dateOne).equals(formatYYYYMMDD(dateTwo));
    }
    public String YYYYMMDDParser(int year, int month, int day){
        String parsedString = Integer.toString(year) + "-";
        if(month < 10){
            parsedString += "0";
        }
        parsedString += Integer.toString(month) + "-";
        if (day < 10){
            parsedString += "0";
        }
        parsedString += Integer.toString(day);
        return parsedString;
    }
    public String HHMMParser(int hour, int minute){
        String parsedString = "";
        if(hour < 10){
            parsedString += "0";
        }
        parsedString += Integer.toString(hour) + ":";
        if (minute < 10){
            parsedString += "0";
        }
        parsedString += Integer.toString(minute);
        return parsedString;
    }
}
