package Sem_3;

import java.io.IOException;

public class app {
    /**
     * @param args
     */
    public static void main (String[] args) {
        try (Task2Counter counter = new Task2Counter()){
            System.out.println(counter.add());
            counter.add();
            System.out.println(counter.add());
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    } 
}
