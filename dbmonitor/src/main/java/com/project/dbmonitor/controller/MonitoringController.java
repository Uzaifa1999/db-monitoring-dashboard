package com.project.dbmonitor.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.project.dbmonitor.service.MonitoringService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/transactions") // keep same for Angular
public class MonitoringController {

    @Autowired
    private MonitoringService service;

    // 🔹 GET - map DB → UI format
    @GetMapping
    public List<Map<String, Object>> getTransactions() {

        List<Map<String, Object>> raw = service.getMetrics();

        return java.util.stream.IntStream.range(0, raw.size())
            .mapToObj(i -> {
                Map<String, Object> row = raw.get(i);

                return Map.of(
                    "id", i, // ✅ SAFE ID
                    "time", row.get("time").toString(),
                    "title", "CPU Usage",
                    "amount", row.get("cpu_usage"),
                    "category", "System"
                );
            }).toList();
    }

    // 🔹 DELETE
    @DeleteMapping("/{time}")
    public ResponseEntity<?> delete(@PathVariable String time) {
        service.deleteMetric(time);
        return ResponseEntity.ok("Deleted successfully");
    }
}