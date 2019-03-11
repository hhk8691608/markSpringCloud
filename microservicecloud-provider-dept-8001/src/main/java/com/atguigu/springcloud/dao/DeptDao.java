package com.atguigu.springcloud.dao;

import org.apache.ibatis.annotations.Mapper;

import com.atguigu.springcloud.entities.Dept;
import com.sun.tools.javac.util.List;

@Mapper
public interface DeptDao {
	
	
	public boolean addDept(Dept dept);

	public Dept findById(Long id);

	public List<Dept> findAll();
	
	

}


