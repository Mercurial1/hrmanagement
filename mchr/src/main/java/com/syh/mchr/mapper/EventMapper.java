package com.syh.mchr.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.syh.mchr.entity.Event;

public interface EventMapper {
	/**
	 * 根据员工工号或领导编号或审批状态查询事件
	 * @param eno 员工工号
	 * @param mgr 领导工号
	 * @param status 状态
	 * @return
	 */
	List<Event> findEvent(@Param("eno")String eno,@Param("mgr")String mgr,@Param("status")Integer status);
	/**
	 * 通过事件id查询事件
	 * @param eventid
	 * @return
	 */
	Event findByEventid(Integer eventid);
	/**
	 * 插入事件
	 * @param event
	 * @return
	 */
	Integer insertEvent(Event event);
	/**
	 * 修改事件
	 * @param status
	 * @param mgr
	 * @return
	 */
	Integer updateStatusAndMgr(@Param("status")Integer status,@Param("mgr")String mgr,@Param("eventid")Integer eventid);
}
