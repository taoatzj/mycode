package com.ibeifeng.service;

import com.ibeifeng.po.BlogInfo;

public interface BlogInfoService {
	//���ò��͸��Ի�����
	public void setBlogInfo(BlogInfo blogInfo);
	
	//��ò��͸��Ի�����
	public BlogInfo getBlogInfo(String name);
}
