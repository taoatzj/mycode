package com.isesol.linker.service;

import com.isesol.linker.domain.Info;
import com.isesol.linker.mapper.InfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by isesol on 2017/7/21.
 */

@Service
public class InfoService {

    @Autowired
    private InfoMapper infoMappper;

    public List<Info> getInfo(Integer age){
        return infoMappper.getInfo(age);
    }
}
