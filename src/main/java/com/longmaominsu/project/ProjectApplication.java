package com.longmaominsu.project;

import com.longmaominsu.project.modules.sys.entity.SysUser;
import com.longmaominsu.project.modules.sys.service.UserMapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootApplication
@EnableAutoConfiguration
@RestController
@MapperScan("com.longmaominsu.project")
public class ProjectApplication {
	@Autowired
	private UserMapper userMapper;
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
	}

	@RequestMapping(value = "/",produces = "text/plain;charset=utf-8")
	String index(){

		return "Hello this is spring boot demo";
	}


	@RequestMapping(value="/test",method = RequestMethod.GET)
	@ResponseBody
	public Map test(String name,String age){
		HashMap map = new HashMap();
		List<SysUser> userList = userMapper.selectList(null);
		userList.forEach(System.out::println);
		map.put("name",name);
		map.put("age", age);
		return map;
	}
}
