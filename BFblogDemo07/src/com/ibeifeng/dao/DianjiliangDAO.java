package com.ibeifeng.dao;

import java.util.Date;
import java.util.List;

import com.ibeifeng.po.Dianjiliang;

public interface DianjiliangDAO {
	
	//���ظ����£���IP����ʱ��ķ��ʼ�¼
	public List queryByAId(int AId,String IP,Date time);
	
	//��ӷ��ʼ�¼
	public void addJilu(Dianjiliang dianjiliang);
}
