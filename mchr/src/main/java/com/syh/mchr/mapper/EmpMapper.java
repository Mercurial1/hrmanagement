package com.syh.mchr.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.syh.mchr.entity.Emp;

public interface EmpMapper {
	/**
	 * 根据员工id查询信息
	 * @param eid
	 * @return
	 */
	Emp findByEid(Integer eid);
	/**
	 * 根据员工身份证号查询信息
	 * @param iDcard
	 * @return
	 */
	Emp findByIDcard(String iDcard);
	/**
	 * 插入员工照片
	 * @param uid 用户id
	 * @param photo 照片路径
	 * @param modifiedUser 修改人
	 * @param modifiedTime 修改日期
	 * @return
	 */
	Integer updatePhoto(@Param("eid") Integer eid, 
	        @Param("photo") String photo,
	        @Param("modifiedUser") String modifiedUser,
	        @Param("modifiedTime") Date modifiedTime);
	/**
	 * 根据员工工号查询信息
	 * @param eno
	 * @return
	 */
	Emp findByEno(String eno);
	
	/**
	 * 查询所有员工信息
	 * @return
	 */
	List<Emp> findAll();
	
	/**
	 * 管理员修改员工信息
	 * @param eid
	 * @param eno
	 * @param ename
	 * @param gender
	 * @param address
	 * @param phone
	 * @param email
	 * @param nation
	 * @param mgr
	 * @param sal
	 * @param deptno
	 * @param jobno
	 * @param hiredate
	 * @param birthday
	 * @param IDcard
	 * @param modifiedUser
	 * @param modifiedTime
	 * @return
	 */
	Integer UpdateInfo(@Param("eno")String eno,@Param("ename")String ename,@Param("gender")Integer gender,@Param("address")String address,
	 @Param("phone")String phone,@Param("email")String email,@Param("nation")String nation,@Param("mgr")String mgr,
	 @Param("sal")Double sal,@Param("deptno")Integer deptno,@Param("jobno")Integer jobno,@Param("hiredate") Date hiredate,
	 @Param("birthday")Date birthday,@Param("IDcard")String IDcard,@Param("modifiedUser") String modifiedUser,
     @Param("modifiedTime") Date modifiedTime);
	
	
	
}
