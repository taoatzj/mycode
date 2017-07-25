package com.tianyan.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
    * @ClassName: ShareData
    * @Description:数据实体
    * @author joe
    * @date 2015-11-18
    *
 */
@Entity
@Table (name="share_data")
public class ShareData {
    
    @Id
    @GeneratedValue
    private int id;
    private String shareType;
    private String shareCode;
    private String shareName;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getShareType() {
        return shareType;
    }
    public void setShareType(String shareType) {
        this.shareType = shareType;
    }
    public String getShareCode() {
        return shareCode;
    }
    public void setShareCode(String shareCode) {
        this.shareCode = shareCode;
    }
    public String getShareName() {
        return shareName;
    }
    public void setShareName(String shareName) {
        this.shareName = shareName;
    }
    
    
    
    
    

}
