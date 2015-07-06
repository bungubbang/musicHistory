package com.fevi.music.top100;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.fevi.music.top100")
public class MusicHistoryTopApplication {

    public static void main(String[] args) {
        SpringApplication.run(MusicHistoryTopApplication.class, args);
    }
}
