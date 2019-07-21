package com.liuhaozzu.loadbalance;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**.四个集合（元素类型随意），实现一个分配函数，入参是一个元素。
 该函数将该元素放入其中一个集合，要求无论调用多少次，四个集合的元素数量之比都尽量接近 1:2:3:4。
 这个是模拟真实的一个按权重分配的场景，权重最高的集合优先分配。
 打印出调用N次后的各集合的大小。例如调用3次，调用10次，调用100次等。
 * @Author liuhaozzu
 * @create 2019/7/19 0019 23:47
 */
public class LoadBalance {
    private List<String> list1 = new ArrayList<>();
    private List<String> list2 = new ArrayList<>();
    private List<String> list3 = new ArrayList<>();
    private List<String> list4 = new ArrayList<>();
    HashFunction hf = Hashing.murmur3_128();

    @Test
    public void test3Element() {
        Random random = new Random();
        LoadBalance loadBalance = new LoadBalance();
        for (int i = 0; i < 3; i++) {
            loadBalance.add(Integer.toString(random.nextInt(Integer.MAX_VALUE)));
        }
        System.out.println(loadBalance.toString());
    }
    @Test
    public void test10Element() {
        Random random = new Random();
        LoadBalance loadBalance = new LoadBalance();
        for (int i = 0; i < 10; i++) {
            loadBalance.add(Integer.toString(random.nextInt(Integer.MAX_VALUE)));
        }
        System.out.println(loadBalance.toString());
    }
    @Test
    public void test100Element() {
        Random random = new Random();
        LoadBalance loadBalance = new LoadBalance();
        for (int i = 0; i < 100; i++) {
            loadBalance.add(Integer.toString(random.nextInt(Integer.MAX_VALUE)));
        }
        System.out.println(loadBalance.toString());
    }




    public void add(String item) {
        int hashCode = hf.newHasher().putString(item, StandardCharsets.UTF_8).hash().asInt();
        System.out.println(hashCode);
        if (hashCode == Integer.MIN_VALUE) {
            hashCode = Integer.MAX_VALUE;
        } else if (hashCode<0) {
            hashCode = Math.abs(hashCode);
        }
        int mode = hashCode % 10;
        if (mode > 5|| list4.size()<4) {
            list4.add(item);
        } else if (mode > 2||list3.size()<3) {
            list3.add(item);
        } else if (mode > 0 || list2.size()<2) {
            list2.add(item);
        } else {
            list1.add(item);
        }
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("LoadBalance{");
        sb.append("list1: size=").append(list1.size());
        sb.append(", list2: size=").append(list2.size());
        sb.append(", list3: size=").append(list3.size());
        sb.append(", list4: size=").append(list4.size());
        sb.append('}');
        return sb.toString();
    }
}
