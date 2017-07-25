package cn.ssm.test.controller;

import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.ssm.test.model.RetObj;
import cn.ssm.test.model.ScheduleJobEntity;
import cn.ssm.test.service.ScheduleJobService;
import cn.ssm.test.util.quartz.util.SpringContextUtils;


@Controller
@RequestMapping("/scheduleJob")
@SuppressWarnings("all")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService jobTaskServiceImpl;
	
	@RequestMapping("/taskList")
	public String taskList(HttpServletRequest request) {
		List<ScheduleJobEntity> taskList = jobTaskServiceImpl.getAllTask();
		request.setAttribute("taskList", taskList);
		return "base/task/taskList";
	}

	/**
	 * 保存定时任务
	 */
	@RequestMapping("add")
	@ResponseBody
	public RetObj add(HttpServletRequest request, ScheduleJobEntity scheduleJob) {
		RetObj retObj = new RetObj();
		retObj.setFlag(false);
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression());
		} catch (Exception e) {
			retObj.setMsg("cron表达式有误，不能被解析！");
			return retObj;
		}
		Object obj = null;
		try {
			if (StringUtils.isNotBlank(scheduleJob.getBeanName())) {
				obj = SpringContextUtils.getBean(scheduleJob.getBeanName());
			} 
		} catch (Exception e) {
		}
		if (obj == null) {
			retObj.setMsg("未找到目标类！");
			return retObj;
		} else {
			Class clazz = obj.getClass();
			Method method = null;
			try {
				Method[] m= clazz.getDeclaredMethods();
				for (Method method2 : m) {
					System.err.println(method2.getName());
				}
				if (scheduleJob.getParams()!=null && !"".equals(scheduleJob.getParams().trim())) {
					method = clazz.getMethod(scheduleJob.getMethodName(),String.class);
				}else
				{
					method = clazz.getMethod(scheduleJob.getMethodName(), null);
					
				}
			
			} catch (Exception e) {
				
			}
			if (method == null) {
				retObj.setMsg("未找到目标方法！");
				return retObj;
			}
		}
		try {
			jobTaskServiceImpl.addTask(scheduleJob);
		} catch (Exception e) {
			e.printStackTrace();
			retObj.setFlag(false);
			retObj.setMsg("保存失败，检查 name group 组合是否有重复！");
			return retObj;
		}

		retObj.setFlag(true);
		return retObj;
	}
	
	/**
	 * 修改定时任务
	 */
	@RequestMapping("/updateScheduleJob")
	@ResponseBody
	public RetObj updateScheduleJob(HttpServletRequest request, ScheduleJobEntity scheduleJob) {
		RetObj retObj = new RetObj();
		retObj.setFlag(false);
		try {
			jobTaskServiceImpl.updateScheduleJob(scheduleJob);;
			retObj.setFlag(true);
			retObj.setMsg("OK");
			return retObj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * @param request
	 * @param jobId
	 * @param cmd
	 * @return
	 */
	@RequestMapping("changeJobStatus")
	@ResponseBody
	public RetObj changeJobStatus(HttpServletRequest request, Long jobId, String cmd) {
		RetObj retObj = new RetObj();
		retObj.setFlag(false);
		try {
			jobTaskServiceImpl.changeStatus(jobId, cmd);
		} catch (Exception e) {
			retObj.setMsg("任务状态改变失败！");
			return retObj;
		}
		retObj.setFlag(true);
		return retObj;
	}
	
	@RequestMapping("updateCron")
	@ResponseBody
	public RetObj updateCron(HttpServletRequest request, Long jobId, String cron) {
		RetObj retObj = new RetObj();
		retObj.setFlag(false);
		try {
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
		} catch (Exception e) {
			retObj.setMsg("cron表达式有误，不能被解析！");
			return retObj;
		}
		try {
			jobTaskServiceImpl.updateCron(jobId, cron);
		} catch (Exception e) {
			retObj.setMsg("cron更新失败！");
			return retObj;
		}
		retObj.setFlag(true);
		return retObj;
	}
	
	@RequestMapping("deleteScheduleJob")
	@ResponseBody
	public RetObj deleteScheduleJob(HttpServletRequest request, Long jobId) {
		RetObj retObj = new RetObj();
		retObj.setFlag(false);
		try {
			jobTaskServiceImpl.deleteScheduleJob(jobId);
			retObj.setFlag(true);
		} catch (Exception e) {
			retObj.setMsg("任务状态改变失败！");
			return retObj;
		}
		
		return retObj;
	}
	
	@RequestMapping("getTaskById")
	@ResponseBody
	public RetObj getTaskById(HttpServletRequest request,HttpServletResponse response, Long jobId) {
		RetObj retObj = new RetObj();
		ScheduleJobEntity jobEntity= jobTaskServiceImpl.getTaskById(jobId);
		retObj.setFlag(true);
		retObj.setObj(jobEntity);
		response.setContentType("text/json"); 
		response.setCharacterEncoding("UTF-8");
		System.err.println(JSON.toJSONString(retObj));
		return retObj;
	}
	
	
	
	
	/*@RequestMapping("/getTaskById/{jobId}")
	public String getTaskById(HttpServletRequest request,Model model,@PathVariable ("jobId") Long jobId) {
		ScheduleJobEntity jobEntity= jobTaskServiceImpl.getTaskById(jobId);
		model.addAttribute("jobEntity", jobEntity);
		return "base/task/editTask";
	}
	*/
	/*	@RequestMapping("getTaskById1")
	public String getTaskById1(HttpServletRequest request,HttpServletResponse response, Long jobId) {
		RetObj retObj = new RetObj();
		ScheduleJobEntity jobEntity= jobTaskServiceImpl.getTaskById((long) 3);
		retObj.setFlag(true);
		retObj.setObj(jobEntity);
		response.setContentType("text/json"); 
		response.setCharacterEncoding("UTF-8");
		System.err.println(JSON.toJSONString(retObj));
		return JSON.toJSONString(retObj);
	}*/
}
