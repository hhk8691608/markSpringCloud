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

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;

@RestController
public class DeptController {
	
	@Autowired
	private DeptService service;
	
	
	
	@Autowired
	private DiscoveryClient client;
	
	@RequestMapping(value = "/dept/add", method = RequestMethod.POST)
	public boolean add(@RequestBody Dept dept)
	{
		if(dept.getDeptno() == 1) {
			return true;
		}
		return false;
	}

	@RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id)
	{
		Dept e1 = new Dept("销售部");
		
		if(id == 1){
			e1.setDeptno(1l);
			e1.setDname("管理部");
			e1.setDb_source("cloudDB03");
		}else {
			e1.setDeptno(3l);
			e1.setDname("销售部");
			e1.setDb_source("cloudDB03");
		}
		return e1;
	}

	@RequestMapping(value = "/dept/list", method = RequestMethod.GET)
	public List<Dept> list()
	{
		Dept e = new Dept("研发部");
		e.setDeptno(1l);
		e.setDname("研发部");
		e.setDb_source("cloudDB03");
		
		
		Dept e1 = new Dept("管理部");
		e1.setDeptno(2l);
		e1.setDname("管理部");
		e1.setDb_source("cloudDB03");
		
		Dept e2 = new Dept("销售部");
		e2.setDeptno(3l);
		e2.setDname("销售部");
		e2.setDb_source("cloudDB03");
		
		 List<Dept>  reuslt = new ArrayList<>();
		 reuslt.add(e);
		 reuslt.add(e1);
		 reuslt.add(e2);
		return reuslt;
	}
	
	@RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
	public Object discovery() {
		
		List<String> list = client.getServices();
		System.out.println("**********" + list);
		List<ServiceInstance> srvList =  client.getInstances("MICROSERVICECLOUD-DEPT");
		for(ServiceInstance element :srvList) {
			System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
		}
		
		return this.client;
	}

	
	

}
