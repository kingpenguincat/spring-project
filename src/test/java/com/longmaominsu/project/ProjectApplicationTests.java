package com.longmaominsu.project;

import com.longmaominsu.project.modules.sys.service.UserMapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProjectApplicationTests {
	@Autowired
	private UserMapper userMapper;
	@Test
	public void contextLoads() {
		System.out.println(("----- selectAll method test ------"));
//		List<SysUser> userList = userMapper.selectList(null);
//		HashMap queryMap = new HashMap();
//		queryMap.put("username","admin");
//		List<SysUser> users = userMapper.selectByMap(queryMap);
//		users.forEach(System.out::println);
//		Assert.assertEquals(1, users.size());
//		users.forEach(System.out::println);


	}

}
