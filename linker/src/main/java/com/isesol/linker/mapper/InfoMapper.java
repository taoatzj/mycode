package com.isesol.linker.mapper;

import com.isesol.linker.domain.Info;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by isesol on 2017/7/21.
 */
public interface InfoMapper {

    @Select("select * from Info where age=#{age}")
    public List<Info> getInfo(Integer age);

}
