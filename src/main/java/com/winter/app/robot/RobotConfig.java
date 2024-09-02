package com.winter.app.robot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//xml 대신에 java를 이용해서 객체 생성

@Configuration //xml 역할을 하는 annotation
public class RobotConfig {
	
	//<bean class="">의 역할을 메서드가 한다. 
	//객체를 만드는 어노테이션을 준다
	
	@Bean("ra")
	RobotArm make() {
		return new RobotArm();
	}
	//객체를 만들었기때문에 autowired가 사용이 가능하다
	@Bean
	Robot makeRobot() {
		Robot robot = new Robot();
		robot.setRobotArm(make());
		return robot;
	}
	
	
	
	
}
