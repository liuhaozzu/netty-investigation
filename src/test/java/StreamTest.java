import org.junit.Test;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Author Administrator
 * @create 2019/3/2 0002 22:46
 */
public class StreamTest {

    @Test
    public void test() {
        String[] abc = {"abc", "a", "aaa"};
        String[] abcArr = Arrays.stream(abc).map(item->item+":"+item).toArray(String[]::new);
        System.out.println(Arrays.toString(abcArr));
    }

    @Test
    public void binaryTest() {
        System.out.println(Integer.toBinaryString(4));
        System.out.println(Integer.toBinaryString(-4));

    }

    @Test
    public void overflowTest() {
        System.out.println(Integer.MIN_VALUE);
        final AtomicInteger idx = new AtomicInteger(Integer.MAX_VALUE-3);
        while (true) {
            System.out.println(idx.getAndIncrement() % Integer.MAX_VALUE);
        }

    }
}
