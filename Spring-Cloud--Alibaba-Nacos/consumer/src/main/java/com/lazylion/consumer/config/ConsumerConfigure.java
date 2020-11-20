package com.lazylion.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @author -> 太傅
 * @date -> 2020/11/20 : 16:36
 * #description    ->
 */

@Configuration
public class ConsumerConfigure {

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
}
