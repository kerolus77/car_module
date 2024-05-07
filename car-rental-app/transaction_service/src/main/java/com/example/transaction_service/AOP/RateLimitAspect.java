package com.example.transaction_service.AOP;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class RateLimitAspect {

    // Map to store the number of requests per method
    private Map<String, Integer> requestCount = new HashMap<>();

//    @Value("${rate.limit}") // Get rate limit value from properties or configuration
    @Value("30") // Get rate limit value from properties or configuration
    private int rateLimit;

    @Around("execution(* com.example.transaction_service.controller.*.*(..))")
    public Object rateLimitAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        String methodName = joinPoint.getSignature().toShortString();

        // Increment the request count for this method
        requestCount.putIfAbsent(methodName, 0);
        int count = requestCount.get(methodName);
        if (count >= rateLimit) {
            // Custom error response when rate limit is exceeded
            CustomErrorResponse errorResponse = new CustomErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.TOO_MANY_REQUESTS.value(),
                    "Rate Limit Exceeded",
                    "Rate limit exceeded for method: " + methodName
            );
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body(errorResponse);
        } else {
            requestCount.put(methodName, count + 1);
        }

        // Proceed with the method execution
        Object result = joinPoint.proceed();

        return result;
    }

    // Custom error response class
    public static class CustomErrorResponse {
        private LocalDateTime timestamp;
        private int status;
        private String error;
        private String message;

        public CustomErrorResponse(LocalDateTime timestamp, int status, String error, String message) {
            this.timestamp = timestamp;
            this.status = status;
            this.error = error;
            this.message = message;
        }

        // Getters and setters
        // You may need to generate them or provide them manually based on your needs
    }
}
