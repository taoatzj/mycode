import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
import job.MyJob;

import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;


public class TestJob {

	private static Scheduler sched = null;
	/**
	 * @param args
	 * @throws SchedulerException 
	 */
	public static void main(String[] args) throws SchedulerException {
		TestJob t = new TestJob();
		t.useJob();
		t.addJob("myjob1", "5 * * ? * *", MyJob.class);//启动任务，每隔5s执行一次MyJob任务
//		t.deleteJob("myjob1");//停止任务
	}
	
	/**
	 * 启动Schedule
	 * @throws SchedulerException
	 */
	public void useJob() throws SchedulerException{		
		SchedulerFactory sf = new StdSchedulerFactory();
		 try {
			sched = sf.getScheduler();
			sched.start();
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 任务中添加作业
	 * @param jobName 任务名称，可以自己命名
	 * @param spaceTime corn表达式，调用的时间，例如“5 * * ？ * *”
	 * @param clazz job实现类
	 */
	public void addJob(String jobName,String spaceTime,Class clazz){
		String groupName = jobName;
		JobDetail jobs = newJob(clazz).withIdentity(jobName,groupName).build();
		CronTrigger trigger; 
		try {
			if(sched.isShutdown()){
				sched.start();
			}
			trigger = newTrigger().withIdentity(jobName+groupName, groupName).withSchedule(cronSchedule(spaceTime)).build();
			sched.scheduleJob(jobs, trigger);
		} catch (Exception e) {
			e.printStackTrace();
			new Exception(e);
		} 
	
	}
	
	/**
	 * 在任务中删除改作业
	 * @param jobName
	 */
	public void deleteJob(String jobName){
		String groupName = jobName;
		try {
			if(sched.isShutdown()){
				sched.start();
			}else{
				JobKey key = new JobKey(jobName,groupName);
				sched.deleteJob(key);
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
			new Exception(e);
		}
	}

}
