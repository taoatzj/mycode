package com.xiaour.spring.boot.rocketmq.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by isesol on 2018/12/20.
 */

@RestController
public class MqttTestController {

    @RequestMapping("/mqttpush")
    public String pushMsg(String msg){
        System.out.println("1111111111111");
        return "ERROR";
    }
}
