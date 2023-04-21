package pool;

public interface Pool {
    Object get();

    void put(Object object);

    int size();
}
