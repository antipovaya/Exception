package Home_Work_3;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class InvalidDataFormatException extends Exception {
    public InvalidDataFormatException(String message) {
        super(message);
    }
}

public class Main {
    // Кодировка по умл.
    private static String cp = System.getProperty("console.encoding","Cp866");

    private static final String DATE_REGEX = "^(0[1-9]|[12][0-9]|3[01])\\.(0[1-9]|1[0-2])\\.\\d{4}$"; // для использования регуляных выражений
    private static final String PHONE_REGEX = "^\\d+$";
    private static final String GENDER_REGEX = "^[fm]$";

    public static void main(String[] args) {
        try {
            // чтение данных с указанием кодировки, для решения проблем с кириллицей
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in, cp));

            System.out.println("Введите данные (Фамилия Имя Отчество Дата_рождения(формат dd.mm.yyyy) Номер_телефона Пол(символ латиницей f или m)):");
            String inputData = reader.readLine();

            String[] data = inputData.split(" ");
            if (data.length != 6) {
                throw new InvalidDataFormatException("Неверное количество данных. Требуется 6 параметров.");
            }

            validateString(data[0], "Фамилия");
            validateString(data[1], "Имя");
            validateString(data[2], "Отчество");
            validateDate(data[3]);
            validatePhoneNumber(data[4]);
            validateString(data[5], "Пол", GENDER_REGEX);

            String surname = data[0];
            String name = data[1];
            String patronymic = data[2];
            String birthDate = data[3];
            String phoneNumber = data[4];
            String gender = data[5];

            String fileName = surname + ".txt";
            String fileContent = surname + " " + name + " " + patronymic + " " + birthDate + " " + phoneNumber + " " + gender;

            try (FileWriter fileWriter = new FileWriter(fileName, true)) {
                fileWriter.write(fileContent + "\n");
                System.out.println("Данные успешно записаны в файл " + fileName);
                fileWriter.close();
            } catch (IOException e) {
                System.err.println("Ошибка при записи в файл: " + e.getMessage());
            }
        } catch (InvalidDataFormatException e) {
            System.err.println("Ошибка в формате данных: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void validateString(String value, String fieldName, String regex) throws InvalidDataFormatException {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);

        if (!matcher.matches()) {
            throw new InvalidDataFormatException(fieldName + " имеет неверный формат.");
        }
    }

    private static void validateString(String value, String fieldName) throws InvalidDataFormatException {
        validateString(value, fieldName, "^.+$");
    }

    private static void validateDate(String value) throws InvalidDataFormatException {
        validateString(value, "Дата_рождения", DATE_REGEX);
    }

    private static void validatePhoneNumber(String value) throws InvalidDataFormatException {
        validateString(value, "Номер_телефона", PHONE_REGEX);
    }
}