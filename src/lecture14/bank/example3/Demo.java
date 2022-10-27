package lecture14.bank.example3;

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
        }
    }
}
