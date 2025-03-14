package com.example.ms2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ms2")
public class Ms2Controller {

    @RequestMapping("/hello")
    public String hello() {
        return "Hello from MS2";
    }
}
