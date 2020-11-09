package com.bytelion.detect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author 太傅
 */
@EnableAsync
@SpringBootApplication
public class FaceDetectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FaceDetectApplication.class, args);
    }

}
