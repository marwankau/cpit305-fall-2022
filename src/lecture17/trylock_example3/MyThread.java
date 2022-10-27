package lecture17.trylock_example3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread {

    static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(3, TimeUnit.SECONDS)) {
                try {
                    for (int i = 0; i < 2; i++) {
                        System.out.printf("%s is runnable\n", Thread.currentThread().getName());
                        Thread.sleep(1000);
                    }
                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {}
        
        System.out.printf("%s is terminated\n", Thread.currentThread().getName());
    }
    
}
