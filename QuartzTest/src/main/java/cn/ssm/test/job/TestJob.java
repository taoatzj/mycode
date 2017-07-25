package cn.ssm.test.job;

import java.util.Date;

import org.springframework.stereotype.Component;
@Component
public class TestJob {

	
	public void test(String param){
		System.err.println("======我是带参数的========="+new Date()+"=========="+param);
	}
	
}
