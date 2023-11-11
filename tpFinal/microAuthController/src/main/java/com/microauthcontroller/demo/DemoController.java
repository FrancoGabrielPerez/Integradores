package com.microauthcontroller.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class DemoController { //TODO esto va?
    
        @RequestMapping(value = "demo")
        public String demo() {
            return "demo";
        }
    
}
