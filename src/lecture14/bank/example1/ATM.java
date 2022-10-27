package lecture14.bank.example1;

public class ATM extends Thread {
    private Bank bank;
    private int from;
    private boolean is_writer;

    public ATM(Bank bank, int from, boolean is_writer) {
        this.bank = bank;
        this.from = from;
        this.is_writer = is_writer;
    }

    @Override
    public void run() {
        if (is_writer) {
            int to, amount;
            while (true) {
                to = (int)(Math.random() * bank.accounts.length);
                amount = (int)(Math.random() * 1000);

                bank.transfer(from, to, amount);
            }
        } else {
            while (true) {
                bank.getTotal();
            }
        }
    }
}
