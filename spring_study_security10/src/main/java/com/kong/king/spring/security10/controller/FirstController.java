
package com.kong.king.spring.security10.controller;


import lombok.extern.log4j.Log4j2;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequestMapping("/")
public class FirstController {
	
    @GetMapping("/guest")
    public String guest(){
    	return "/guest/guest01";
    }

    @GetMapping("/manager")
    public String manager(){
    	return "/manager/manager01";
    }


    @GetMapping("/admin")
    public String admin(){
    	return "/admin/admin01";
    }



}
