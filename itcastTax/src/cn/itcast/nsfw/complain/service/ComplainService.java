package cn.itcast.nsfw.complain.service;

import cn.itcast.core.service.BaseService;
import cn.itcast.nsfw.complain.entity.Complain;

public interface ComplainService extends BaseService<Complain> {

	//自动受理投诉
	public void autoDeal();
}
