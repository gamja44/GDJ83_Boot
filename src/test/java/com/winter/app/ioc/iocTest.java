package com.winter.app.ioc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.winter.app.robot.Robot;

@SpringBootTest
class iocTest {
	
	@Autowired
	private Robot robot;

	@Test
	void test() {
		
		System.out.println("Test Case");
		robot.getRobotArm().punch();
		//로봇이 널이면 실패, 특정 객체가 null이 아님을 확인하는 테스트메서드이다.
		assertNotNull(robot);
	}

}
