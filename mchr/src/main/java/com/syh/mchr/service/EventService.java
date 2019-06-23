package com.syh.mchr.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syh.mchr.entity.Emp;
import com.syh.mchr.entity.Event;
import com.syh.mchr.ex.EventEmptyException;
import com.syh.mchr.ex.InsertException;
import com.syh.mchr.ex.UpdateException;
import com.syh.mchr.ex.UserNotFoundException;
import com.syh.mchr.mapper.EmpMapper;
import com.syh.mchr.mapper.EventMapper;

@Service
public class EventService implements IEventService {

	@Autowired
	EventMapper eventMapper;
	@Autowired
	EmpMapper empMapper;
	
	@Override
	public void submitEvent(Event event,String eno) throws UserNotFoundException, InsertException {
		Emp emp = empMapper.findByEno(eno);
		if(emp==null||emp.getIsDelete()==1) {
			throw new UserNotFoundException("员工不存在");
		}
		
		event.setMgr(emp.getMgr());
		event.setEno(eno);
		event.setStatus(Event.wait);
		event.setSubmitTime(new Date());
		Integer row = eventMapper.insertEvent(event);
		if(row!=1) {
			throw new InsertException("事件提交失败,稍后重试");
		}
		
	}

	@Override
	public List<Event> getEvent(String eno, String mgr, Integer status, Date begin, Date end)
			throws UserNotFoundException,EventEmptyException {
		if(eno!=null) {
			Emp emp = empMapper.findByEno(eno);
			if(emp==null||emp.getIsDelete()==1) {
				throw new UserNotFoundException("员工不存在");
			}
		}
		
		List<Event> events = new ArrayList<Event>();
		events.addAll(eventMapper.findEvent(eno, mgr, status));
		Iterator<Event> it = events.iterator();
		while(it.hasNext()) {
			//判断是否在查询时间内
			Event event = it.next();
			if(event.getSubmitTime().getTime()>end.getTime()||event.getSubmitTime().getTime()<begin.getTime()) {
				it.remove();
			}
		}
		if(events.isEmpty()) {
			throw new EventEmptyException("查询结果为空");
		}
		return events;
	}

	@Override
	public void consoleEvent(String eno, Integer status, Integer eventid) throws UpdateException, EventEmptyException {
		if(status==Event.agree) {
			Emp emp = empMapper.findByEno(eno);
			if(emp.getMgr()!=null) {
				Event event = eventMapper.findByEventid(eventid);
				if(event==null||!event.getMgr().equals(eno)) {
					throw new EventEmptyException("事件不存在");
				}
				Integer row = eventMapper.updateStatusAndMgr(Event.wait, emp.getMgr(), eventid);
				if(row!=1) {
					throw new UpdateException("提交失败,稍后重试");
				}
			} else {
				Event event = eventMapper.findByEventid(eventid);
				if(event==null||!event.getMgr().equals(eno)) {
					throw new EventEmptyException("事件不存在");
				}
				Integer row = eventMapper.updateStatusAndMgr(status, event.getEno(), eventid);
				if(row!=1) {
					throw new UpdateException("提交失败,稍后重试");
				}
			}
		} else if(status==Event.refuse) {
			Event event = eventMapper.findByEventid(eventid);
			if(event==null||!event.getMgr().equals(eno)) {
				throw new EventEmptyException("事件不存在");
			}
			Integer row = eventMapper.updateStatusAndMgr(status, event.getEno(), eventid);
			if(row!=1) {
				throw new UpdateException("提交失败,稍后重试");
			}
		}
	}

	

}
