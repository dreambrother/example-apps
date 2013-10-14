package com.blogspot.nikcode;

import org.apache.zookeeper.ZooKeeper;

/**
 * @author nshmakov
 */
public class Reader {

    public static void main(String[] args) throws Exception {
        ZooKeeper zk = new ZooKeeper("localhost:2181", 10000, null);
        printDataInNode("/data", zk);
        printChildNodes("/data", zk);
        zk.close();
    }

    private static void printDataInNode(String node, ZooKeeper zk) throws Exception {
        System.out.println(new String(zk.getData(node, false, null)));
    }

    private static void printChildNodes(String parentNode, ZooKeeper zk) throws Exception {
        for (String child : zk.getChildren(parentNode, false)) {
            System.out.println(new String(zk.getData(parentNode + "/" + child, false, null)));
        }
    }
}
