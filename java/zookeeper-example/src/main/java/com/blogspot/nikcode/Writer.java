package com.blogspot.nikcode;

import java.nio.charset.Charset;
import java.util.Random;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author nshmakov
 */
public class Writer {

    private static final String NODE = "/data";

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("localhost:2181", 10000, null);
        removeNodeIfExists(NODE, zk);
        writeOneMessage(NODE, zk);
        writeNMessages(10, NODE, zk);
        zk.close();
    }

    private static void writeOneMessage(String node, ZooKeeper to) throws Exception {
        to.create(node, "test-data".getBytes(Charset.forName("UTF-8")), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    }

    private static void writeNMessages(int n, String node, ZooKeeper to) throws Exception {
        for (int i = 0; i < n; i++) {
            to.create(node + "/children-" + new Random().nextLong(),
                    ("test-data-" + i).getBytes(Charset.forName("UTF-8")), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }

    private static void removeNodeIfExists(String node, ZooKeeper in) throws Exception {
        if (in.exists(node, false) != null) {
            for (String children : in.getChildren(node, false)) {
                in.delete(node + "/" + children, -1);
            }
            in.delete(node, -1);
        }
    }
}
