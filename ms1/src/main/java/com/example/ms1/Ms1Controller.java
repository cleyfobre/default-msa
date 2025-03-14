package com.example.ms1;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ms1")
public class Ms1Controller {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from MS1!";
    }

}
