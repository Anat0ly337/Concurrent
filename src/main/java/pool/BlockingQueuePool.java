package pool;

import java.util.concurrent.ArrayBlockingQueue;

public class BlockingQueuePool implements Pool{

    private final ArrayBlockingQueue<Object> queue;

    public BlockingQueuePool(int size) {
        this.queue = new ArrayBlockingQueue<>(size);
    }

    @Override
    public Object get() {
        try {
            return queue.take();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void put(Object object) {
        try {
            queue.put(object);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        return queue.size();
    }
}
