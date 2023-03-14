package io.github.matheusned.msavaliadorcredito;

import org.springframework.boot.SpringApplication;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients
@EnableRabbit
public class MsAvaliadorcreditoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsAvaliadorcreditoApplication.class, args);
    }

}
