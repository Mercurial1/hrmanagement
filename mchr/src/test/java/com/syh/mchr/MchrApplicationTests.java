package com.syh.mchr;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.DigestUtils;

import com.syh.mchr.entity.Emp;
import com.syh.mchr.entity.User;
import com.syh.mchr.ex.InsertException;
import com.syh.mchr.mapper.EmpMapper;
import com.syh.mchr.mapper.UEMapper;
import com.syh.mchr.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MchrApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Autowired
	UEMapper ueMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	EmpMapper empMapper;
	
	@Test
	public void insert() {
		
		User user = new User();
		user.setPassword("1234");
		user.setUsername("root");
		
		String salt = UUID.randomUUID().toString();
		String pwd = user.getPassword();
		String md5pwd = getPassword(pwd,salt);
		user.setPassword(md5pwd);
		user.setSalt(salt);
		user.setIsadmin(1);
		user.setCreatedUser("1");
		user.setCreatedTime(new Date());
		user.setModifiedUser("1");
		user.setModifiedTime(new Date());
		Integer row1 = ueMapper.insertUser(user);
		if(row1!=1) {
			throw new InsertException("插入数据失败,请联系管理员");
		}
	}
	
	private String getPassword(String pwd, String salt) {
		pwd = pwd+salt;
		for(int i =0;i<3;i++) {
			pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
		}
		return pwd;
	}
}
