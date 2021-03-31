package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

//@Component
@Mapper
public interface vDao {
	
	List getData(Map<String, Object> map) throws Exception;

}
