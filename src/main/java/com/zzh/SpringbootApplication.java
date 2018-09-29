package com.zzh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class SpringbootApplication {

    @RequestMapping("/")
    public String main(){
        return "Hello World!";
    }

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}
}
