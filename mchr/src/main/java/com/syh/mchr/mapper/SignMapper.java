package com.syh.mchr.mapper;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.syh.mchr.entity.Sign;

public interface SignMapper {
	
	/**
	 * 根据日期查询签到信息
	 * @param eno
	 * @param begin
	 * @param end
	 * @return
	 */
	List<Sign> findByDate(@Param("eno")String eno,@Param("begin")Date begin,@Param("end")Date end);
	
	/**
	 * 根据日期查询签到信息
	 * @param eno
	 * @param date
	 * @return
	 */
	Sign findByDay(@Param("eno")String eno,@Param("date")Date date);
	
	/**
	 * 插入签到信息
	 * @return
	 */
	Integer InsertSignIn(@Param("eno")String eno,@Param("date")Date date);
	
	/**
	 * 更新签到信息
	 * @param sign
	 * @return
	 */
	Integer UpdateSignIn(@Param("eno")String eno,@Param("date")Date date,@Param("signInTime")Timestamp signInTime,@Param("signOutTime")Timestamp signOutTime,@Param("status")Integer status);
}
