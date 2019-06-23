package com.syh.mchr.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.util.DigestUtils;

import com.syh.mchr.entity.Emp;
import com.syh.mchr.entity.User;
import com.syh.mchr.entity.UserVO;
import com.syh.mchr.ex.InsertException;
import com.syh.mchr.ex.PasswordNotMatchedException;
import com.syh.mchr.ex.PermissionException;
import com.syh.mchr.ex.UpdateException;
import com.syh.mchr.ex.UserNotFoundException;
import com.syh.mchr.ex.DuplicateException;
import com.syh.mchr.mapper.EmpMapper;
import com.syh.mchr.mapper.UEMapper;
import com.syh.mchr.mapper.UserMapper;

@Service
public class UserService implements IUserService {

	@Autowired
	UEMapper ueMapper;
	@Autowired
	UserMapper userMapper;
	@Autowired
	EmpMapper empMapper;
	@Autowired
	private DataSourceTransactionManager dstm;
	
	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void addpeople(User user, Emp emp,String eno) throws DuplicateException, InsertException {
		
		//获取user,判断是否存在,存在则抛出异常
		User user1 = userMapper.findByUsername(user.getUsername());
		if(user1!=null) {
			throw new DuplicateException("用户名已存在");
		}
		Emp emp1 = empMapper.findByIDcard(emp.getIDcard());
		System.err.println(emp1);
		if(emp1!=null&&emp1.getIsDelete()==0) {
			throw new DuplicateException("员工已存在");
		}
		String salt = UUID.randomUUID().toString();
		String pwd = user.getPassword();
		String md5pwd = getPassword(pwd,salt);
		user.setPassword(md5pwd);
		user.setSalt(salt);
		String date=emp.getIDcard().substring(14);
		emp.setEno(""+(emp.getDeptno()>=10?emp.getDeptno():"0"+emp.getDeptno())+(emp.getJobno()>=10?emp.getJobno():"0"+emp.getJobno())+date);
		emp.setHiredate(new Date());
		
		emp.setIsDelete(0);
		user.setCreatedUser(eno);
		user.setCreatedTime(new Date());
		user.setModifiedUser(eno);
		user.setModifiedTime(new Date());
		emp.setCreatedUser(eno);
		emp.setCreatedTime(new Date());
		emp.setModifiedUser(eno);
		emp.setModifiedTime(new Date());
		//事务提交
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("reg");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dstm.getTransaction(def);
		try{
			ueMapper.insertUser(user);
			ueMapper.insertEmp(emp);
		}catch(Exception e){
			dstm.rollback(status);
			throw new InsertException("插入数据失败,请联系管理员");
		}
	}

	@Override
	public UserVO login(User user) throws UserNotFoundException, PasswordNotMatchedException {
		User user1 = userMapper.findByUsername(user.getUsername());
		System.err.println(user1);
		System.err.println(empMapper.findByEid(user1.getUid()));
		if(user1==null||empMapper.findByEid(user1.getUid()).getIsDelete()==1) {
			throw new UserNotFoundException("用户名不存在");
		}
		String pwd = user.getPassword();
		String md5pwd = getPassword(pwd, user1.getSalt());
		if(!md5pwd.equals(user1.getPassword())) {
			throw new PasswordNotMatchedException("密码错误");
		}
		UserVO uservo = new UserVO();
		uservo.setUid(user1.getUid());
		uservo.setEno(empMapper.findByEid(user1.getUid()).getEno());
		return uservo;
	}
	
	private String getPassword(String pwd, String salt) {
		pwd = pwd+salt;
		for(int i =0;i<3;i++) {
			pwd = DigestUtils.md5DigestAsHex(pwd.getBytes());
		}
		return pwd;
	}

	@Override
	public void changePhoto(Integer uid, String eno, String photo) throws UserNotFoundException, UpdateException {
		Emp emp = empMapper.findByEid(uid);
		if(emp==null||emp.getIsDelete()==1) {
			throw new UserNotFoundException("员工不存在");
		}
		
		Integer row = empMapper.updatePhoto(uid, photo, eno, new Date());
		if(row!=1) {
			throw new UpdateException("修改照片失败");
		}
	}
	
	@Override
	public void isAdmin(Integer uid) throws PermissionException {
		User admin = userMapper.findByUid(uid);
		if(admin.getIsadmin()!=1) {
			throw new PermissionException("无管理员权限");
		}
	}

	@Override
	public Emp getInfo(String eno) throws  UserNotFoundException{
		Emp emp = empMapper.findByEno(eno);
		if(emp==null||emp.getIsDelete()==1) {
			throw new UserNotFoundException("员工不存在");
		}
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		try {
//			emp.setHiredate(sdf.parse(sdf.format(emp.getHiredate())));
//			emp.setBirthday(sdf.parse(sdf.format(emp.getBirthday())));
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		return emp;
	}

	@Override
	public void changeInfo(String eno, Emp emp, String modifiedUser) throws UserNotFoundException,UpdateException{
		Emp temp = empMapper.findByEno(eno);
		if(temp==null||temp.getIsDelete()==1) {
			throw new UserNotFoundException("员工不存在");
		}
		Integer row = empMapper.UpdateInfo(eno, emp.getEname(), emp.getGender(), emp.getAddress(), emp.getPhone(), emp.getEmail(), emp.getNation(), emp.getMgr(), emp.getSal(), emp.getDeptno(), emp.getJobno(), emp.getHiredate(), emp.getBirthday(), emp.getIDcard(), modifiedUser, new Date());
		if(row!=1) {
			throw new UpdateException("修改数据失败,请联系管理员");
		}
	}
	
}
