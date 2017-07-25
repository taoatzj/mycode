package com.mycode.linker.mapper;

import com.mycode.linker.domain.Info;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface InfoMapper {

    @Select("select * from Info where age=#{age}")
    public List<Info> getInfo(Integer age);

}
