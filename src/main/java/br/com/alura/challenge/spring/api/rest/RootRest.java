package br.com.alura.challenge.spring.api.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class RootRest {

    @GetMapping
    public String hello() {
        return "Hello World!";
    }

}