package com.example.loggingdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public void addStudent(String name) {
        logger.info("📚 Adding student: {}", name);
    }

    public void deleteStudent(int id) {
        logger.warn("🗑 Deleting student with ID: {}", id);
    }

    public void causeError() {
        try {
            int result = 10 / 0;
        } catch (Exception e) {
            logger.error("❌ Error occurred: {}", e.getMessage());
        }
    }
}
