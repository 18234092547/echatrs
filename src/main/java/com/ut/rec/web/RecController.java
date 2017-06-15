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
import com.ut.rec.entity.BarModel;
import com.ut.rec.entity.ChartModel;
import com.ut.rec.entity.Record;
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
		List<BarModel> barModelList = recService.getEchartStatis(date);
		// 第二个参数设置当前统计图总数
		ChartModel chartModel = new ChartModel(barModelList,"20");
		String json = "";
		try {
			json = objectMapper.writeValueAsString(chartModel);
		} catch (JsonProcessingException e) {
			logger.error("statis.do,obj转json错误");
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping(value = "/detail.do")
	@ResponseBody
	public List<Record> getDetail(Model model, HttpServletRequest request) {
		String date = request.getParameter("date");
		return recService.getList(date);
	}

}
