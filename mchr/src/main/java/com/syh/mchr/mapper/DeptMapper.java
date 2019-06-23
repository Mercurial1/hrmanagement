package com.syh.mchr.mapper;

import java.util.List;

import com.syh.mchr.entity.Dept;

public interface DeptMapper {
	/**
	 * 根据部门id获取部门名称
	 * @param deptno
	 * @return
	 */
	Dept findByDeptno(Integer deptno);
	/**
	 * 获取所有部门名称
	 * @return
	 */
	List<Dept> findAll();
}
