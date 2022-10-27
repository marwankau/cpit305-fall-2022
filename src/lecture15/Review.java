package lecture15;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Review {
    public static void main(String[] args) {
        File f = new File("test");
        try {
            f.createNewFile();
        } catch (IOException e) {        }

        File newLocation = new File("src/lecture15/test");
        f.renameTo(newLocation);

        try (Scanner scan = new Scanner(System.in)) {

            System.out.println("Enter your name: ");
            String name = scan.nextLine();

            System.out.println("Welcome " + name);
        }

        System.out.println(f.getAbsolutePath());
        try (PrintWriter writer = new PrintWriter(f)) {
            writer.println("Hello");
        } catch (IOException e) {
            System.out.println("Unable to open the file for writing!");
        }

        PrintWriter x = null;
        try {
            x = new PrintWriter(newLocation);
            x.println("Hi");

            x.println(8 / 0);

        } catch (IOException e) {
            System.out.println("Unable to open the file for writing!");
        } finally {
            
            x.close();
        }

        MyThread[] m1 = new MyThread[100];
        OtherThread[] m2 = new OtherThread[100];

        for (int i = 0; i < 100; i++) {
            m1[i] = new MyThread();
            Thread t = new Thread(m1[i]);
            t.start();


            m2[i] = new OtherThread();
            m2[i].start();
        }

        try {
            m2[0].join();  // main thread is in waiting state until m2[0] finish
        } catch (InterruptedException e) { }
        


    }
}
