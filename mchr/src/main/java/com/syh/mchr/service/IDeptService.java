package com.syh.mchr.service;

import java.util.List;

import com.syh.mchr.entity.Dept;
import com.syh.mchr.entity.Job;

public interface IDeptService {
	/**
	 * 获取所有部门名字
	 * @return
	 */
	List<Dept> findDept();
	/**
	 * 获取部门下的所有职位
	 * @param deptno
	 * @return
	 */
	List<Job> findJob(Integer deptno);
}
