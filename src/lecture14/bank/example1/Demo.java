package lecture14.bank.example1;

public class Demo {
    public static void main(String[] args) {
        Bank bank = new Bank(10, 1000);

        ATM[] atms = new ATM[20];
        for (int i = 0; i < 10; i++ ){
            atms[i] = new ATM(bank, i, true);
            atms[i].setName("Writer " + i);
            atms[i].start();
        }
        for (int i = 10; i < atms.length; i++ ){
            atms[i] = new ATM(bank, i, false);
            atms[i].setName("Reader " + i);
            atms[i].start();
        }


        // for (int i = 0; i < 1000; i++) {
        //     System.out.printf("balance: %d\n", alinma.getTotal());
        // }
        // alinma.displayAccounts();
    }
}
