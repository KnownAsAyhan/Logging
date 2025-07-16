package com.example.loggingdemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    public void addStudent(String name) {
        logger.info("📚 [INFO] Adding student: {}", name);
        logger.debug("🔍 [DEBUG] addStudent() called with name='{}'", name);
    }

    public void deleteStudent(int id) {
        logger.warn("⚠️ [WARN] Deleting student with ID: {}", id);
        logger.debug("🔍 [DEBUG] deleteStudent() called with id={}", id);
    }

    public void causeError() {
        try {
            int result = 10 / 0;
        } catch (Exception e) {
            logger.error("❌ [ERROR] Something went wrong: {}", e.getMessage());
            logger.debug("🔍 [DEBUG] Error details", e);
        }
    }
}
