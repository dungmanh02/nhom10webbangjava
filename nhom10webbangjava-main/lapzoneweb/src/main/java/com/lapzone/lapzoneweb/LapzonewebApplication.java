package com.lapzone.lapzoneweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.persistence.autoconfigure.EntityScan;

@SpringBootApplication
@EntityScan("com.lapzone.lapzoneweb.model.entity")
public class LapzonewebApplication {

    public static void main(String[] args) {
        // Lệnh này sẽ khởi động Server Tomcat tích hợp sẵn
        SpringApplication.run(LapzonewebApplication.class, args);
        System.out.println("--- LAPZONE SERVER IS RUNNING ---");
    }

}