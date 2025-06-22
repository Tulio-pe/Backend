package com.acme.tallerazo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TallerazoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TallerazoApplication.class, args);
    }

}
