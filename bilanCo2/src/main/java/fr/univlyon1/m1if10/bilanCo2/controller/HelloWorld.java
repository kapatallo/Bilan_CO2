package fr.univlyon1.m1if10.bilanCo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The type Hello world.
 */
@Controller
public class HelloWorld {

    /**
     * Gets hello world.
     *
     * @return the hello world
     */
    @ResponseBody
    @GetMapping(value = "/HelloWorld", produces = {"application/json"})
    public String getHelloWorld() {
        return "{\"data\": \"Hello world\" }";
    }
}
