package cn.ssm.test.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.quartz.CronTrigger;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.ssm.test.dao.ScheduleJobDao;
import cn.ssm.test.model.ScheduleJobEntity;
import cn.ssm.test.service.ScheduleJobService;
import cn.ssm.test.util.quartz.util.Constant.ScheduleStatus;
import cn.ssm.test.util.quartz.util.ScheduleUtils;

@Service("scheduleJobService")
public class ScheduleJobServiceImpl implements ScheduleJobService {
	@Autowired
    private Scheduler scheduler;
	@Autowired
	private ScheduleJobDao schedulerJobDao;
	
	/**
	 * 项目启动时，初始化定时器
	 */
	@PostConstruct
	public void init(){
		List<ScheduleJobEntity> scheduleJobList = schedulerJobDao.getAll();
		for(ScheduleJobEntity scheduleJob : scheduleJobList){
			CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            //如果不存在，则创建
            if(cronTrigger == null) {
                ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
            }else {
                ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
            }
		}
	}

	/**
	 * 定时任务列表
	 */
	@Override
	public List<ScheduleJobEntity> getAllTask() {
		
		return schedulerJobDao.getAll();
	}

	/**
	 * 保存定时任务
	 */
	@Override
	public void addTask(ScheduleJobEntity scheduleJob) {
		scheduleJob.setCreateTime(new Date());
		schedulerJobDao.insertSelective(scheduleJob);
		
		ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
	}
	
	
	/**
	 * 开始/暂停定时任务
	 */
	@Override
	public void changeStatus(Long jobId, String cmd) {
		ScheduleJobEntity jobEntity = getTaskById(jobId);
		if (jobEntity==null) {
			return ;
		} 
	if ("stop".equals(cmd)) {
		ScheduleUtils.pauseJob(scheduler, jobId);
    	
    	jobEntity.setStatus(ScheduleStatus.PAUSE.getValue());
	} else if ("start".equals(cmd)) {
		ScheduleUtils.resumeJob(scheduler, jobId);
		jobEntity.setStatus(ScheduleStatus.NORMAL.getValue());
	}
	schedulerJobDao.updateByPrimaryKeySelective(jobEntity);	
		
	}
    
	
	/**
	 * 从数据库中查询job
	 */
	public ScheduleJobEntity getTaskById(Long jobId) {
		return schedulerJobDao.selectByPrimaryKey(jobId);
	}

	/**
	 * 更新Corn 表达式
	 */
	@Override
	public void updateCron(Long jobId, String cron) {
		ScheduleJobEntity jobEntity = getTaskById(jobId);
		if (jobEntity==null) {
			return ;
		} 
		jobEntity.setCronExpression(cron.trim());
		 ScheduleUtils.updateScheduleJob(scheduler, jobEntity);
		
		schedulerJobDao.updateByPrimaryKeySelective(jobEntity);	
	}

	@Override
	public void updateScheduleJob(ScheduleJobEntity scheduleJob) {
		 	ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
	        schedulerJobDao.updateByPrimaryKeySelective(scheduleJob);
	}

	
	@Override
	public void deleteScheduleJob(Long jobId) {
		ScheduleUtils.deleteScheduleJob(scheduler, jobId);
		//删除数据
    	schedulerJobDao.deleteScheduleJob(jobId);
		
	}





	

	
	
}
