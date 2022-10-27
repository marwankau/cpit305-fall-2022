package lecture17.example1;

public class Demo {
    public static void main(String[] args) {
        Bank bank = new Bank(10, 1000);

        ATM[] atms = new ATM[10];
        for (int i = 0; i < atms.length; i++ ){
            atms[i] = new ATM(bank, i);
            atms[i].start();
        }


        for (int i = 0; i < 1000; i++) {
            System.out.println();
            bank.displayAccounts();
            System.out.printf("\ntotal balance: %d\n", bank.getTotal());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) { }
        }
    }
}
