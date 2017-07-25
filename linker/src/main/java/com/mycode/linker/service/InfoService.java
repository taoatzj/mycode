package com.mycode.linker.service;

import com.mycode.linker.domain.Info;
import com.mycode.linker.mapper.InfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;



@Service
public class InfoService {

    @Autowired
    private InfoMapper infoMappper;

    public List<Info> getInfo(Integer age){
        return infoMappper.getInfo(age);
    }
}
