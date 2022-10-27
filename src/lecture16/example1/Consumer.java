package lecture16.example1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

public class Consumer extends Thread {
    private ArrayBlockingQueue<File> queue;
    private Thread[] threads;
    private String word;

    public Consumer(ArrayBlockingQueue<File> queue, Thread[] threads, String word) {
        this.queue = queue;
        this.threads = threads;
        this.word = word;
    }

    @Override
    public void run() {
        search();
    }

    private void search() {
        String line;
        File file;
        while (!Thread.currentThread().isInterrupted()) {
            try {
                file = queue.take();
            } catch (InterruptedException e) {
                break;
            }
            if (file != Producer.DUMMY_FILE) {
                // open file for reading and serach for word
                try (Scanner scan = new Scanner(file)) {
                    while (scan.hasNext()) {
                        line = scan.nextLine();
                        if (line.contains(word)) {
                            System.out.println(file);
                            // we find the word, so send signal to all threads to stop searching
                            for (Thread thread : threads) {
                                thread.interrupt();
                            }
                            break;
                        }
                    }
                } catch (FileNotFoundException e) {}
            } else {
                try {
                    queue.put(file);
                } catch (InterruptedException e) {}
                break;
            }
        }
    }
    
}
