package Demo;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalTime;

public class TimeConverterTest {
    private BritishTimeConverter converter;
    
    @BeforeEach
    void setUp() {
        converter = new BritishTimeConverter();
    }
    
    @Test
    void testSpecialTimes() {
        assertEquals("midnight", converter.convertTimeToWords("0:00"));
        assertEquals("noon", converter.convertTimeToWords("12:00"));
    }
    
    @Test
    void testOClockTimes() {
        assertEquals("one o'clock", converter.convertTimeToWords("1:00"));
        assertEquals("two o'clock", converter.convertTimeToWords("14:00"));
    }
    
    @Test
    void testPastTimes() {
        assertEquals("five past two", converter.convertTimeToWords("2:05"));
        assertEquals("ten past three", converter.convertTimeToWords("3:10"));
        assertEquals("quarter past four", converter.convertTimeToWords("4:15"));
        assertEquals("twenty past five", converter.convertTimeToWords("5:20"));
        assertEquals("twenty-five past six", converter.convertTimeToWords("6:25"));
        assertEquals("half past seven", converter.convertTimeToWords("7:30"));
    }
    
    @Test
    void testToTimes() {
        assertEquals("twenty-five to eight", converter.convertTimeToWords("7:35"));
        assertEquals("twenty to nine", converter.convertTimeToWords("8:40"));
        assertEquals("quarter to ten", converter.convertTimeToWords("9:45"));
        assertEquals("ten to eleven", converter.convertTimeToWords("10:50"));
        assertEquals("five to twelve", converter.convertTimeToWords("11:55"));
    }
    
    @Test
    void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convertTimeToWords("25:00");
        });
        
        assertThrows(IllegalArgumentException.class, () -> {
            converter.convertTimeToWords("invalid");
        });
    }
}
public class TimeConverterTest {

}
