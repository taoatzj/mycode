package cn.ssm.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import cn.ssm.test.model.ScheduleJobEntity;

@Mapper
public interface ScheduleJobDao {


	List<ScheduleJobEntity> getAll();

	void insertSelective(ScheduleJobEntity scheduleJob);

	ScheduleJobEntity selectByPrimaryKey(Long jobId);
	/**
	 * 暂停和开启
	 * @param jobEntity
	 */
	void updateByPrimaryKeySelective(ScheduleJobEntity jobEntity);

	void updateScheduleJob(ScheduleJobEntity scheduleJob);

	void deleteScheduleJob(Long jobId);

}
