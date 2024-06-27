package com.shyam.props;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@EnableConfigurationProperties
@ConfigurationProperties(prefix = "order-service")
@Configuration
@Getter
@Setter
public class AppProperties {

	public Map<String, String> message=new HashMap<>();
}
