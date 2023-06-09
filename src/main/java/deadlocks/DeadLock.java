package deadlocks;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
    private static Lock lock = new ReentrantLock();
    private static List<Integer> list = new ArrayList<>();
    private static Condition condition = lock.newCondition();

    private static int count;

    private static int count2;

    public static void main(String[] args) {
        new MyThread2().start();
        new MyThread1().start();
      //  new MyThread3().start();
    }

    static class MyThread1 extends Thread {
        @Override
        public void run() {

            while (count < 100) {
                lock.lock();
                    condition.signal();
                    lock.unlock();
                int cnt = count++;
                list.add(cnt);
                System.out.println("Count = " + cnt);
                condition.signal();
                lock.unlock();
            }
        }
    }

    static class MyThread2 extends Thread {
        @Override
        public void run() {

            while (true) {
                try {
                    lock.lock();
                    Thread.sleep(500);
                    condition.await();

                    System.out.println(count2++);

                    System.out.println("sum = " + list.stream()
                        .mapToInt(Integer::intValue)
                        .sum());

                    condition.signalAll();
                    lock.unlock();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    static class MyThread3 extends Thread {
        @Override
        public void run() {
            try {
                lock.lock();
                condition.await();

                System.out.println("Sum squares = " + list.stream()
                    .map(Double::valueOf)
                        .map(Math::sqrt)
                    .mapToDouble(Double::intValue)
                    .sum());

                lock.unlock();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

