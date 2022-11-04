package io.github.artsiomshshshsk.mydictionary.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/home")
    public String hello(){
        return "Hello autodeploy!!";
    }
}
