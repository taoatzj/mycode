package com.tianyan.web.controller;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tianyan.domain.entity.ShareData;
import com.tianyan.domain.service.ShareDataService;


/**
 * 
    * @ClassName: IndexController
    * @Description:首页路由 ---注解@Controller
    * @author joe
    * @date 2015-11-19
    *
 */
@Controller
public class IndexController {
    
    @Autowired
    ShareDataService shareDataService;
    
    @RequestMapping("/testInDb")
    public String testInDb(Model model){
        ShareData  shareData=new ShareData();
        shareData.setShareCode("00023");
        shareData.setShareName("万科");
        shareData.setShareType("SZ");
        Serializable result=shareDataService.insertData(shareData);
        model.addAttribute("result", result);
        return "/test";
        
    }
    
    
    

}
