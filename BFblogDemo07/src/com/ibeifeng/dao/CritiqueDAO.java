package com.ibeifeng.dao;

import java.util.List;

import com.ibeifeng.fenye.Page;
import com.ibeifeng.po.Critique;

public interface CritiqueDAO {
	//�������
	public void addCritique(Critique critique);
	
	//���ָ�����µ�������
	public int queryCritiqueCount(int AId);
	
	//����Page����ѯָ�����µ�����
	public List<Critique> queryByPage(int AId,Page page);
}
