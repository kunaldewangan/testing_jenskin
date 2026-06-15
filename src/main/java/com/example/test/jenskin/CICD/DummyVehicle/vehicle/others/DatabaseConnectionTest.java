package com.example.test.jenskin.CICD.DummyVehicle.vehicle.others;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionTest implements CommandLineRunner {

    // 1. Manually define the logger (Replaces @Slf4j)
    private static final Logger log = LoggerFactory.getLogger(DatabaseConnectionTest.class);

    private final JdbcTemplate jdbcTemplate;

    // 2. Standard Constructor (Replaces @RequiredArgsConstructor)
    public DatabaseConnectionTest(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {
        log.info("--- Testing Mercedes Database Connection ---");
        try {
            String version = jdbcTemplate.queryForObject("SELECT version()", String.class);
            log.info("Successfully connected to PostgreSQL!");
            log.info("Database Version: " + version);
        } catch (Exception e) {
            log.error("--- CONNECTION FAILED! ---");
            log.error("Reason: " + e.getMessage());
        }
    }
}
