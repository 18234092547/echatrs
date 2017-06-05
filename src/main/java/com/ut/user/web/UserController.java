package com.ut.user.web;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ut.user.entity.User;
import com.ut.user.service.UserService;

@Controller
@RequestMapping(value = "user", method = { RequestMethod.POST, RequestMethod.GET })
public class UserController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/login.do")
	@ResponseBody
	private long loginConfirm(HttpServletRequest request, @RequestBody String body) {
		ObjectMapper mapper = new ObjectMapper();
		User user;
		try {
			user = mapper.readValue(body, User.class);
			return this.userService.loginConfirm(user.getName(), user.getPassword());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return -1l;

	}

	@RequestMapping(value = "/list")
	private String list(Model model) {
		List<User> list = userService.getList();
		model.addAttribute("list", list);
		// list.jsp + model = ModelAndView
		return "list";// WEB-INF/jsp/"list".jsp
	}

	@RequestMapping(value = "/add")
	private String add(@RequestBody User user, Model model) {
		this.userService.add(user);
		return "detail";
	}

	@RequestMapping(value = "/register.do")
	@ResponseBody
	private long register(HttpServletRequest request, @RequestBody String body) {

		ObjectMapper mapper = new ObjectMapper();
		User user;
		try {
			user = mapper.readValue(body, User.class);
			return this.userService.registerUser(user.getName(), user.getPassword());
		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}

		return -1l;

		// -1L 注册失败
		// 0L 用户名已存在
		// >0成功
	}

	@RequestMapping(value = "/update.do")
	@ResponseBody
	private long update(HttpServletRequest request, @RequestBody String body) {

		ObjectMapper mapper = new ObjectMapper();

		HashMap<String, String> parsedMap;

		Long userId;
		String username;
		String password;
		try {
			parsedMap = mapper.readValue(body, HashMap.class);

			userId = Long.valueOf(parsedMap.get("userId"));
			username = parsedMap.get("username");
			password = parsedMap.get("password");

			return this.userService.updateUser(username, password, userId);

		} catch (IOException e) {
			e.printStackTrace();
			logger.error(e.getMessage());
		}
		return -1l;

		// -1L 修改失败
		// 0L 用户名已存在
		// >0成功

	}

}
