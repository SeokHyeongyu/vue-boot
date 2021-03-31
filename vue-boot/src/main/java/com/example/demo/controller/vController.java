package com.example.demo.controller;

import java.util.List;
import java.util.Map;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.vDao;

@Controller
@MapperScan(basePackages = "com.example.demo.dao")
public class vController {
	
	@Autowired
	private vDao vdao;
	
	@ResponseBody
	@RequestMapping(value="/getData")
	public List<Map> getData(@RequestParam Map<String, Object> map) throws Exception {
		return vdao.getData(map);
	}
}
