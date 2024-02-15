import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String patch = "C:\\Users\\Кирилл\\Desktop\\rabota\\gb\\Ex\\sem2\\seminar30.01.2024.13.00\\src\\names.txt";
        List<String[]> list = readFile(patch);
        modificationArray(list);
        writeFile(list, patch);
    }

    public static List<String[]> readFile(String path) {
        List<String[]> list = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line = "";
            while ((line = reader.readLine()) != null) {
                list.add(line.split("="));
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
            ;
        }
        return list;
    }

    public static void modificationArray(List<String[]> listNames) {
        for (String[] item : listNames) {
            if (!item[1].equals("?") && !checkNumber(item[1]))
                throw new IllegalArgumentException();
            if (item[1].equals("?"))
                item[1] = String.valueOf(item[0].length());
        }
    }

    public static boolean checkNumber(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void writeFile(List<String[]> listNames, String patch) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(patch));
            for (String[] item : listNames) {
                writer.write(item[0] + "=" + item[1] + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
