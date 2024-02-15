package Sem_3;

import java.io.IOException;

public class Task2Counter implements AutoCloseable {
    private Integer counter = 0;

    public Integer add() throws IOException { // это значит, что ошибку мы будем обрабатывать, когда будем вызываать метод в майн
        checkClose();
        return ++counter;
    }

    private void checkClose() throws IOException { // это значит, что ошибку мы будем обрабатывать, когда будем вызываать метод в майн
        if (counter == null)
            throw new IOException("Экземпляр закрыт");
    }

    @Override
    public void close() {
        System.out.println("метод close");
        counter = null;
    }
}
