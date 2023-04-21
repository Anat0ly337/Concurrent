package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) {
        Pool pool = new BlockingQueuePool(100);

        for (int i = 0; i < 60; i++) {
            pool.put(new Object());
        }

      ExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        for (int i = 0; i < 100; i++) {
            final int iCopy = i;
            executorService.submit(() ->{
                Object o = pool.get();
                System.out.println(iCopy + "Consumed " + o);

                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                pool.put(o);

                System.out.println("returned = " + o);
            });
        }
    }
}
