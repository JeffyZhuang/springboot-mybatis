package com.zzh;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;


@ComponentScan
@EnableCaching
@SpringBootApplication(scanBasePackages = "com.zzh")
@MapperScan("com.zzh.mapper")
public class SpringbootApplication {



	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
