package com.ibeifeng.service;

import java.util.List;

import com.ibeifeng.fenye.Page;
import com.ibeifeng.fenye.Result;
import com.ibeifeng.po.Article;

public interface ArticleService {
	//�������µı���
	public void addArticle(Article article);
	
	//ȡ���û����е�����
	public List<Article> showUserAllArticle(String username);
	
	//��ҳ��ʾ�û�����
	public Result showUserArticleByPage(String username,Page page);
	
	//��ҳ��ʾȫ������
	public Result showArticleByPage(Page page);
	
	//��ʾ����
	public Article showArticle(int id);
	
	//���������
	public int getCritiqueCount(int AId);
}
