package com.oby.autumn.boutika.web.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.oby.autumn.boutika.configuration.DataConfig;
import com.oby.autumn.boutika.configuration.LoggerConfig;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.oby.autumn.boutika" }, excludeFilters = {
		@Filter(type = FilterType.ANNOTATION, value = Configuration.class) })
@Import({ DataConfig.class ,LoggerConfig.class})
public class WebConfig {

}
