package com.syh.mchr.service;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.syh.mchr.entity.Emp;
import com.syh.mchr.entity.Sign;
import com.syh.mchr.ex.InsertException;
import com.syh.mchr.ex.NightSignException;
import com.syh.mchr.ex.UpdateException;
import com.syh.mchr.ex.UserNotFoundException;
import com.syh.mchr.mapper.EmpMapper;
import com.syh.mchr.mapper.SignMapper;

@Service
public class SignService implements ISignService {
	
	@Autowired
	SignMapper signMapper;
	@Autowired
	EmpMapper empMapper;
	
	
	@Override
	public void sign(Sign sign) throws UserNotFoundException, UpdateException,NightSignException {
		Emp emp = empMapper.findByEno(sign.getEno());
		if(emp==null||emp.getIsDelete()==1) {
			throw new UserNotFoundException("员工不存在");
		}
		if(sign.getSignState()==Sign.SIGNIN) {
			
			Date date = new Date();
			Sign morning = signMapper.findByDay(sign.getEno(), date);
			if(morning.getSignOutTime()!=null) {
				throw new NightSignException("已进行签退,不能签到");
			}
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY,9);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,0);
			Date signIn = calendar.getTime();
			//判断是否迟到
			if(date.getTime()>signIn.getTime()) {
				sign.setStatus(Sign.late);
			}else {
				sign.setStatus(Sign.common);
			}
			Integer row = signMapper.UpdateSignIn(sign.getEno(), date, new Timestamp(date.getTime()), null, sign.getStatus());
			if(row!=1) {
				throw new UpdateException("签到失败,稍后重试");
			}
		} else if(sign.getSignState()==Sign.SIGNOUT) {
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			calendar.set(Calendar.HOUR_OF_DAY,17);
			calendar.set(Calendar.MINUTE,0);
			calendar.set(Calendar.SECOND,0);
			Date signOut = calendar.getTime();
			//得到当天早上签到情况
			Sign morning = signMapper.findByDay(sign.getEno(), date);
			//判断是否早退
			if(date.getTime()<signOut.getTime()) {
				if(morning.getStatus()==Sign.late) {
					sign.setStatus(Sign.lateAndEarly);
				}
				else if(morning.getStatus()==Sign.ignore) {
					sign.setStatus(Sign.ignore);
				}
				else if(morning.getStatus()==Sign.common) {
					sign.setStatus(Sign.early);
				}
			} else {
				if(morning.getStatus()==Sign.late) {
					sign.setStatus(Sign.late);
				}
				else if(morning.getStatus()==Sign.ignore) {
					sign.setStatus(Sign.ignore);
				}
				else if(morning.getStatus()==Sign.common) {
					sign.setStatus(Sign.common);
				}
			}
			Integer row = signMapper.UpdateSignIn(sign.getEno(), date, null, new Timestamp(date.getTime()), sign.getStatus());
			if(row!=1) {
				throw new UpdateException("签退失败,稍后重试");
			}
		}
		
	}

	@Override
	public List<Sign> signList(String eno, Date begin, Date end) throws UserNotFoundException {
		Emp emp = empMapper.findByEno(eno);
		if(emp==null||emp.getIsDelete()==1) {
			throw new UserNotFoundException("员工不存在");
		}
		return signMapper.findByDate(eno, begin, end);
	}

	@Autowired
	private DataSourceTransactionManager dstm;
	@Override
	@Transactional(rollbackFor = {Exception.class})
	public void createSignList() throws InsertException {
		List<Emp> emp = empMapper.findAll();
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("sign");
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = dstm.getTransaction(def);
		try{
			for(int i =0;i<emp.size();i++) {
				signMapper.InsertSignIn(emp.get(i).getEno(),new Date());
			}
		}catch(Exception e){
			dstm.rollback(status);
			throw new InsertException("插入数据失败,请联系管理员");
		}
		
	}

}
