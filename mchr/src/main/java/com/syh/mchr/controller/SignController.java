package com.syh.mchr.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syh.mchr.entity.Sign;
import com.syh.mchr.service.ISignService;
import com.syh.mchr.util.JsonResult;

@RestController
@RequestMapping("sign")
public class SignController extends BaseController {
	@Autowired
	ISignService signService;
	
	@RequestMapping("signin")
	public JsonResult<Void> signIn(HttpSession session,Sign sign){
		sign.setEno(session.getAttribute("eno").toString());
		signService.sign(sign);
		return new JsonResult<Void>(SUCCESS);
	}
	
	@RequestMapping("list")
	public JsonResult<List<Sign>> signList(HttpSession session,String begin,String end){
		List<Sign> list;
		try {
			list = signService.signList(session.getAttribute("eno").toString(), new SimpleDateFormat("yyyy-MM-dd").parse(begin), new SimpleDateFormat("yyyy-MM-dd").parse(end));
			return new JsonResult<>(SUCCESS,list);
		} catch (ParseException e) {
			e.printStackTrace();
			throw new RuntimeException();
		}
	}
	
	@RequestMapping("create")
	public JsonResult<Void> createList(){
		Timer timer = new Timer();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		Date date = cal.getTime();
		ISignService service = signService;
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				service.createSignList();
			}
		}, date, 24*60*60*1000L);
		return new JsonResult<>(SUCCESS);
	}
}
