package cn.ssm.test.service;

import java.util.List;
import java.util.Map;

import cn.ssm.test.model.ScheduleJobEntity;

/**
 * 定时任务
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年11月28日 上午9:55:32
 */
public interface ScheduleJobService {


	
	

	
	/**
	 * 查询定时任务列表
	 */
	List<ScheduleJobEntity> getAllTask();
	
	/**
	 * 添加定时任务
	 * @param scheduleJob
	 */
	void addTask(ScheduleJobEntity scheduleJob);
	
	/**
	 * 开始/暂停 定时任务
	 * @param jobId
	 * @param cmd
	 */
	void changeStatus(Long jobId, String cmd);

	/**
	 * 更新 Corn 表达式
	 * @param jobId
	 * @param cron
	 */
	void updateCron(Long jobId, String cron);
	
	
	ScheduleJobEntity getTaskById(Long jobId);




	void updateScheduleJob(ScheduleJobEntity scheduleJob);

	void deleteScheduleJob(Long jobId);
}
