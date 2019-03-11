package com.atguigu.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.atguigu.springcloud.entities.Dept;

import feign.hystrix.FallbackFactory;

@Component // 不要忘记添加，不要忘记添加
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

	@Override
	public DeptClientService create(Throwable cause) {
		return new DeptClientService() {
			
			
			@Override
			public Dept get(long id) {
				Dept dd = new Dept("该ID：没有没有对应的信息null--@HystrixCommand");
				dd.setDeptno(id);
				dd.setDname("该ID：没有没有对应的信息null--@HystrixCommand");
				dd.setDb_source("no this database in MySQL");
				return dd;
			}
			
			
			@Override
			public List<Dept> list()
			{
				return null;
			}

			@Override
			public boolean add(Dept dept)
			{
				return false;
			}
			
			
		};
		
	}

}
