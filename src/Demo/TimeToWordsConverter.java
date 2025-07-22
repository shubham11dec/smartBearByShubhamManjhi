package Demo;

import java.time.LocalTime;

public class TimeToWordsConverter {
    private static final String[] NUMBERS = {
        "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
        "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
        "seventeen", "eighteen", "nineteen", "twenty"
    };
    
    private static final String[] TENS = {
        "", "", "twenty", "thirty", "forty", "fifty"
    };
    
    public String convert(LocalTime time) {
        int hour = time.getHour();
        int minute = time.getMinute();
        
        // Handle special cases first
        if (hour == 0 && minute == 0) {
            return "midnight";
        }
        if (hour == 12 && minute == 0) {
            return "noon";
        }
        
        return convertRegularTime(hour, minute);
    }
    
    private String convertRegularTime(int hour, int minute) {
        if (minute == 0) {
            return convertHourToWords(hour) + " o'clock";
        }
        
        if (minute <= 30) {
            return convertMinutesAfter(minute, hour);
        } else {
            return convertMinutesBefore(60 - minute, hour + 1);
        }
    }
    
    private String convertMinutesAfter(int minute, int hour) {
        String minuteWords = convertMinuteToWords(minute);
        String hourWords = convertHourToWords(hour);
        
        if (minute == 15) {
            return "quarter past " + hourWords;
        } else if (minute == 30) {
            return "half past " + hourWords;
        } else {
            return minuteWords + " past " + hourWords;
        }
    }
    
    private String convertMinutesBefore(int minutesBefore, int nextHour) {
        // Handle hour overflow
        if (nextHour > 23) {
            nextHour = 0;
        }
        
        String minuteWords = convertMinuteToWords(minutesBefore);
        String hourWords = convertHourToWords(nextHour);
        
        if (minutesBefore == 15) {
            return "quarter to " + hourWords;
        } else {
            return minuteWords + " to " + hourWords;
        }
    }
    
    private String convertHourToWords(int hour) {
        // Convert 24-hour to 12-hour format
        int displayHour = hour == 0 ? 12 : (hour > 12 ? hour - 12 : hour);
        return NUMBERS[displayHour];
    }
    
    private String convertMinuteToWords(int minute) {
        if (minute <= 20) {
            return NUMBERS[minute];
        } else if (minute < 30) {
            return TENS[2] + (minute % 10 > 0 ? "-" + NUMBERS[minute % 10] : "");
        } else {
            // For minutes like 32, we use "thirty-two" format
            int tens = minute / 10;
            int ones = minute % 10;
            return TENS[tens] + (ones > 0 ? "-" + NUMBERS[ones] : "");
        }
    }
}
