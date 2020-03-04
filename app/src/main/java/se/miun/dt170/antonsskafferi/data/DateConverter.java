package se.miun.dt170.antonsskafferi.data;

import android.icu.text.SimpleDateFormat;
import android.icu.util.GregorianCalendar;
import android.icu.util.TimeZone;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Date;

public class DateConverter {
    SimpleDateFormat formatHHMM;
    SimpleDateFormat formatYYYYMMDD;

    public DateConverter(){
        formatHHMM = new SimpleDateFormat("HH:mm"); // MMMM for full month name
        formatYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd"); // MMMM for full month name
    }
    public String getCurrentTime(){
        Date time = GregorianCalendar.getInstance(TimeZone.getDefault()).getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX"); // IMPORTANT PATTERN, DON'T CHANGE
        String currentTime = simpleDateFormat.format(time);
        return currentTime;
    }

    public String formatHHMM(String dateString){
        Date date = null;

        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return formatHHMM.format(date);
    }

    public String formatYYYYMMDD(String dateString){
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatYYYYMMDD.format(date);
    }

    /**
     * Compares two dates
     * @param dateOne
     * @param dateTwo
     * @return
     */
    public boolean compareDates(String dateOne, String dateTwo){
        return formatYYYYMMDD(dateOne).equals(formatYYYYMMDD(dateTwo));
    }
}
