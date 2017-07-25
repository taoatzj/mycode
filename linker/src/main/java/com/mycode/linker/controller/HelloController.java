package com.mycode.linker.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class HelloController {

    @Value("${cupSize}")
    private String cupSize;

    @RequestMapping(value="/hello",method = RequestMethod.GET)
    @ResponseBody
    public String say(){

        return cupSize;
    }

    @RequestMapping(value="/hello2",method = RequestMethod.GET)
    public String say2(){

        return "index2";
    }
}
