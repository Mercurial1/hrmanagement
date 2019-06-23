package com.syh.mchr.mapper;

import java.util.List;

import com.syh.mchr.entity.Job;

public interface JobMapper {
	/**
	 * 根据部门id获取部门下所有职位名称
	 * @param deptno
	 * @return
	 */
	List<Job> findByDeptno(Integer deptno);
}
