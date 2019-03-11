package com.atguigu.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import com.netflix.loadbalancer.RoundRobinRule;

@Configuration
public class MySelfRule {

	@Bean
	public IRule myRule() {
		return new RandomRule_ZY();//达到的目的，用我们重新选择的随机算法替代默认的轮询。
	}
	
}
