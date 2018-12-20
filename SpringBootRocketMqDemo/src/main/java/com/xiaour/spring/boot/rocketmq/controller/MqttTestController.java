package com.xiaour.spring.boot.rocketmq.controller;

import com.xiaour.spring.boot.rocketmq.paho.PahoProduce;
import com.xiaour.spring.boot.rocketmq.producer.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by isesol on 2018/12/20.
 */

@RestController
public class MqttTestController {

    @Autowired
    private PahoProduce producer;

    @RequestMapping("/mqttpush")
    public String pushMsg(String msg){
        System.out.println("1111111111111");
        producer.send();
        return "OK";
    }
}
