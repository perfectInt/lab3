import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String surname = scanner.next();
        String name = scanner.next();
        String patronymic = scanner.next();
        String birthdayStr = scanner.next();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(birthdayStr);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate birthday = LocalDate.parse(birthdayStr, formatter);

        String initials = name.charAt(0) + "." + patronymic.charAt(0) + ".";
        String gender = patronymic.charAt(patronymic.length() - 1) == 'ч' ? "М" : "Ж";
        String age = getAge(birthday);

        System.out.printf("%s %s %s %s%n", surname, initials, gender, age);
    }

    private static String getAge(LocalDate birthday) {
        LocalDate today = LocalDate.now();
        Period period = Period.between(birthday, today);
        int years = period.getYears();

        int lastDigit = years % 10;
        if (lastDigit == 1 && (years % 100) != 11) {
            return years + " год";
        } else if (lastDigit >= 2 && lastDigit <= 4 && (years < 12 || years > 14)) {
            return years + " года";
        } else {
            return years + " лет";
        }
    }
}