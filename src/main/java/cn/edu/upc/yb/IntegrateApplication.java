package cn.edu.upc.yb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

/**
 * SpringBoot APP
 * @author  yiban
 */
@SpringBootApplication
@MapperScan("cn.edu.upc.yb.app.leinuo.weekcp.dao")
@ComponentScan("cn.edu.upc.yb.app.leinuo.weekcp.service")
@ComponentScan("cn.edu.upc.yb.app.leinuo.weekcp.controller")
public class IntegrateApplication {

	public static void main(String[] args) {
		SpringApplication.run(IntegrateApplication.class, args);
	}
}
