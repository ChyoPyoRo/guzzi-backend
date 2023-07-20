package com.project.guzzi.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@MapperScan("com.guzzi.mapper")// Mapper  인터페이스가 위치하는 패키지를 스캔한다.
public class MyBatisConfig{
	// 추가적인 MyBatis 설정 코드 y
}