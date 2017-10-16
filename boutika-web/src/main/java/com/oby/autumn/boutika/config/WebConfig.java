package com.oby.autumn.boutika.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.oby.autumn.boutika" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
@Import({ DataConfig.class ,LoggerConfig.class})
public class WebConfig {

}
