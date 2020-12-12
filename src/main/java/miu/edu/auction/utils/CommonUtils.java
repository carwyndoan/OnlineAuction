package miu.edu.auction.utils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CommonUtils {
    public static long calculateDuration(LocalDateTime toDate){
        LocalDateTime fromDate = LocalDateTime.now();
        LocalDateTime tempDateTime = LocalDateTime.from(fromDate);
        long days = tempDateTime.until(toDate, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);
        long hours = tempDateTime.until(toDate, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);
        long minutes = tempDateTime.until(toDate, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);
        long seconds = tempDateTime.until(toDate, ChronoUnit.SECONDS);
        return (days * 24 * 60 + hours * 60 + minutes) * 60 + seconds;
    }

    public static String explainDuration(LocalDateTime toDate){
        LocalDateTime fromDate = LocalDateTime.now();
        LocalDateTime tempDateTime = LocalDateTime.from(fromDate);
        long days = tempDateTime.until(toDate, ChronoUnit.DAYS);
        tempDateTime = tempDateTime.plusDays(days);
        long hours = tempDateTime.until(toDate, ChronoUnit.HOURS);
        tempDateTime = tempDateTime.plusHours(hours);
        long minutes = tempDateTime.until(toDate, ChronoUnit.MINUTES);
        tempDateTime = tempDateTime.plusMinutes(minutes);
        long seconds = tempDateTime.until(toDate, ChronoUnit.SECONDS);
        StringBuilder stringBuilder = new StringBuilder();
        if(days > 0)
            stringBuilder.append(days).append("d ");
        if(hours > 0)
            stringBuilder.append(hours).append("h ");
        if(minutes > 0)
            stringBuilder.append(minutes).append("m ");
        if(seconds > 0)
            stringBuilder.append(seconds).append("s ");
        stringBuilder.append("left");
        return stringBuilder.toString();
    }
}
