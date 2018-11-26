package com.logo;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.RunWith;
import org.junit.runner.notification.Failure;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.logo.ui.components.ButtonGenerator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LogoresApplicationTests {

	@Test
	public void contextLoads() {
		Result result = JUnitCore.runClasses(ButtonGenerator.class);
	    for (Failure failure : result.getFailures()) {
	      System.out.println(failure.toString());
	    }
	}

}
