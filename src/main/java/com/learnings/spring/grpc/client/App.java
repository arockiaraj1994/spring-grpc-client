package com.learnings.spring.grpc.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Autowired
    private CollateralAPIService helloWorldClient;

    @GetMapping(path = "/say/{firstName}/{lastName}")
    public String say(@PathVariable String firstName, @PathVariable String lastName) {

        return helloWorldClient.sayHello(firstName, lastName);
    }

}
