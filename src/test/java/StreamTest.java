import org.junit.Test;

import java.util.Arrays;

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
}
