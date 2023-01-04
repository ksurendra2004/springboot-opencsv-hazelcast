package com.opencsvdemo;

import com.hazelcast.config.Config;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import com.opencsvdemo.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class OpenCsvDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenCsvDemoApplication.class, args);
	}

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.opencsvdemo")).build();
	}

	@Bean
	public HazelcastInstance hazelcastInstance() {
		Config config = new Config();
		return Hazelcast.newHazelcastInstance(config);
	}

	@Bean
	public IMap<Long, Employee> employeeCache(HazelcastInstance hazelcastInstance) {
		return hazelcastInstance.getMap("employeeCache");
	}
}
