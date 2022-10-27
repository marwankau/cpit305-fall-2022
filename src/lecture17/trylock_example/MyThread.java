package lecture17.trylock_example;

import java.util.concurrent.locks.ReentrantLock;

public class MyThread extends Thread {

    static ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        if (lock.tryLock()) {
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
        
        System.out.printf("%s is terminated\n", Thread.currentThread().getName());
    }
    
}
