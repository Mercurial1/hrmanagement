package com.syh.mchr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.syh.mchr.entity.Dept;
import com.syh.mchr.entity.Job;
import com.syh.mchr.service.IDeptService;
import com.syh.mchr.util.JsonResult;

@RestController
@RequestMapping("dept")
public class DeptController extends BaseController {
	
	@Autowired
	IDeptService deptService;
	
	@RequestMapping("list")
	public JsonResult<List<Dept>> deptList(){
		List<Dept> depts = deptService.findDept();
		return new JsonResult<List<Dept>>(SUCCESS,depts);
	}
	
	@RequestMapping("job")
	public JsonResult<List<Job>> joblist(Job job){
		System.err.println(job.getDeptno());
		List<Job> jobs = deptService.findJob(job.getDeptno());
		return new JsonResult<List<Job>>(SUCCESS,jobs);
	}
	
}
