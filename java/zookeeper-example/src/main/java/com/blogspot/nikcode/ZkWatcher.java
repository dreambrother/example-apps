package com.blogspot.nikcode;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

/**
 * @author nshmakov
 */
public class ZkWatcher implements Watcher {

    private String watcherName;

    public ZkWatcher(String watcherName) {
        this.watcherName = watcherName;
    }

    @Override
    public void process(WatchedEvent event) {
        System.out.println(watcherName + ": " + event);
    }

    public static void main(String[] args) throws Exception {
        // watchers will be called only ones
        ZooKeeper zk = new ZooKeeper("localhost:2181", 10000, new ZkWatcher("Root-Watcher"));
        zk.getData("/data", new ZkWatcher("Data-Watcher"), null);
        zk.getChildren("/data", new ZkWatcher("Data-Children-Watcher"), null);
        Thread.sleep(60000);
        zk.close();
    }
}
