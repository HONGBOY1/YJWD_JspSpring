package com.wp2023.pjh22.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Pjh22Controller {

    @GetMapping("/home")
    public void home(){
    	
    }

    @GetMapping({"/exam1", "/exam2"})
    public void hello(){

    }


}
