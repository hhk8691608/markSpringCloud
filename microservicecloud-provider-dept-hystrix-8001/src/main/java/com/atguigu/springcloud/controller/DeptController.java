package com.atguigu.springcloud.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atguigu.springcloud.entities.Dept;
import com.atguigu.springcloud.service.DeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@RestController
public class DeptController {
	
	@Autowired
	private DeptService service;
	
	
	
	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	@HystrixCommand(fallbackMethod = "processHystrix_Get")
	public Dept get(@PathVariable("id") Long id) {
		
		Dept dept = new Dept("部");
		if(id == 1) {
			dept.setDb_source("DeptProvider8001_Hystrix_App");
			dept.setDeptno(159357468200L);
			dept.setDname("运营部");
		}
		
		if (dept == null || dept.getDeptno() == null) {
			throw new RuntimeException("该ID：" + id + "没有没有对应的信息");
		}
		
		return dept;
	}
	
	public Dept processHystrix_Get(@PathVariable("id") Long id)
	{
		Dept dd = new Dept("该ID：没有没有对应的信息null--@HystrixCommand");
		dd.setDeptno(id);
		dd.setDname("该ID：没有没有对应的信息null--@HystrixCommand");
		dd.setDb_source("no this database in MySQL");
		return dd;
	}
	
	
	

}
