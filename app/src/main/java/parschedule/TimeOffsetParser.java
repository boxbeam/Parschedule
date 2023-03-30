import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TimeOffsetParser {

    public static Duration parseTimeOffset(String input) {
        Pattern pattern = Pattern.compile("(\\d+)([smhd])");
        Matcher matcher = pattern.matcher(input);

        long seconds = 0;
        while (matcher.find()) {
            String value = matcher.group(1);
            String unit = matcher.group(2);

            switch (unit) {
                case "s":
                    seconds += Long.parseLong(value);
                    break;
                case "m":
                    seconds += Long.parseLong(value) * 60;
                    break;
                case "h":
                    seconds += Long.parseLong(value) * 60 * 60;
                    break;
                case "d":
                    seconds += Long.parseLong(value) * 24 * 60 * 60;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid time unit: " + unit);
            }
        }

        return Duration.ofSeconds(seconds);
    }

    public static Duration diff(Calendar from, Calendar to) {
        LocalTime fromTime = LocalTime.of(from.get(Calendar.HOUR_OF_DAY), from.get(Calendar.MINUTE));
        LocalTime toTime = LocalTime.of(to.get(Calendar.HOUR_OF_DAY), to.get(Calendar.MINUTE));
        return Duration.between(fromTime, toTime);
    }

    public static void main(String[] args) {
        System.out.println(parseTimeOffset("10s"));
        System.out.println(parseTimeOffset("5m10s"));
        System.out.println(parseTimeOffset("1d"));

        Calendar now = Calendar.getInstance();
        Calendar threePM = Calendar.getInstance();
        threePM.set(Calendar.HOUR_OF_DAY, 15);
        System.out.println(diff(now, threePM));
    }
}