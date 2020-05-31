package org.vamshi.goldspacesvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class GoldspacesvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(GoldspacesvcApplication.class, args);
    }

}
