package com.project.dbmonitor.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class MonitoringRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // 🔹 Fetch latest metrics
    public List<Map<String, Object>> getMetrics() {
        String sql = """
            SELECT time, cpu_usage, memory_usage, active_connections
            FROM system_metrics_normal
            ORDER BY time DESC
            LIMIT 20
        """;

        return jdbcTemplate.queryForList(sql);
    }

    // 🔹 Delete using time (since no id column)
    public void deleteMetric(String time) {
        String sql = "DELETE FROM system_metrics_normal WHERE time = ?";
        jdbcTemplate.update(sql, time);
    }
    
    public List<Map<String, Object>> postgresPerformance() {

        String sql = """
            EXPLAIN ANALYZE
            SELECT
                date_trunc('hour', time),
                AVG(cpu_usage)
            FROM system_metrics_normal
            GROUP BY 1
        """;

        return jdbcTemplate.queryForList(sql);
    }
    
    public List<Map<String, Object>> timescalePerformance() {

        String sql = """
            EXPLAIN ANALYZE
            SELECT
                time_bucket('1 hour', time),
                AVG(cpu_usage)
            FROM system_metrics
            GROUP BY 1
        """;

        return jdbcTemplate.queryForList(sql);
    }
}