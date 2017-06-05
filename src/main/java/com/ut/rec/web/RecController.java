package com.ut.rec.web;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ut.rec.entity.Record;
import com.ut.rec.service.RecService;
//import com.ut.user.service.UserService;

@Controller
@RequestMapping(value = "rec", method = { RequestMethod.POST, RequestMethod.GET })
public class RecController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RecService recService;
	private ObjectMapper objectMapper = new ObjectMapper();;

	
	
	//request 格式 20XX  或者 20XX-XX
	@RequestMapping(value = "/statis.do")
	public String getStatis(Model model) {
		
		List<String> retString = null;
		
		try {
			model.addAttribute("data",objectMapper.writeValueAsString(recService.getEchartStatis("2017-05")));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "echarts/echarts";
	}

	
	//request 格式 20XX-XX-XX
	@RequestMapping(value = "/detail.do")
	public String getDetail(HttpServletRequest request) {
			
			List<String> retString = null;
			
//			return recService.getEchartStatis("2017-05-06");
			
			return "echarts/detail";
		}
	
	
	
	
	
	
}
