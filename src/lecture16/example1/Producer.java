package lecture16.example1;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;

public class Producer extends Thread {

    private ArrayBlockingQueue<File> queue;
    private String startingDirectory;
    final static File DUMMY_FILE = new File("");

    public Producer(ArrayBlockingQueue<File> queue, String startingDirectory) {
        this.queue = queue;
        this.startingDirectory = startingDirectory;
    }

    @Override
    public void run() {
        File file = new File(startingDirectory);
        traverse(file);
        try {
            queue.put(DUMMY_FILE);
        } catch (InterruptedException e) { }
    }

    private void traverse(File directory) {
        File[] files = directory.listFiles();
        if (!Thread.currentThread().isInterrupted()) {
            for (File file : files) {
                try {
                    if (file.isFile()) {
                        try {
                            queue.put(file);
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            break;
                        }
                    } else {
                        traverse(file);
                    }
                } catch (NullPointerException e) {}
            }
        }
    }
    
}
