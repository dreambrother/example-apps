package com.github.dreambrother.cassandra;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * @author nshmakov
 */
public class CassandraDao {

    private Session cassandraSession;

    public void increaseCounter(final String hash, final long times) {
        cassandraSession.execute(
                "UPDATE disk_ks.page_view_counts" +
                        " SET counter_value = counter_value + ?" +
                        " WHERE private_hash = ?",
                times, hash);
    }

    public Map<String, Long> readCounters(final List<String> hash) {
        String inClause = generateInClause(hash.size());
        String query = "SELECT private_hash, counter_value FROM disk_ks.page_view_counts " +
                "WHERE private_hash IN " + inClause;

        ResultSet queryResult = cassandraSession.execute(query, hash.toArray());
        Map<String, Long> result = new HashMap<String, Long>();
        for (Row row : queryResult) {
            result.put(row.getString("private_hash"), row.getLong("counter_value"));
        }
        return result;
    }

    private String generateInClause(int paramsSize) {
        StringBuilder builder = new StringBuilder("(");
        for (int i = 0; i < paramsSize; i++) {
            if (i == paramsSize - 1) {
                builder.append("?");
            } else {
                builder.append("?, ");
            }
        }
        return builder.append(")").toString();
    }

    public void setCassandraSession(Session cassandraSession) {
        this.cassandraSession = cassandraSession;
    }
}
