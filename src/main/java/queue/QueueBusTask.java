package queue;

import java.util.Queue;

public class QueueBusTask {

    static Object lock = new Object();

    public static void main(String[] args) {

        int BOUND = 10;
        int N_PRODUCERS = 4;
        int N_CONSUMERS = Runtime.getRuntime().availableProcessors();
        int poisonPill = Integer.MAX_VALUE;
        int poisonPillPerProducer = N_CONSUMERS / N_PRODUCERS;
        int mod = N_CONSUMERS % N_PRODUCERS;

        Queue<Integer> queue = new CustomQueue<>(BOUND);

        Thread producer = new Thread(() -> {

            for (int i = 0; i < N_PRODUCERS; i++) {
                queue.add(i);
            }
        });

        Thread consumer = new Thread(() -> {

            for (int i = 0; i < N_CONSUMERS; i++) {
                Integer ielement = queue.poll();
                System.out.println("consumedElement = " + ielement);
            }
        });

        producer.setName("PRODUCER");
        consumer.setName("CONSUMER");

        producer.start();
        consumer.start();
    }
}
