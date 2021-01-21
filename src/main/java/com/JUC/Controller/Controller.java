package com.JUC.Controller;


import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("/aa")
    public  String aa(){
        System.out.println("xxx");
        return "index";
    }
}
