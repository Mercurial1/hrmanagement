package com.syh.mchr.service;

import com.syh.mchr.entity.Emp;
import com.syh.mchr.entity.User;
import com.syh.mchr.entity.UserVO;
import com.syh.mchr.ex.InsertException;
import com.syh.mchr.ex.PasswordNotMatchedException;
import com.syh.mchr.ex.PermissionException;
import com.syh.mchr.ex.UpdateException;
import com.syh.mchr.ex.UserNotFoundException;
import com.syh.mchr.ex.DuplicateException;

public interface IUserService {
	/**
	 * 添加员工信息和用户信息
	 * @param user 用户信息
	 * @param emp 员工信息
	 * @throws DuplicateException 用户名冲突异常
	 * @throws InsertException 插入异常
	 */
	void addpeople(User user,Emp emp,String eno) throws DuplicateException,InsertException,PermissionException;
	/**
	 * 登陆
	 * @param user
	 * @return
	 * @throws UserNotFoundException
	 * @throws PasswordNotMatchedException
	 */
	UserVO login(User user) throws UserNotFoundException,PasswordNotMatchedException;
	
	/**
	 * 修改照片
	 * @param uid 用户的id
	 * @param username 用户名
	 * @param avatar 头像的路径
	 * @throws UserNotFoundException 用户数据不存在，或者已经被标记为删除
	 * @throws UpdateException 更新数据失败
	 */
	void changePhoto(Integer uid, String eno, String photo) 
	    throws UserNotFoundException, UpdateException;
	
	/**
	 * 获取员工信息
	 * @param eno
	 * @return
	 */
	Emp getInfo(String eno) throws  UserNotFoundException;
	
	/**
	 * 修改员工信息
	 * @param eno
	 * @param emp
	 * @param string
	 * @throws UserNotFoundException
	 * @throws UpdateException
	 */
	public void changeInfo(String eno, Emp emp, String string) throws UserNotFoundException,UpdateException;
	
	/**
	 * 判断权限
	 * @param uid
	 * @throws PermissionException
	 */
	void isAdmin(Integer uid) throws PermissionException;
}
