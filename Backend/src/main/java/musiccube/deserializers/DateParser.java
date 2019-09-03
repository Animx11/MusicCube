package musiccube.deserializers;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateParser {
    protected DateParser() {}
    public static Date parseDate(String dateString) {
        String[] dateArr = dateString.split("-");
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.set(Calendar.HOUR,0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);
        switch (dateArr.length) {
            case 3:
                calendar.set(Calendar.YEAR,Integer.parseInt(dateArr[0]));
                calendar.set(Calendar.MONTH,Integer.parseInt(dateArr[1]) -1);
                calendar.set(Calendar.DAY_OF_MONTH,Integer.parseInt(dateArr[2]));
                break;
            case 2:
                calendar.set(Calendar.YEAR,Integer.parseInt(dateArr[0]));
                calendar.set(Calendar.MONTH,Integer.parseInt(dateArr[1]) -1);
                calendar.set(Calendar.DAY_OF_MONTH,1);
                break;
            case 1:
                calendar.set(Calendar.YEAR,Integer.parseInt(dateArr[0]));
                calendar.set(Calendar.MONTH,0);
                calendar.set(Calendar.DAY_OF_MONTH,1);
                break;
            default:
                break;
        }
        return calendar.getTime();
    }
}
