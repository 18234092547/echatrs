package com.ut.rec.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ut.rec.service.RecService;

@Controller
@RequestMapping(value = "rec", method = { RequestMethod.POST, RequestMethod.GET })
public class RecController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private RecService recService;
	private ObjectMapper objectMapper = new ObjectMapper();;

	@RequestMapping(value = "/main.do")
	public String main(Model model) {

		return "echarts/echarts";
	}

	// request 格式 20XX 或者 20XX-XX
	@RequestMapping(value = "/statis.do")
	@ResponseBody
	public String getStatis(Model model, HttpServletRequest request) {
		String date = request.getParameter("date");
		String json = "";
		try {
			json = objectMapper.writeValueAsString(recService.getEchartStatis(date));
		} catch (JsonProcessingException e) {
			logger.error("statis.do,obj转json错误");
			e.printStackTrace();
		}
		return json;
	}

	// request 格式 20XX-XX-XX
	@RequestMapping(value = "/detail.do")
	public String getDetail(HttpServletRequest request) {
		List<String> retString = null;

		return "echarts/detail";
	}

}
