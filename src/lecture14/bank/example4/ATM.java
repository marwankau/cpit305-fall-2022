package lecture14.bank.example4;

public class ATM extends Thread {
    private Bank bank;
    private int from;

    public ATM(Bank bank, int from) {
        this.bank = bank;
        this.from = from;
    }

    @Override
    public void run() {
        int to, amount;
        while (true) {
            to = (int)(Math.random() * bank.accounts.length);
            amount = (int)(Math.random() * 1000);

            bank.transfer(from, to, amount);
        }
    }
}
