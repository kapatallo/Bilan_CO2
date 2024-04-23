package fr.univlyon1.m1if10.bilanCo2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Hello world.
 */
@RestController
public class HelloWorld {

    /**
     * Gets hello world.
     *
     * @return the hello world
     */
    @GetMapping(value = "/HelloWorld", produces = {"application/json"})
    public String getHelloWorld() {
        return "{\"data\": \"Hello world\" }";
    }
}
