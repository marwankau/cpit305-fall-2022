package lecture17.example2;


public class Bank {
    int[] accounts;
    int init_balance;


    public Bank(int number_of_account, int init_balance) {
        accounts = new int[number_of_account];
        this.init_balance = init_balance;
        for (int i = 0; i < number_of_account; i++) {
            accounts[i] = init_balance;
        }
    }

    public void transfer(int from, int to, int amount) {
        synchronized (this) {
            while (accounts[from] < amount) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }
            accounts[from] -= amount;  // LD accounts[from]; DEC amount; STORE accounts[from]
            accounts[to] += amount;

            notifyAll();
        }
    }

    public int getTotal() {
        int sum = 0;
        synchronized (this) {
            for (int account: accounts) {
                sum += account;
            }
        }
        return sum;
    }

    public void displayAccounts() {
        synchronized (this) {
            for (int i = 0; i < accounts.length; i++) {
                System.out.printf("[%d]: %d\n", i, accounts[i]);
            }
        }
    }
}
