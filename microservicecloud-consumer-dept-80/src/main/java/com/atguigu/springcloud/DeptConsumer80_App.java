package com.atguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.atguigu.myrule.MySelfRule;

/***
 * 必须要定义在和主启动类不同的包下不然达不到定制的效果
 * @author admin
 *
 */
@SpringBootApplication
//在启动该微服务的时候就能去加载我们的自定义Ribbon配置类，从而使配置生效 具体那个服务，具体那个自定义配置类
@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
@EnableEurekaClient
public class DeptConsumer80_App
{
	public static void main(String[] args)
	{
		SpringApplication.run(DeptConsumer80_App.class, args);
	}
}
