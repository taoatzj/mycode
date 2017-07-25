package com.tianyan.domain.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tianyan.domain.dao.ShareDataDao;
import com.tianyan.domain.entity.ShareData;
import com.tianyan.domain.service.ShareDataService;


/**
 * 
    * @ClassName: ShareDataServiceImpl
    * @Description:股票数据业务实现---注解为服务@Service和事物@Transactional
    * @author joe
    * @date 2015-11-19
    *
 */
@Service
@Transactional
public class ShareDataServiceImpl implements ShareDataService{
    
    @Autowired
    ShareDataDao shareDataDao;

    public Serializable insertData(ShareData shareData) {
           return shareDataDao.save(shareData);
    }

}
