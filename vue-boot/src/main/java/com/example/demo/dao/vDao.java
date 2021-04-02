package com.example.demo.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

//@Component
@Mapper
public interface vDao {
	
	List<Map<String, Object>> getData(Map<String, Object> map) throws Exception;

	List<Map<String, Object>> getScorllPage(int limit)throws Exception;

}
