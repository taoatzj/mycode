package cn.ssm.test.job;

import java.nio.charset.Charset;
import java.util.Date;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class TaskTest {
	public final Logger log = Logger.getLogger(this.getClass());
 
	public void run() {
		for (int i = 0; i < 1; i++) {
			log.debug(i+" run......................................" + (new Date()));
			System.err.println(i+" run......................................" + (new Date()));
		}

	}

	public void run1() {
		for (int i = 0; i < 1; i++) {
			log.debug(i+" run1......................................" + (new Date()));
		}
	}
	
}
