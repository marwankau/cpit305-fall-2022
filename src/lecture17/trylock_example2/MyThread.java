package lecture17.trylock_example2;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread {

    static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        try {
            if (lock.tryLock(10, TimeUnit.SECONDS)) {
                try {
                    while (true) {
                        System.out.printf("%s is runnable\n", Thread.currentThread().getName());
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {

                } finally {
                    lock.unlock();
                }
            }
        } catch (InterruptedException e) {}
        
        System.out.printf("%s is terminated\n", Thread.currentThread().getName());
    }
    
}
