package com.syh.mchr.service;

import java.util.Date;
import java.util.List;

import com.syh.mchr.entity.Sign;
import com.syh.mchr.ex.InsertException;
import com.syh.mchr.ex.UpdateException;
import com.syh.mchr.ex.UserNotFoundException;

public interface ISignService {
	/**
	 * 签到签退操作
	 * @param sign 签到信息
	 * @throws UserNotFoundException
	 * @throws InsertException
	 */
	void sign(Sign sign) throws UserNotFoundException,UpdateException;
	/**
	 * 查询签到情况
	 * @param eno
	 * @param begin
	 * @param end
	 * @return
	 */
	List<Sign> signList(String eno,Date begin,Date end) throws UserNotFoundException;
	
	/**
	 * 创建签到表
	 * @throws InsertException
	 */
	void createSignList() throws InsertException;
	
}
