package Sem_3;

/*Задание №4
1. Напишите метод,на вход которого подаётся двумерный строковый массив
размером 4х4. При подаче массива другого размера необходимо бросить
исключение MyArraySizeException.
2. Далееметод долженпройтисьпо всем элементам массива,преобразоватьв
int и просуммировать. Если в каком-то элементе массива преобразование
не удалось (например, в ячейке лежит символ или текст вместо числа),
должно быть брошено исключение MyArrayDataException с детализацией, в
какой именно ячейкележат неверныеданные.
3. В методе main() вызвать полученный метод, обработать возможные
исключения MyArraySizeException и MyArrayDataException и вывести
результатрасчета (сумму элементов,при условиичто подали на вход
корректныймассив).*/
public class Task4 {
    public static void main(String[] args) {
        String[][] arr = { { "1", "2", "3", "4" }, { "1", "2", "qazwsxedc", "4" }, { "1", "2", "3", "4" },
                { "1", "2", "3", "4" } };
        System.out.println(sumArray(arr));
    }

    public static int sumArray(String[][] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            if (array.length != 4 || array[i].length != 4)
                throw new MyArraySizeException();
            for (int j = 0; j < array.length; j++) {
                try {
                    sum += Integer.parseInt(array[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException(i, j);
                }
            }
        }
        return sum;
    }
}

class MyArraySizeException extends RuntimeException {
    public MyArraySizeException(int row, int colum) {
        super("Массив должен быть 4х4,размеры вашего массив -> " + row + "x" + colum);
    }

    public MyArraySizeException() {
        super("Массив должен быть 4х4");
    }
}

class MyArrayDataException extends NumberFormatException {
    public MyArrayDataException() {
        super("В каком-то элементе массива преобразование не удалось");
    }

    public MyArrayDataException(int i, int j) {
        super("Преобразование не удалось, индексы ->" + i + ":" + j);
    }
}