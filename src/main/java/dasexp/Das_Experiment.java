package dasexp;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Map;

public class Das_Experiment {

    private static final Object object = new Object();

    public static void main(String[] args) {

        Map<Integer, Integer> map = new ThreadSafeMap<>();

        map.put(1, 1);

        Iterator<Integer> it = map.values().iterator();

        System.out.println(it.next());

        map.put(2, 2);

        try {
            System.out.println(it.next());
            map.put(3,3);
            System.out.println(it.next());
            System.out.println("SUM IS = " + Arrays.stream(map
                    .values()
                    .toArray())
                .mapToInt(o -> Integer.parseInt(String.valueOf(o)))
                .sum());

        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }
    }
}
