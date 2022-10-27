package lecture16.introduction;

import java.util.concurrent.ArrayBlockingQueue;

public class Demo {
    public static void main(String[] args) {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(3);

        try {
            queue.put("Ahmed");
            queue.put("Abdullah");
            queue.put("Khalid");

            
            System.out.println(queue.take());
            System.out.println(queue.take());
            System.out.println(queue.take());
        } catch (InterruptedException e) {}
    }
}
