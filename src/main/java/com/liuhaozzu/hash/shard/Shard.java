package com.liuhaozzu.hash.shard;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author: liuhaozzu
 * @date: 2019-07-15 11:45
 */
public class Shard<S> {
    private TreeMap<Long,S> nodes;//虚拟节点
    private List<S> shards;//真实机器节点
    private final int NODE_NUM=100;//每个机器节点关联的虚拟节点个数

    public Shard(List<S> shards) {
        this.shards = shards;
        init();
    }

    /**
     * init the hash circle
     */
    private void init() {
        nodes = new TreeMap<>();
        for (int i = 0; i != shards.size(); i++) {//每个真实机器节点都需要关联虚拟节点
            final S shardInfo = shards.get(i);
            for (int j = 0; j < NODE_NUM; j++) {
                //一个真实机器节点关联NODE_NUM个虚拟节点
                nodes.put(hash("SHARD-" + i + "-NODE-" + j), shardInfo);
            }
        }
    }

    public S getShardInfo(String key) {
        SortedMap<Long, S> tail = nodes.tailMap(hash(key));//沿环的顺时针找到一个虚拟节点
        if (tail.size() == 0) {
            return nodes.get(nodes.firstKey());
        }
        return tail.get(tail.firstKey());//返回该虚拟节点对应的真实机器节点的信息
    }


    private Long hash(String key) {
        ByteBuffer buf = ByteBuffer.wrap(key.getBytes(StandardCharsets.UTF_8));
        int  seed=0x123ABC;
        ByteOrder byteOrder = buf.order();
        buf.order(ByteOrder.LITTLE_ENDIAN);

        long m = 0xc6a4a7935bd1e995L;
        int r = 47;

        long h = seed ^ (buf.remaining() * m);

        long k;

        while (buf.remaining() >= 8) {
            k = buf.getLong();
            k*=m;
            k^=k>>>r;
            k*=m;
            h^=k;
            h*=m;
        }

        if (buf.remaining() > 0) {
            ByteBuffer finish = ByteBuffer.allocate(8).order(ByteOrder.LITTLE_ENDIAN);

            finish.put(buf).rewind();

            h ^= finish.getLong();
            h*=m;
        }

        h^=h>>>r;
        h*=m;
        h^=h>>>r;

        buf.order(byteOrder);
        return h;
    }


}
