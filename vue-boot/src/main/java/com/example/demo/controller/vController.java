package com.example.demo.controller;

import java.util.List;
import java.util.Map;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.vDao;

@Controller
@MapperScan(basePackages = "com.example.demo.dao") //dao경로설정하면 mapper스캔한다.
public class vController {
	
	@Autowired
	private vDao vdao;
	
	@ResponseBody
	@RequestMapping(value="/getData")
	public List<Map<String, Object>> getData(@RequestParam Map<String, Object> map) throws Exception {
		return vdao.getData(map);
	}
	
	//무한스크롤
	@ResponseBody
	@RequestMapping(value="/getScorllPage/{limit}")
	public List<Map<String, Object>> getScorllPage(@PathVariable int limit)throws Exception {
		return vdao.getScorllPage(limit);
		
	}
	
	//결제
	@ResponseBody
	@RequestMapping(value="/getPaymentData")
	public int getPaymentData(@RequestParam Map<String, Object> map) throws Exception {
		System.out.println(map.toString());
		return 1;
	}
}
