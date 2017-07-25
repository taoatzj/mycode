package com.tianyan.domain.dao.impl;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tianyan.domain.dao.ShareDataDao;
import com.tianyan.domain.entity.ShareData;


/**
 * 
    * @ClassName: ShareDataDaoImpl
    * @Description: 股票数据数据库操作服务---注解成@Repository 
    * @author joe
    * @date 2015-11-19
    *
 */
@Repository 
@Transactional
public class ShareDataDaoImpl implements ShareDataDao{
    
    @Autowired
    SessionFactory  sessionFactory;

   
    public Serializable save(ShareData shareData) {
      Session  session=sessionFactory.getCurrentSession();
           return   session.save(shareData);
    }
    
    

}
