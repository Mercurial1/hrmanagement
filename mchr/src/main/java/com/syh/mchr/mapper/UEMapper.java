package com.syh.mchr.mapper;

import org.springframework.transaction.annotation.Transactional;

import com.syh.mchr.entity.Emp;
import com.syh.mchr.entity.User;

@Transactional
public interface UEMapper {
	/**
	 * 添加用户
	 * @param user 用户信息
	 * @return
	 */
	Integer insertUser(User user);
	/**
	 * 添加员工
	 * @param emp 员工信息
	 * @return
	 */
	Integer insertEmp(Emp emp);
}
