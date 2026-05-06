package com.project.dbmonitor.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dbmonitor.repository.MonitoringRepository;

@Service
public class MonitoringService {

    @Autowired
    private MonitoringRepository repo;

    public List<Map<String, Object>> getMetrics() {
        return repo.getMetrics();
    }

    public void deleteMetric(String time) {
        repo.deleteMetric(time);
    }
    public List<Map<String, Object>> postgresPerformance() {
        return repo.postgresPerformance();
    }

    public List<Map<String, Object>> timescalePerformance() {
        return repo.timescalePerformance();
    }
}