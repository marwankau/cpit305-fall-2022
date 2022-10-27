package lecture16.example1;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;

public class Demo {
    public static void main(String[] args) {
        ArrayBlockingQueue<File> queue = new ArrayBlockingQueue<>(100);

        Thread[] threads = new Thread[11];

        threads[10] = new Producer(queue, "C://Users/00006639/Documents");
        threads[10].start();

        for (int i = 0; i < 10; i++) {
            threads[i] = new Consumer(queue, threads, "Marwan");
            threads[i].start();
        }
    }
}
