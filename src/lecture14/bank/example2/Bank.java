package lecture14.bank.example2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Bank {
    int[] accounts;
    int init_balance;
    ReentrantReadWriteLock rw;
    Lock readerLock;
    Lock writerLock;
    Condition cond;


    public Bank(int number_of_account, int init_balance) {
        accounts = new int[number_of_account];
        this.init_balance = init_balance;
        for (int i = 0; i < number_of_account; i++) {
            accounts[i] = init_balance;
        }
        rw = new ReentrantReadWriteLock();
        readerLock = rw.readLock();
        writerLock = rw.writeLock();
        cond = writerLock.newCondition();
    }

    public void transfer(int from, int to, int amount) {
        writerLock.lock();
        // System.out.printf("%s: transfer: start\n", Thread.currentThread().getName());
        try {
            while (accounts[from] < amount) {
                cond.await();
            }
            accounts[from] -= amount;  // LD accounts[from]; DEC amount; STORE accounts[from]
            accounts[to] += amount;

            cond.signalAll();
        } catch (InterruptedException e) {
        } finally {
            // System.out.printf("%s: transfer: end\n", Thread.currentThread().getName());
            writerLock.unlock();
        }
    }

    public int getTotal() {
        int sum = 0;
        readerLock.lock();

        // System.out.printf("%s: getTotal: start\n", Thread.currentThread().getName());
        try {
            for (int account: accounts) {
                sum += account;
            }
        } finally {
            // System.out.printf("%s: getTotal: end\n", Thread.currentThread().getName());
            readerLock.unlock();
        }
        return sum;
    }

    public void displayAccounts() {
        for (int i = 0; i < accounts.length; i++) {
            System.out.printf("[%d]: %d\n", i, accounts[i]);
        }
    }
}
