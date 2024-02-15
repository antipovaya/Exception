package Sem_3;

import java.io.FileNotFoundException;

public class Task3 {
    public static void main(String[] args) {
        try {
            System.out.println(div(5, 0));
        } catch (DivByZero e) {
            System.out.println(e.getMessage());
        }
    }

    public static double div(int a, int b) {
        if (b == 0)
            throw new DivByZero();
        return a / b;
    }
}

class DivByZero extends ArithmeticException {
    public DivByZero() {
        super("Деление на 0 запрещено");
    }
}

class ArrayExcption extends Exception {
    public ArrayExcption(int n) {
        super("Выход за пределы массива, при обращении по интексу " + n);
    }

    public ArrayExcption() {
        super("Выход за пределы массива");
    }
}

class NotFile extends FileNotFoundException {
    public NotFile(String path) {
        super("Открыть несуществующий файл нельзя, ваш путь к фалу -> " + path);
    }

    public NotFile() {
        super("Открыть несуществующий файл нельзя");
    }

}