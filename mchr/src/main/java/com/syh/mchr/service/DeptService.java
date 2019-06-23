package com.syh.mchr.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.syh.mchr.entity.Dept;
import com.syh.mchr.entity.Job;
import com.syh.mchr.mapper.DeptMapper;
import com.syh.mchr.mapper.JobMapper;

@Service
public class DeptService implements IDeptService {

	@Autowired
	DeptMapper deptMapper;
	@Autowired
	JobMapper jobMapper;
	
	@Override
	public List<Dept> findDept() {
		List<Dept> depts = new ArrayList<Dept>();
		depts.addAll(deptMapper.findAll());
		return depts;
	}

	@Override
	public List<Job> findJob(Integer deptno) {
		List<Job> jobs = new ArrayList<Job>();
		jobs.addAll(jobMapper.findByDeptno(deptno));
		return jobs;
	}

}
