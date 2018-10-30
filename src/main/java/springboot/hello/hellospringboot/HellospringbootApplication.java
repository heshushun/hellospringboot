package springboot.hello.hellospringboot;


import javafx.application.Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 启动
 * @author hss
 * @since 2018-10-09
 */
@SpringBootApplication
@EnableScheduling
public class HellospringbootApplication extends SpringBootServletInitializer implements EmbeddedServletContainerCustomizer {

	private final static Logger logger = LoggerFactory.getLogger(HellospringbootApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(HellospringbootApplication.class,args);
		logger.info("HellospringbootApplication is success!");
	}

	@Override
	public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {

	}

	/**
	 * 如果需要把应用部署到容器(如tomcat),使用
	 * @param application
	 * @return
	 */
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(HellospringbootApplication.class);
	}


}
