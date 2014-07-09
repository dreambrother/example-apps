package com.github.dreambrother.cassandra;

import java.util.Arrays;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.HostDistance;
import com.datastax.driver.core.PoolingOptions;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.Session;

/**
 * @author nshmakov
 */
public class Main {

    public static void main(String[] args) {
        String[] hosts = new String[] { };
        Cluster cluster = Cluster.builder()
                .addContactPoints(hosts)
                .withQueryOptions(new QueryOptions().setConsistencyLevel(ConsistencyLevel.ONE))
                .withPoolingOptions(new PoolingOptions()
                        .setMinSimultaneousRequestsPerConnectionThreshold(HostDistance.REMOTE, 10)
                        .setMaxSimultaneousRequestsPerConnectionThreshold(HostDistance.REMOTE, 50)
                        .setCoreConnectionsPerHost(HostDistance.REMOTE, 1)
                        .setMaxConnectionsPerHost(HostDistance.REMOTE, 25))
                .build();
        Session session = cluster.newSession().init();

        CassandraDao dao = new CassandraDao();
        dao.setCassandraSession(session);

        dao.increaseCounter("1", 50);
        dao.increaseCounter("3", 10);
        System.out.println(dao.readCounters(Arrays.asList("1", "2", "3")));

        cluster.close();
    }
}
