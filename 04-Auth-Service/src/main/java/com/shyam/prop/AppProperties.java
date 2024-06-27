package com.shyam.prop;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "auth-service")
@Configuration
public class AppProperties {
private Map<String,String> messages=new HashMap<>();
}
