package com.syh.mchr.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.syh.mchr.entity.Event;
import com.syh.mchr.service.IEventService;
import com.syh.mchr.util.JsonResult;

@RestController
@RequestMapping("event")
public class EventController extends BaseController{
	
	@Autowired
	IEventService eventService;
	
	@RequestMapping("submit")
	public JsonResult<Void> submitEvent(@RequestParam("vacationBegin1")String vacationBegin,@RequestParam("vacationEnd1")String vacationEnd,Event event,HttpSession session){
		String eno = session.getAttribute("eno").toString();
		try {
			event.setVacationBegin(new SimpleDateFormat("yyyy-MM-dd").parse(vacationBegin));
			event.setVacationEnd(new SimpleDateFormat("yyyy-MM-dd").parse(vacationEnd));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		eventService.submitEvent(event, eno);
		return new JsonResult<Void>(SUCCESS);
	}
	
	Date begin1 = new Date(0);
	Date end1 = new Date();
	/**
	 * 查询自己提交的事件
	 * @param session
	 * @param status
	 * @param begin
	 * @param end
	 * @return
	 */
	@RequestMapping("query_submit")
	public JsonResult<List<Event>> querySubmitEvent(HttpSession session,@RequestParam(value = "status")Integer status,@RequestParam(value = "begin")String begin,@RequestParam(value = "end")String end){
		String eno = session.getAttribute("eno").toString();
		try {
			begin1 = begin==null? begin1:new SimpleDateFormat("yyyy-MM-dd").parse(begin);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		try {
			end1 = end==null? end1:new SimpleDateFormat("yyyy-MM-dd").parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Event> events = eventService.getEvent(eno, null, status, begin1, end1);
		for(int i=0;i<events.size();i++) {
			if(events.get(i).getEno()==events.get(i).getMgr()&&events.get(i).getStatus()!=Event.wait) {
				events.remove(i);
			}
		}
		System.err.println(events);
		return new JsonResult<List<Event>>(SUCCESS, events);
	}
	
	/**
	 * 查询自己需要处理的事件
	 * @param queryeno
	 * @param session
	 * @param status
	 * @param begin
	 * @param end
	 * @return
	 */
	@RequestMapping("query_console")
	public JsonResult<List<Event>> queryConsoleEvent(String queryeno,HttpSession session,String begin,String end){
		String mgr = session.getAttribute("eno").toString();
		Date begin1,end1;
		try {
			begin1 = new SimpleDateFormat("yyyy-MM-dd").parse(begin);
			end1 = new SimpleDateFormat("yyyy-MM-dd").parse(end);
			List<Event> events = eventService.getEvent(queryeno, mgr, Event.wait, begin1, end1);
			return new JsonResult<List<Event>>(SUCCESS, events);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	/**
	 * 处理事件
	 * @param session
	 * @param status
	 * @param eventid
	 * @return
	 */
	@RequestMapping("console")
	public JsonResult<Void> consoleEvent(HttpSession session, Integer status, Integer eventid){
		String eno = session.getAttribute("eno").toString();
		eventService.consoleEvent(eno, status, eventid);
		return new JsonResult<Void>(SUCCESS);
	}
	
}
