package com.ibeifeng.service;

import com.ibeifeng.fenye.Page;
import com.ibeifeng.fenye.Result;
import com.ibeifeng.po.Critique;

public interface CritiqueService {
	//�������
	public void addCritique(Critique critique);
	
	//��ҳ��ʾ����
	public Result showCritiqueByPage(int AId,Page page);
}
