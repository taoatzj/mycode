package com.isesol.linker.controller;

import com.github.pagehelper.PageHelper;
import com.isesol.linker.domain.Info;
import com.isesol.linker.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by isesol on 2017/7/21.
 */

@RestController
public class InfoController {

    @Autowired
    private InfoService infoService;

    @RequestMapping("/queryAge")
    public List<Info> getInfo(Integer age){
		/*
		 * 第一个参数：第几页;
		 * 第二个参数：每页获取的条数.
		 */
        PageHelper.startPage(1, 2);
        return infoService.getInfo(age);
    }

}
