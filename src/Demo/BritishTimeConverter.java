package Demo;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class BritishTimeConverter {
    private final TimeToWordsConverter converter;
    
    public BritishTimeConverter() {
        this.converter = new TimeToWordsConverter();
    }
    
    public String convertTimeToWords(String timeInput) {
        try {
            LocalTime time = LocalTime.parse(timeInput, DateTimeFormatter.ofPattern("H:mm"));
            return converter.convert(time);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format. Please use H:mm format (e.g., 12:30)");
        }
    }
    
    public static void main(String[] args) {
        BritishTimeConverter app = new BritishTimeConverter();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("British Time Converter");
        System.out.println("Enter time in H:mm format (or 'quit' to exit):");
        
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine().trim();
            
            if ("quit".equalsIgnoreCase(input)) {
                break;
            }
            
            try {
                String spokenForm = app.convertTimeToWords(input);
                System.out.println("Spoken form: " + spokenForm);
            } catch (IllegalArgumentException e) {
                System.err.println("Error: " + e.getMessage());
            }
            
            System.out.println("\nEnter another time (or 'quit' to exit):");
        }
        
        scanner.close();
        System.out.println("Goodbye!");
    }
}
