package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

//@Component
@Mapper
public interface vDao {
	
	List getData() throws Exception;

}
