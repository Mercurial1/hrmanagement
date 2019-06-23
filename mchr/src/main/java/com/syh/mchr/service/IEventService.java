package com.syh.mchr.service;

import java.util.Date;
import java.util.List;

import com.syh.mchr.entity.Event;
import com.syh.mchr.ex.EventEmptyException;
import com.syh.mchr.ex.InsertException;
import com.syh.mchr.ex.UpdateException;
import com.syh.mchr.ex.UserNotFoundException;

public interface IEventService {
	/**
	 * 提交事件
	 * @param event 事件
	 * @param eno 员工工号
	 * @throws UserNotFoundException 员工不存在异常
	 * @throws InsertException 插入异常
	 */
	void submitEvent(Event event,String eno) throws UserNotFoundException,InsertException;
	/**
	 * 查找在begin到end中提交的事件
	 * @param eno 员工工号
	 * @param mgr 领导工号
	 * @param status 状态
	 * @param begin 开始时间
	 * @param end 结束时间
	 * @return
	 * @throws UserNotFoundException
	 */
	List<Event> getEvent(String eno,String mgr,Integer status,Date begin,Date end) throws UserNotFoundException,EventEmptyException;
	/**
	 * 处理事件
	 * @param eno
	 * @param status
	 * @param eventid
	 * @throws UpdateException
	 * @throws EventEmptyException
	 */
	void consoleEvent(String eno,Integer status,Integer eventid) throws UpdateException,EventEmptyException;
}
